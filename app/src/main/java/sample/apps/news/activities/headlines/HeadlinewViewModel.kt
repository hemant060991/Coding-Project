package sample.apps.news.activities.headlines

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sample.apps.news.database.ArticleDatabase
import sample.apps.news.entity.Article
import sample.apps.news.utils.MYTAG

class HeadlinewViewModel(val repository: HeadlinesRepository) : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    private var dataBaseInstance: ArticleDatabase? = null

    var articleList: LiveData<List<Article>>? = null

    fun setInstanceOfDb(dataBaseInstance: ArticleDatabase) {
        this.dataBaseInstance = dataBaseInstance
    }

    private fun saveDataIntoDb(data: Article) {
        dataBaseInstance?.ArticleDao()?.insertArticle(data)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                Log.i(MYTAG, "Article Added to DB")
            }, {
                Log.i(MYTAG, "Article not added to DB: " + it.message)
            })?.let {
                compositeDisposable.add(it)
            }
    }

    fun getArticlesFromAPI() {
        compositeDisposable.add(
            repository.getNewHeadlines()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { apiResponse ->
                        apiResponse?.articles?.forEach {
                            saveDataIntoDb(it)
                        }
                    },
                    {
                        Log.e(
                            MYTAG,
                            this.javaClass.name + ": Error getting data from server: ${it.message}"
                        )
                    }
                )
        )
    }

    fun getLiveData(): LiveData<List<Article>>? {

        if (articleList == null) {
            articleList = dataBaseInstance?.ArticleDao()?.getAllArticles()
        }

        return articleList
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }

}