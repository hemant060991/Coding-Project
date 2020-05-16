package sample.apps.news.di

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import sample.apps.news.activities.headlines.HeadlinesRepository
import sample.apps.news.activities.headlines.HeadlinesViewModelFactory
import sample.apps.news.api.ApiInterface
import javax.inject.Singleton

@Module
class RepositoryModule() {

    @Provides
    @Singleton
    fun provideHeadlinesRepository(apiInterface: ApiInterface): HeadlinesRepository =
        HeadlinesRepository(apiInterface)

    @Provides
    @Singleton
    fun getHeadlinesViewModelFactory(myRepository: HeadlinesRepository): ViewModelProvider.Factory? {
        return HeadlinesViewModelFactory(
            myRepository
        )
    }

}