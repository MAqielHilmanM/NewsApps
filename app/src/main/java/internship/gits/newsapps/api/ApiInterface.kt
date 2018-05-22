package internship.gits.newsapps.api

import internship.gits.newsapps.api.dao.NewsDao
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/top-headlines")
    fun hitCountCheck(
            @Query("country") country:String,
            @Query("apikey") apikey:String
    ) : Observable<NewsDao>

}