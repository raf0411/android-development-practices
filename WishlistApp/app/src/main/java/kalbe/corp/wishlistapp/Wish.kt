package kalbe.corp.wishlistapp

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Wish(
    val id: Long = 0L,
    var title: String = "",
    var description: String = "",
    val createdAt: String = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).format(Date()).toString(),
)

object DummyDataWish{
    val wishlist: List<Wish>  = listOf(
        Wish(title = "Google Watch Pixel 2", description = "An android watch that is absolutely useless for me."),
        Wish(title = "Samsung S17", description = "An android phone that is absolutely useless for me."),
        Wish(title = "Apartment", description = "I need somewhere to live frfr."),
    )
}