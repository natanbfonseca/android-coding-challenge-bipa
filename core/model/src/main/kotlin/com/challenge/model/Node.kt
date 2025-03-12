package com.challenge.model

data class Node(
    val alias: String? = "",
    val capacity: String? = "",
    val channels: Int? = 0,
    val city: City? = City(),
    val country: Country? = Country(),
    val firstSeen: String? = "",
    val publicKey: String? = "",
    val updatedAt: String? = ""
)