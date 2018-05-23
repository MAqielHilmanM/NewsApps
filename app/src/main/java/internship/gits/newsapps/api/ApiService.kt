package internship.gits.newsapps.api

import internship.gits.newsapps.api.dao.NewsApiDao
import internship.gits.newsapps.util.Constant
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    fun getTopNews(
            @Query("country") country:String,
            @Query("apikey") apikey:String
    ) : Observable<NewsApiDao>

    companion object Factory {

        fun create():ApiService{

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl(Constant.BASE_URL)
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}