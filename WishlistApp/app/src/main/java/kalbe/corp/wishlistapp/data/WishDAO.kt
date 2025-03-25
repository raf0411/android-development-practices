package kalbe.corp.wishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun createWish(wishEntity: Wish)

    @Delete
    abstract suspend fun deleteWish(wishEntity: Wish)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun editWish(wishEntity: Wish)

    @Query("SELECT * FROM `wish-table`")
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Query("SELECT * FROM `wish-table` WHERE id = :id")
    abstract fun getWishById(id: Long): Flow<Wish>
}
