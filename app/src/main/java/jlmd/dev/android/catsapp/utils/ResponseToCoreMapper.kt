package jlmd.dev.android.catsapp.utils

import jlmd.dev.android.catsapp.data.service.gateway.dto.CatResponse
import jlmd.dev.android.catsapp.core.model.Cat

fun CatResponse.toCat() =
    Cat(
        id = id,
        breedName = breedName,
        origin = origin,
        affectionLevel = affectionLevel,
        intelligence = intelligence,
        imageUrl = imageUrl
    )