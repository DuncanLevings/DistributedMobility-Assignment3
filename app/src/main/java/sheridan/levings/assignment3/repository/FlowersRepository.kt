package sheridan.levings.assignment3.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import sheridan.levings.assignment3.database.FlowersDatabase
import sheridan.levings.assignment3.database.asDomainModel
import sheridan.levings.assignment3.domain.Flower
import sheridan.levings.assignment3.network.FlowerDataApi
import sheridan.levings.assignment3.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlowersRepository(private val database: FlowersDatabase) {

    val flowers: LiveData<List<Flower>> = Transformations.map(database.flowerDao.getFlowers()) {
        it.asDomainModel()
    }

    suspend fun refreshFlowers() {
        withContext(Dispatchers.IO) {
            val flowers = FlowerDataApi.retrofitService.getCatalog()
//            database.flowerDao.insertAll(flowers.asDatabaseModel())
        }
    }
}
