package hu.bme.aut.dogspecies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds")
data class BreedEntity (
    @PrimaryKey val id: kotlin.Int,
    val breedFor: kotlin.String? = null,
    val breedGroup: kotlin.String? = null,
    val height: String? = null,
    val image: String? = null,
    val lifeSpan: kotlin.String? = null,
    val name: kotlin.String? = null,
    val origin: kotlin.String? = null,
    val referenceImageId: kotlin.String? = null,
    val temperament: kotlin.String? = null,
    val weight: String? = null
)

fun BreedEntity.toBreed() = Breed(
    id = id,
    name = name,
    breedFor = breedFor,
    breedGroup = breedGroup,
    height = height,
    image = image,
    lifeSpan = lifeSpan,
    origin = origin,
    referenceImageId = referenceImageId,
    temperament = temperament,
    weight = weight,
)