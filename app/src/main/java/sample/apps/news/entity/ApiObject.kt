package sample.apps.news.entity

data class ApiObject(
    val status: String?,
    val totalResults: Int?,
    val articles: List<Article>?
)