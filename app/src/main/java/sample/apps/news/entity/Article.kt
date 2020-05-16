package sample.apps.news.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import sample.apps.news.utils.ARTICLE_TABLE_NAME
import java.io.Serializable

@Entity(tableName = ARTICLE_TABLE_NAME, indices = arrayOf(Index(value = ["url"], unique = true)))
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    @ColumnInfo(name = "url")
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
) : Serializable

data class Source(
    val id: String?,
    val name: String?
) : Serializable