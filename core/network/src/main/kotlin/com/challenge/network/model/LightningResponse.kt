package com.challenge.network.model


import com.google.gson.annotations.SerializedName

data class LightningResponse(
    @SerializedName("alias")
    val alias: String? = "",
    @SerializedName("capacity")
    val capacity: Long? = 0,
    @SerializedName("channels")
    val channels: Int? = 0,
    @SerializedName("city")
    val city: CityResponse? = CityResponse(),
    @SerializedName("country")
    val country: CountryResponse? = CountryResponse(),
    @SerializedName("firstSeen")
    val firstSeen: Int? = 0,
    @SerializedName("publicKey")
    val publicKey: String? = "",
    @SerializedName("updatedAt")
    val updatedAt: Int? = 0
)