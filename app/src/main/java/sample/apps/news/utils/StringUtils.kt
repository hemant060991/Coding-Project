package sample.apps.news.utils

import android.view.View
import android.widget.TextView

fun setTextToTextView(textView: TextView?, data: String?) {
    if (textView != null && !data.isNullOrBlank()) {
        textView.text = data
        textView.visibility = View.VISIBLE
        return
    }
    textView?.visibility = View.GONE
}