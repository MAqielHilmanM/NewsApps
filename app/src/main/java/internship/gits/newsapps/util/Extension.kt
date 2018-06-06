package internship.gits.newsapps.util

import android.app.FragmentTransaction
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.BindingAdapter
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import java.security.cert.CertPath

fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.run {
        action()
    }
}
fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int){
    supportFragmentManager.transact {
        replace(frameId,fragment)
    }
}
private inline fun FragmentManager.transact(action: android.support.v4.app.FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}
fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, tag: String) {
    supportFragmentManager.transact {
        add(fragment, tag)
    }
}

val Context.picasso: Picasso
    get() = Picasso.get()

fun ImageView.load(path: String, request: (RequestCreator) -> RequestCreator){
    request(context.picasso.load(path)).into(this)
}
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url:String?) {
    if (url != null) {
        view.load(url){ requestCreator -> requestCreator.fit().centerCrop() }
    }
}