package sample.apps.news.activities.headlines

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class HeadlinesViewModelFactory @Inject constructor(private val repository: HeadlinesRepository) :
    ViewModelProvider.Factory {

    @NonNull
    override fun <T : ViewModel?> create(@NonNull modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeadlinewViewModel::class.java)) {
            return HeadlinewViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
