package internship.gits.newsapps.newsdetail

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import internship.gits.newsapps.R
import internship.gits.newsapps.util.obtainViewModel
import internship.gits.newsapps.util.replaceFragmentInActivity
import internship.gits.newsapps.util.setupActionBar
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.widget.Toast
import android.view.WindowManager
import android.os.Build
import android.support.v7.widget.Toolbar
import android.view.View
import internship.gits.newsapps.R.id.toolbar




class DetailActivity : AppCompatActivity(), DetailUserActionListener {

    private lateinit var mActivity: AppCompatActivity
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_act)

        setupToolbar()
        setupFragment()
        setupViewModel()

        mActivity = this
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel().apply{
            openBrowser.observe(this@DetailActivity, Observer{ browser ->
                    onClickMoreInfo(browser!!)
            })
        }
    }

    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.frameNews)
        DetailFragment.newInstance(intent.extras).let {
            replaceFragmentInActivity(it, R.id.frameNews)
        }
    }
    override fun onClickMoreInfo(url: String) {
        var uri : String
        uri = url
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            uri = "http://" + url;
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(browserIntent)
    }

    private fun setupToolbar() {
        setupActionBar(R.id.toolbar){
            setTitle("")
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        /*
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        // Toolbar :: Transparent
        toolbar.setBackgroundColor(Color.TRANSPARENT)

        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Status bar :: Transparent
        val window = this.window

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
        }*/
    }

    fun obtainViewModel(): DetailViewModel = obtainViewModel(DetailViewModel::class.java)

    companion object {

        const val EXTRA_PARCELABLE = "NEWS_DATA"

    }
}