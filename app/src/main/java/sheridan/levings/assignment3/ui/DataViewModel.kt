package sheridan.levings.assignment3.ui

import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import sheridan.levings.assignment3.domain.Flower
import sheridan.levings.assignment3.network.FlowerJson
import sheridan.levings.assignment3.network.FlowerDataApi

class DataViewModel : ViewModel() {

    private var flowerListData: LiveData<List<Flower>>? = null

    private val _navigateToSelectedProperty = MutableLiveData<Flower>()
    val navigateToSelectedProperty: LiveData<Flower>
        get() = _navigateToSelectedProperty

    fun getFlowers(): LiveData<List<Flower>> {
        return flowerListData ?: liveData {
            val catalog = FlowerDataApi.retrofitService.getCatalog()
            val flowers = catalog.flowers.mapIndexed { index, flowerJson ->
                flowerJson.asFlower(index)
            }
            emit(flowers)
        }.also {
            flowerListData = it
        }
    }

    fun displayFlowerDetails(flower: Flower) {
        _navigateToSelectedProperty.value = flower
    }

    fun displayFlowerDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}

fun FlowerJson.asFlower(index: Int): Flower{
    return Flower(label, text, price, pictures.large, pictures.small, index.toLong())
}