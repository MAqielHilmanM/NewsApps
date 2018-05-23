package internship.gits.newsapps.news

import android.databinding.DataBindingUtil
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import internship.gits.newsapps.R
import internship.gits.newsapps.data.News
import internship.gits.newsapps.databinding.NewsRowBinding
import internship.gits.newsapps.util.load

class NewsAdapter(private var news: MutableList<News>,private var newsViewModel: NewsViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val newsRowBinding : NewsRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.news_row,parent,false)
        return NewsRowHolder(newsRowBinding)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val datas = news[position]
        val actionListener = object : NewsItemUserActionListener{
            override fun onNewsClicked(news: News) {
                newsViewModel.openDetailNews.value = news
            }

        }
        (holder as NewsRowHolder).bindRows(datas,actionListener)
    }

    fun replaceData(news: MutableList<News>){
        setList(news)
    }

    fun setList(news: MutableList<News>){
        this.news = news
        notifyDataSetChanged()
    }

    class NewsRowHolder(binding: NewsRowBinding) : RecyclerView.ViewHolder(binding.root){
        val newsRowBinding = binding

        fun bindRows(news: News, userActionListener: NewsItemUserActionListener) {
            newsRowBinding.datas =  news
            newsRowBinding.action = userActionListener
            newsRowBinding.executePendingBindings()
            if(news.urlToImage!= null)
                newsRowBinding.ivRowNewsImage.load(news.urlToImage!!){
                    requestCreator -> requestCreator.fit().centerCrop()
                }
        }
    }
}