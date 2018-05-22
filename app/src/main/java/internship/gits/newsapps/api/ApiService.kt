package internship.gits.newsapps.api

import internship.gits.newsapps.util.Constant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    fun create():ApiInterface{
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()

        return retrofit.create(ApiInterface::class.java)
    }
}