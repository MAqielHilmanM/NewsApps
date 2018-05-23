package internship.gits.newsapps.news

import internship.gits.newsapps.data.News

interface NewsItemUserActionListener {
    fun onNewsClicked(news: News)
}