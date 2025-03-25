package kalbe.corp.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "wish-title") var title: String = "",
    @ColumnInfo(name = "wish-desc") var description: String = "",
    @ColumnInfo(name = "wish-created-at") val createdAt: String = SimpleDateFormat(
        "MMMM d, yyyy", Locale.ENGLISH
    ).format(Date()).toString(),
)

object DummyDataWish {
    val wishlist: List<Wish> = listOf(
        Wish(
            title = "Google Watch Pixel 2",
            description = "An android watch that is absolutely useless for me."
        ),
        Wish(
            title = "Samsung S17",
            description = "An android phone that is absolutely useless for me."
        ),
        Wish(title = "Apartment", description = "I need somewhere to live fr fr."),
    )
}