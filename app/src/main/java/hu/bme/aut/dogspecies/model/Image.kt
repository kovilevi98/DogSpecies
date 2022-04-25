package hu.bme.aut.dogspecies.model

import androidx.room.Entity

/**
 *
 * @param height
 * @param id
 * @param url
 * @param width
 */
@Entity
data class Image (
    val height: kotlin.Int? = null,
    val id: kotlin.String? = null,
    val url: kotlin.String? = null,
    val width: kotlin.Int? = null,
) {

}