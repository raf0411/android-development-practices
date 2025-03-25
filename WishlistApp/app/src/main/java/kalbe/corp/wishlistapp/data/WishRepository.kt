package kalbe.corp.wishlistapp.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDAO: WishDAO) {
    suspend fun createWish(wish: Wish){
        wishDAO.createWish(wish)
    }

    fun getAllWishes(): Flow<List<Wish>> = wishDAO.getAllWishes()

    fun getWishById(id: Long): Flow<Wish> {
        return wishDAO.getWishById(id)
    }

    suspend fun editWish(wish: Wish){
        wishDAO.editWish(wish)
    }

    suspend fun deleteWish(wish: Wish){
        wishDAO.deleteWish(wish)
    }
}