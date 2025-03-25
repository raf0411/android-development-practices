package kalbe.corp.wishlistapp

import android.content.Context
import androidx.room.Room
import kalbe.corp.wishlistapp.data.WishDB
import kalbe.corp.wishlistapp.data.WishRepository

// This is Dependency Injection
// A Singleton -> Only one graph in our entire App
object Graph {
    lateinit var database: WishDB

    // lazy -> code will only be executed when it first accessed (saves resources)
    val wishRepository by lazy {
        WishRepository(wishDAO = database.wishDAO())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDB::class.java, "wishlist.db").build()
    }
}