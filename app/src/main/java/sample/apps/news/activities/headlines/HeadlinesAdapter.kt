package sample.apps.news.activities.headlines

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_headlines.view.*
import sample.apps.news.R
import sample.apps.news.entity.Article
import sample.apps.news.utils.MYTAG
import sample.apps.news.utils.setTextToTextView

class HeadlinesAdapter(private val clickHandler: ClickHandler) :
    ListAdapter<Article, HeadlinesAdapter.DataViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_headlines, parent, false),
            clickHandler
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

    inner class DataViewHolder(
        itemView: View, private val clickHandler: ClickHandler
    ) : RecyclerView.ViewHolder(itemView) {
        fun setData(data: Article) {
            itemView.apply {
                setTextToTextView(headline_title, data.title)
                setTextToTextView(heading_description, data.description)
                if (!data.author.isNullOrBlank()) {
                    setTextToTextView(author, "By ${data.author}")
                }
                data.urlToImage?.let {
                    Picasso.get().load(it).into(thumbnail)
                }
            }
            itemView.setOnClickListener {
                Log.i(MYTAG, this.javaClass.name + ": clickListener: ${data.title}")
                clickHandler.itemClick(data)
            }
        }
    }


    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Article> =
            object : DiffUtil.ItemCallback<Article>() {
                override fun areItemsTheSame(
                    oldItem: Article,
                    newItem: Article
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Article,
                    newItem: Article
                ): Boolean {
                    return oldItem.id == newItem.id &&
                            oldItem.title == newItem.title &&
                            oldItem.content == newItem.content
                }
            }
    }

    interface ClickHandler {
        fun itemClick(article: Article)
    }

}