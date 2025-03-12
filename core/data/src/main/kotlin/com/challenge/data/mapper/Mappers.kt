package com.challenge.data.mapper

import com.challenge.model.City
import com.challenge.model.Country
import com.challenge.model.Node
import com.challenge.network.model.CityResponse
import com.challenge.network.model.CountryResponse
import com.challenge.network.model.LightningResponse
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val DECIMAL_PATTERN = "0.00000000"
private const val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"
private const val DATE_TIME_BR_PATTERN = "dd/MM/yyyy HH:mm:ss"
private const val DATE_TIME_DEFAULT_PATTERN = "0000-00-00 00:00:00"

internal fun List<LightningResponse>.mapToNodes(): List<Node> = map { it.mapToNode() }

internal fun LightningResponse.mapToNode(): Node =
    Node(
        alias = alias,
        capacity = capacity?.convertToBTC(isBR = country?.ptBR.isNullOrEmpty().not()),
        channels = channels,
        city = city?.mapToCity(),
        country = country?.mapToCountry(),
        firstSeen = firstSeen.toString()
            .convertToUnixTime(isBR = country?.ptBR.isNullOrEmpty().not()),
        publicKey = publicKey,
        updatedAt = updatedAt.toString()
            .convertToUnixTime(isBR = country?.ptBR.isNullOrEmpty().not())
    )


internal fun CityResponse.mapToCity(): City =
    City(
        de = de,
        en = en,
        es = es,
        fr = fr,
        ja = ja,
        ptBR = ptBR,
        ru = ru
    )

internal fun CountryResponse.mapToCountry(): Country =
    Country(
        de = de,
        en = en,
        es = es,
        fr = fr,
        ja = ja,
        ptBR = ptBR,
        ru = ru,
        zhCN = zhCN
    )

private fun Long.convertToBTC(isBR: Boolean = false): String {
    val btc = this / 100000000.0
    val decimalFormat = DecimalFormat(DECIMAL_PATTERN, DecimalFormatSymbols(Locale.getDefault()))
    decimalFormat.decimalFormatSymbols = if (isBR) DecimalFormatSymbols(Locale("pt", "BR"))
    else DecimalFormatSymbols(Locale.US)
    return decimalFormat.format(btc)
}

private fun String?.convertToUnixTime(isBR: Boolean = false): String {
    if (this == null) return DATE_TIME_DEFAULT_PATTERN
    return try {
        val unixTime = this.toLong()
        val pattern = if (isBR) DATE_TIME_BR_PATTERN else DATE_TIME_PATTERN
        val date = Date(unixTime * 1000L)
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.format(date)
    } catch (e: NumberFormatException) {
        DATE_TIME_DEFAULT_PATTERN
    }
}