package sample.apps.news.activities.headlines

import io.reactivex.Single
import sample.apps.news.api.ApiInterface
import sample.apps.news.entity.ApiObject
import javax.inject.Singleton

@Singleton
class HeadlinesRepository(val apiInterface: ApiInterface) {

    fun getNewHeadlines(): Single<ApiObject> {
        return apiInterface.getArticles()
    }

}