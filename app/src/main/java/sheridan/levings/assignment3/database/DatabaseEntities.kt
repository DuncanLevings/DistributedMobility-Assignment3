package sheridan.levings.assignment3.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import sheridan.levings.assignment3.domain.Flower

@Entity
data class DatabaseFlower constructor(
        @PrimaryKey
        val id: Long = 0L,
        val label: String,
        val text: String,
        var price: String,
        val picture: String,
        val pictureSmall: String)

fun List<DatabaseFlower>.asDomainModel(): List<Flower> {
        return map {
                Flower(
                        id = it.id,
                        label = it.label,
                        text = it.text,
                        price = it.price,
                        picture = it.picture,
                        pictureSmall = it.pictureSmall)
        }
}
