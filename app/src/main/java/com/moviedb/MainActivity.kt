package com.moviedb

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.moviedb.presentation.ui.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingSupportFragmentInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() =
        dispatchingSupportFragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpActionBar()
    }

    private fun setUpActionBar() {
        setSupportActionBar(toolbar)
    }

    fun showActionBar() {
        supportActionBar?.show()
    }

    fun hideActionBar() {
        supportActionBar?.hide()
    }

    fun setActionBarTitle(title: String?, isDisplayHome: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isDisplayHome)
        supportActionBar?.title = title
        if (isDisplayHome)
            toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}