package internship.gits.newsapps.data.source.remote

import internship.gits.newsapps.api.ApiService
import internship.gits.newsapps.api.dao.NewsApiDao
//import internship.gits.newsapps.data.Artikel
import internship.gits.newsapps.data.News
//import internship.gits.newsapps.data.Source
import internship.gits.newsapps.data.source.NewsDataSource
import internship.gits.newsapps.util.Constant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object NewsRemoteDataSource : NewsDataSource {
    private val apiService = ApiService.create();
    override fun getNews(callback: NewsDataSource.GetNewsCallback) {
        apiService.getTopNews("id", Constant.BASE_APIKEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ results ->
                    run {
                        if (results.totalResults != 0) {
                            val listNews: MutableList<News> = mutableListOf<News>()
                            for (items: NewsApiDao.ArtikelDao in results.articles!!) {
                                val news: News = News()
                                news.author = items.author
                                news.description = items.description
                                val listSparator : List<String> = items.publishedAt!!.split("T")
                                news.publishedAt = listSparator.get(0)
                                news.sourceId = items.source!!.id
                                news.sourceName = items.source!!.name
                                news.title = items.title
                                news.url = items.url
                                news.urlToImage = items.urlToImage

                                listNews.add(news)
                            }

                            /*
                            news.status = results.status
                            news.totalResults = results.totalResults

                            for (items: NewsApiDao.ArtikelDao in results.articles!!){
                                val artikel : Artikel = Artikel()
                                artikel.author = items.author
                                artikel.description = items.description
                                artikel.publishedAt = items.publishedAt
                                artikel.title = items.title
                                artikel.url = items.url
                                artikel.urlToImage = items.urlToImage

                                val source : Source = Source()
                                source.id = items.source!!.id
                                source.name = items.source!!.name

                                artikel.source = source

                                news.articles!!.add(artikel)
                            }

                            callback.onNewsLoaded(news)
                         */

                            callback.onNewsLoaded(listNews)
                        } else {
                            callback.onNotAvailable()
                        }
                    }
                }, { error ->
                    callback.onError(error.message)
                })
    }

}