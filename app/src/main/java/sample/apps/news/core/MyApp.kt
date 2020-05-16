package sample.apps.news.core

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid
import sample.apps.news.di.*

class MyApp : Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        instantiateDagger()
        JodaTimeAndroid.init(this)
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }

    fun instantiateDagger() {
        appComponent = DaggerAppComponent
            .builder()
            .myModule(MyModule(this))
            .retrofitModule(RetrofitModule())
            .repositoryModule(RepositoryModule())
            .build()
    }

}