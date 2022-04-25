package hu.bme.aut.dogspecies.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @param breedFor
 * @param breedGroup
 * @param height
 * @param id
 * @param image
 * @param lifeSpan
 * @param name
 * @param origin
 * @param referenceImageId
 * @param temperament
 * @param weight
 */
@Entity(tableName = "breed")
data class Breed (
    val breedFor: kotlin.String? = null,
    val breedGroup: kotlin.String? = null,
    val height: String? = null,
    val id: kotlin.Int,
    val image: String? = null,
    val lifeSpan: kotlin.String? = null,
    val name: kotlin.String? = null,
    val origin: kotlin.String? = null,
    val referenceImageId: kotlin.String? = null,
    val temperament: kotlin.String? = null,
    val weight: String? = null
)

fun Breed.toBreedEntity() = BreedEntity(
    id = id,
    name = name,
    breedGroup = breedGroup,
    breedFor = breedFor,
    height = height,
    image = image,
    lifeSpan = lifeSpan,
    origin = origin,
    referenceImageId = referenceImageId,
    temperament = temperament,
    weight = weight,
)
