package hu.bme.aut.dogspecies.model

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
data class Breed (
    val breedFor: kotlin.String? = null,
    val breedGroup: kotlin.String? = null,
    val height: Height? = null,
    val id: kotlin.Int? = null,
    val image: Image? = null,
    val lifeSpan: kotlin.String? = null,
    val name: kotlin.String? = null,
    val origin: kotlin.String? = null,
    val referenceImageId: kotlin.String? = null,
    val temperament: kotlin.String? = null,
    val weight: Weight? = null
) {

}

