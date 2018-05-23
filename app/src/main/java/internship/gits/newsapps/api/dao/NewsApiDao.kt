package internship.gits.newsapps.api.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class NewsApiDao {
    @SerializedName("status")
    @Expose
    open var status: String? = null

    @SerializedName("totalResults")
    @Expose
    open var totalResults: Int? = null

    @SerializedName("articles")
    @Expose
    open var articles: List<ArtikelDao>? = null

    open class ArtikelDao {

        @SerializedName("source")
        @Expose
        open var source: SourceDao? = null

        @SerializedName("author")
        @Expose
        open var author: String? = null

        @SerializedName("title")
        @Expose
        open var title: String? = null

        @SerializedName("description")
        @Expose
        open var description: String? = null

        @SerializedName("url")
        @Expose
        open var url: String? = null

        @SerializedName("urlToImage")
        @Expose
        open var urlToImage: String? = null

        @SerializedName("publishedAt")
        @Expose
        open var publishedAt: String? = null

        open class SourceDao {

            @SerializedName("id")
            @Expose
            open var id: String? = null


            @SerializedName("name")
            @Expose
            open var name: String? = null

        }

    }


}