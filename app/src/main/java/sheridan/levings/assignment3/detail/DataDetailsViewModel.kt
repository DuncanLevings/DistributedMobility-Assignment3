package sheridan.levings.assignment3.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import sheridan.levings.assignment3.domain.Flower

class DataDetailsViewModel(flowersProperty: Flower,
                           app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<Flower>()

    val selectedProperty: LiveData<Flower>
        get() = _selectedProperty

    init {
        _selectedProperty.value = flowersProperty
    }
}
