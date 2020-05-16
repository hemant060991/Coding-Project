package sample.apps.news.api

import io.reactivex.Single
import retrofit2.http.GET
import sample.apps.news.entity.ApiObject
import sample.apps.news.utils.API_KEY

interface ApiInterface {

    @GET("top-headlines?country=us&apiKey=$API_KEY")
    fun getArticles(): Single<ApiObject>
}