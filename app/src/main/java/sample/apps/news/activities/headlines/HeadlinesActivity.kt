package sample.apps.news.activities.headline

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_headlines.*
import kotlinx.android.synthetic.main.content_main.*
import sample.apps.news.R
import sample.apps.news.activities.details.DetailNewsActivity
import sample.apps.news.activities.details.DetailNewsActivity.Companion.EXTRA_ARTICLE
import sample.apps.news.activities.headlines.HeadlinesAdapter
import sample.apps.news.activities.headlines.HeadlinesViewModelFactory
import sample.apps.news.activities.headlines.HeadlinewViewModel
import sample.apps.news.core.BaseActivity
import sample.apps.news.database.ArticleDatabase
import sample.apps.news.entity.Article
import sample.apps.news.utils.MYTAG
import javax.inject.Inject

class HeadlinesActivity : BaseActivity() {

    private var headlinesAdapter: HeadlinesAdapter? = null

    @Inject
    lateinit var viewModelFactory: HeadlinesViewModelFactory

    @Inject
    lateinit var dataBaseInstance: ArticleDatabase

    private var viewModel: HeadlinewViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headlines)
        setSupportActionBar(toolbar)

        appComponent?.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(HeadlinewViewModel::class.java)
        viewModel?.setInstanceOfDb(dataBaseInstance)
        initViews()
        viewModel?.getLiveData()?.observe(this,
            Observer { items ->
                if (!items.isNullOrEmpty()) {
                    headlinesAdapter?.submitList(items)
                }
            })
        getDataFromAPI()
    }

    private fun initViews() {
        headlinesAdapter = HeadlinesAdapter(object : HeadlinesAdapter.ClickHandler {
            override fun itemClick(article: Article) {
                openArticle(article)
            }
        })
        headlines_recyclerview.adapter = headlinesAdapter
    }

    fun openArticle(it: Article) {
        Log.e(
            MYTAG,
            this.javaClass.name + ": openArticle(): ${it.title}"
        )

        val intent = Intent(this, DetailNewsActivity::class.java)
        intent.putExtra(EXTRA_ARTICLE, it)
        startActivity(intent)
    }

    private fun getDataFromAPI() {
        viewModel?.getArticlesFromAPI()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }
}
