package hu.bme.aut.dogspecies.model

import com.squareup.moshi.Json

class BreedDto (
    @Json(name = "bred_for")
    val bredFor: kotlin.String?,
    @Json(name = "breed_group")
    val breedGroup: kotlin.String?,
    val height: Height,
    val id: kotlin.Int,
    val image: Image,
    @Json(name = "life_span")
    val lifeSpan: kotlin.String,
    val name: kotlin.String,
    val origin: kotlin.String?,
    @Json(name = "reference_image_id")
    val referenceImageId: kotlin.String,
    val temperament: kotlin.String?,
    val weight: Weight
)

fun BreedDto.toBreed() = Breed(
    id = id,
    name = name,
    breedGroup = breedGroup,
    breedFor = bredFor,
    height = height.imperial,
    image = image.url,
    lifeSpan = lifeSpan,
    origin = origin,
    referenceImageId = referenceImageId,
    temperament = temperament,
    weight = weight.imperial,
)