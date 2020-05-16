package sample.apps.news.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import sample.apps.news.entity.Article
import sample.apps.news.utils.ARTICLE_TABLE_NAME

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertArticle(data: Article): Completable

    @Query("SELECT * FROM $ARTICLE_TABLE_NAME")
    fun getAllArticles(): LiveData<List<Article>>

}