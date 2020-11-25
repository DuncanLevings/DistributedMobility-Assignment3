package sheridan.levings.assignment3.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sheridan.levings.assignment3.domain.Flower

class DataDetailsViewModelFactory(
        private val flower: Flower,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataDetailsViewModel::class.java)) {
            return DataDetailsViewModel(flower, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
