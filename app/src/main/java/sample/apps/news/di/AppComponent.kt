package sample.apps.news.di

import dagger.Component
import sample.apps.news.activities.headline.HeadlinesActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [MyModule::class, RetrofitModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(activity: HeadlinesActivity)
}