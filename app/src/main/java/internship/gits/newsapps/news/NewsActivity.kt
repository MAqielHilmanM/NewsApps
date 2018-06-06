package internship.gits.newsapps.news

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Slide
import android.view.Gravity
import internship.gits.newsapps.R
import internship.gits.newsapps.data.News
import internship.gits.newsapps.newsdetail.DetailActivity
import internship.gits.newsapps.util.obtainViewModel
import internship.gits.newsapps.util.replaceFragmentInActivity
import internship.gits.newsapps.util.setupActionBar


class NewsActivity : AppCompatActivity(), NewsItemUserActionListener {

    private lateinit var mActivity: AppCompatActivity
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_act)

        setupToolbar()
        setupFragment()
        setupViewModel()
        setupTransition()
        mActivity = this
    }

    private fun setupTransition() {
        postponeEnterTransition()
        val enterTransition = Slide()
        enterTransition.duration = 500
        enterTransition.slideEdge = Gravity.RIGHT
        window.enterTransition = enterTransition

    }


    private fun setupViewModel() {
        viewModel = obtainViewModel().apply{
            openDetailNews.observe(this@NewsActivity, Observer{ news ->
                onNewsClicked(news!!)
            })
        }
    }

    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.frameNews)
        NewsFragment.newInstance().let {
            replaceFragmentInActivity(it, R.id.frameNews)
        }
    }

    private fun setupToolbar() {
        setupActionBar(R.id.toolbar){
            title = "NewsApp"
        }
    }

    override fun onNewsClicked(news : News){
        val bundle = Bundle()
        bundle.putParcelable(DetailActivity.EXTRA_PARCELABLE,news)
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtras(bundle)
        }
        startActivity(intent)

    }

    fun obtainViewModel(): NewsViewModel = obtainViewModel(NewsViewModel::class.java)


}
