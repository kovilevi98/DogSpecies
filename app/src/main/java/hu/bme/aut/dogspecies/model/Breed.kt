package hu.bme.aut.dogspecies.model

import androidx.room.ColumnInfo
import androidx.room.Entity

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
    @ColumnInfo(name = "breedFor") val breedFor: kotlin.String? = null,
    @ColumnInfo(name = "breedGroup") val breedGroup: kotlin.String? = null,
    @ColumnInfo(name = "height") val height: Height? = null,
    @ColumnInfo(name = "id") val id: kotlin.Int? = null,
    @ColumnInfo(name = "image") val image: Image? = null,
    @ColumnInfo(name = "lifeSpan") val lifeSpan: kotlin.String? = null,
    @ColumnInfo(name = "name") val name: kotlin.String? = null,
    @ColumnInfo(name = "origin") val origin: kotlin.String? = null,
    @ColumnInfo(name = "referenceImageId") val referenceImageId: kotlin.String? = null,
    @ColumnInfo(name = "temperament") val temperament: kotlin.String? = null,
    @ColumnInfo(name = "weight") val weight: Weight? = null
) {

}

