package sample.apps.news.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import sample.apps.news.entity.Article
import sample.apps.news.utils.DB_NAME
import sample.apps.news.utils.DB_VERSION

@Database(entities = [Article::class], version = DB_VERSION)
@androidx.room.TypeConverters(TypeConverters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun ArticleDao(): ArticleDao

    companion object {

        private var databaseInstance: ArticleDatabase? = null

        fun getDatabaseInstance(context: Context): ArticleDatabase {
            return databaseInstance ?: synchronized(this) {
                databaseInstance ?: buildDatabaseInstance(context).also {
                    databaseInstance = it
                }
            }
        }

        private fun buildDatabaseInstance(mContext: Context) =
            Room.databaseBuilder(mContext, ArticleDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

}