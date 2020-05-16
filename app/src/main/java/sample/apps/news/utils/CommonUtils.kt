package sample.apps.news.utils

import android.content.Context
import android.content.Intent
import android.net.Uri


// Can be replaced with WebView later
fun openWebBrowser(context: Context, url: String?) {
    url?.let {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }
}