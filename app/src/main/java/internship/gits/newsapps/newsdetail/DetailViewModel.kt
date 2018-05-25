package internship.gits.newsapps.newsdetail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Intent
import android.databinding.ObservableField
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.support.annotation.StringRes
import internship.gits.newsapps.data.News
import internship.gits.newsapps.data.source.NewsRepository
import internship.gits.newsapps.util.SingleLiveEvent

class DetailViewModel(application: Application, newsRepository: NewsRepository) : AndroidViewModel(application){
    val details: ObservableField<News> = ObservableField()
    internal val openBrowser = SingleLiveEvent<String>()

    fun start(bundle : Bundle){
        val news : News = bundle.getParcelable(DetailActivity.EXTRA_PARCELABLE)
        details.set(news)
    }

    fun setMoreInformation(url: String) {
        openBrowser.value = url
    }


}