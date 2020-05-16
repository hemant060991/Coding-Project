package sample.apps.news.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import sample.apps.news.database.ArticleDatabase
import javax.inject.Singleton

@Module
class MyModule(val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDatabaseInstance(context: Context): ArticleDatabase =
        ArticleDatabase.getDatabaseInstance(context)
}