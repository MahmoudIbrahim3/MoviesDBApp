package com.moviedb.presentation.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.moviedb.MainActivity
import com.moviedb.R
import com.moviedb.di.ViewModelFactory
import com.moviedb.presentation.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SplashFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        (activity as MainActivity).hideActionBar()
        initSplashLiveData()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SplashViewModel::class.java)
    }

    private fun initSplashLiveData() {
        viewModel.splashLiveData.observe(viewLifecycleOwner, Observer {
            navigateToMoviesFragment()
        })
    }

    private fun navigateToMoviesFragment() =
        findNavController().navigate(R.id.action_splashFragment_to_moviesFragment)

}