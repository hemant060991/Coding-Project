package sample.apps.news.activities.details

import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_news.*
import kotlinx.android.synthetic.main.content_detail_news.*
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import sample.apps.news.R
import sample.apps.news.core.BaseActivity
import sample.apps.news.entity.Article
import sample.apps.news.utils.openWebBrowser
import sample.apps.news.utils.setTextToTextView


class DetailNewsActivity : BaseActivity() {

    companion object {
        const val EXTRA_ARTICLE = "extra_article"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupViews()
    }

    private fun setupViews() {
        getArticle()?.let {
            it.urlToImage?.let { url ->
                Picasso.get().load(url).into(image)
            }
            setTextToTextView(title_detail_page, it.title)
            setTextToTextView(content_detail_page, it.content)
            if (!it.author.isNullOrBlank()) {
                setTextToTextView(author_detail_page, "By ${it.author}")
            }
            it.publishedAt.let { publishDate ->
                val formatter: DateTimeFormatter =
                    DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

                val date = LocalDate.parse(publishDate, formatter)

                val newDate = date.toString(DateTimeFormat.fullDate())

                setTextToTextView(date_detail_page, "Published on $newDate")
            }
        }
    }

    fun seeNews(view: View) {
        getArticle()?.url?.let {
            openWebBrowser(this, it)
        }
    }

    private fun getArticle(): Article? =
        intent.getSerializableExtra(EXTRA_ARTICLE) as Article?

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}