package com.example.wb_7_2.data.model

import com.google.gson.annotations.SerializedName


data class SuperHeroModelData(
    @SerializedName("Name")
    val name: String,

    @SerializedName("Image")
    val image: String,

    @SerializedName("Publisher")
    val publisher: String,

    @SerializedName("Appears in")
    val appearsIn: String,

    @SerializedName("Powers")
    val powers: String?

    )
