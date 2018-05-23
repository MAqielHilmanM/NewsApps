package internship.gits.newsapps.news

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import internship.gits.newsapps.data.News

object NewsBinding {
    @BindingAdapter("app:newsList")
    @JvmStatic
    fun setNewsList(recyclerView: RecyclerView, news: MutableList<News>) {
        with(recyclerView.adapter as NewsAdapter){
            replaceData(news)
        }
    }
}