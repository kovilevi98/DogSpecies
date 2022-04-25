package hu.bme.aut.dogspecies.model

import androidx.room.Entity

/**
 *
 * @param imperial
 * @param metric
 */
@Entity
data class Height (
    val imperial: kotlin.String? = null,
    val metric: kotlin.String? = null,
) {

}