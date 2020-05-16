package sample.apps.news.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import sample.apps.news.di.AppComponent

open class BaseActivity : AppCompatActivity() {

    var appComponent: AppComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = (application as MyApp).getAppComponent()
    }
}