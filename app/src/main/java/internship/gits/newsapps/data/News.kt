package internship.gits.newsapps.data

import android.os.Parcel
import android.os.Parcelable

/*
data class News(
        var status: String?=null,
        var totalResults: Int?=null,
        var articles: MutableList<Artikel>?=null
)
data class Artikel(
        var source: Source? = null,
        var author: String? = null,
        var title: String? = null,
        var description: String? = null,
        var url: String? = null,
        var urlToImage: String? = null,
        var publishedAt: String? = null
)
data class Source(
        var id: String? = null,
        var name: String? = null
)
*/

class News(
        var author: String? = null,
        var title: String? = null,
        var description: String? = null,
        var url: String? = null,
        var urlToImage: String? = null,
        var publishedAt: String? = null,
        var sourceId: String? = null,
        var sourceName: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
        parcel.writeString(sourceId)
        parcel.writeString(sourceName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}