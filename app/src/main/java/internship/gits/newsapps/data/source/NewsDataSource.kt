package internship.gits.newsapps.data.source

import internship.gits.newsapps.data.News

interface NewsDataSource {
    fun getNews(callback: GetNewsCallback)

    interface GetNewsCallback {
        fun onNewsLoaded(news: MutableList<News>?)
        fun onNotAvailable()
        fun onError(msg : String?)
    }
}