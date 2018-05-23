package internship.gits.newsapps.util

import android.content.Context
import android.preference.PreferenceManager
import internship.gits.newsapps.data.source.NewsRepository
import internship.gits.newsapps.data.source.local.NewsLocalDataSource
import internship.gits.newsapps.data.source.remote.NewsRemoteDataSource

object Injection{
    fun provideNewsRepository(context: Context) = NewsRepository.getInstance(NewsRemoteDataSource,
            NewsLocalDataSource.getInstance(PreferenceManager.getDefaultSharedPreferences(context))!!)
}