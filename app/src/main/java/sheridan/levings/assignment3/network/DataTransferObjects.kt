package sheridan.levings.assignment3.network

import sheridan.levings.assignment3.database.DatabaseFlower
import sheridan.levings.assignment3.domain.Flower
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkFlowerContainer(val flowers: List<Flower>)

@JsonClass(generateAdapter = true)
data class NetworkFlower(
        val id: Long = 0L,
        val label: String,
        val text: String,
        var price: String,
        val picture: String,
        val pictureSmall: String)

fun NetworkFlowerContainer.asDomainModel(): List<Flower> {
    return flowers.map {
        Flower(
                id = it.id,
                label = it.label,
                text = it.text,
                price = it.price,
                picture = it.picture,
                pictureSmall = it.pictureSmall)
    }
}

fun NetworkFlowerContainer.asDatabaseModel(): List<DatabaseFlower> {
    return flowers.map {
        DatabaseFlower(
                id = it.id,
                label = it.label,
                text = it.text,
                price = it.price,
                picture = it.picture,
                pictureSmall = it.pictureSmall)
    }
}

