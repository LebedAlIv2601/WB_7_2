package com.example.wb_7_2.data.utils

import com.example.wb_7_2.data.model.SuperHeroModelData
import com.example.wb_7_2.domain.model.SuperHeroModelDomain

fun SuperHeroModelData.toDomain(): SuperHeroModelDomain {
    return SuperHeroModelDomain(
        name = name,
        image = image,
        publisher = publisher,
        appearsIn = appearsIn,
        powers = powers ?: "Who knows..."
    )
}