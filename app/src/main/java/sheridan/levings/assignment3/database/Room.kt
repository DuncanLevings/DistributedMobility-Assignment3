package sheridan.levings.assignment3.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FlowerDao {
    @Query("select * from databaseflower")
    fun getFlowers(): LiveData<List<DatabaseFlower>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( flowers: List<DatabaseFlower>)
}



@Database(entities = [DatabaseFlower::class], version = 1)
abstract class FlowersDatabase: RoomDatabase() {
    abstract val flowerDao: FlowerDao
}

private lateinit var INSTANCE: FlowersDatabase

fun getDatabase(context: Context): FlowersDatabase {
    synchronized(FlowersDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                    FlowersDatabase::class.java,
                    "flowers").build()
        }
    }
    return INSTANCE
}
