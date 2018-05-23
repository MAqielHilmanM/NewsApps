package internship.gits.newsapps.data.source.local

import android.content.SharedPreferences
import android.support.annotation.VisibleForTesting
import internship.gits.newsapps.data.source.NewsDataSource

class NewsLocalDataSource private constructor(private val preference:SharedPreferences) : NewsDataSource{
    override fun getNews(callback: NewsDataSource.GetNewsCallback) {
        //NOTHING
    }

    companion object {
        private var INSTANCE: NewsLocalDataSource? = null
        @JvmStatic
        fun getInstance(preference: SharedPreferences): NewsLocalDataSource? {
            if (INSTANCE == null){
                synchronized(NewsLocalDataSource::class.java){
                    INSTANCE = NewsLocalDataSource(preference)
                }
            }
            return INSTANCE
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}