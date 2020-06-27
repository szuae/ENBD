package com.test.enbdtest.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.test.enbdtest.R
import com.test.enbdtest.ViewModelProviderFactory
import com.test.enbdtest.entity.Data
import com.test.enbdtest.entity.PixabayRepo
import com.test.enbdtest.extn.showSnackBar
import com.test.enbdtest.ui.details.DetailsActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(),PixbayAdapter.onItemClick {

    lateinit var viewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private val pixbayAdapter = PixbayAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        listen()

        recyclerViewData.adapter = pixbayAdapter
        recyclerViewData.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        swipeRefreshLayoutLayout.setOnRefreshListener {
            viewModel.getPixabayReposNext()
            swipeRefreshLayoutLayout.isRefreshing = true
        }

        showProgress()
        searchView.setQuery("apple",true)
        viewModel.getPixabayRepos("apple", 1)
        searchView.setOnQueryTextListener(DebouncingQueryTextListener(this.lifecycle){ newText ->
            showProgress()
            newText?.let {
                if (it.isEmpty()) {
                    viewModel.getPixabayRepos(it, 1)
                } else {
                    viewModel.getPixabayRepos(it, 1)
                }
            }
        })
    }

    private fun listen(){
        viewModel.getData().observe(this, Observer {
            swipeRefreshLayoutLayout.isRefreshing = false
            hideProgress()
            when (it) {
                is Data.SUCCESS -> {
                    if(viewModel.isNextPageRequest){
                        pixbayAdapter.update(it.data!!)
                    }else{
                        pixbayAdapter.clearAndUpdate(it.data!!)
                    }
                }
                is Data.ERROR -> {
                    var error = it.error ?: getString(R.string.sometingWentWrong)
                    mainRoot.showSnackBar(error,R.color.snack_red)
                }
            }
        })
    }

    private fun showProgress(){
        mainProgress.visibility = View.VISIBLE
        mainProgress.setOnClickListener { }
    }

    private fun hideProgress(){
        mainProgress.visibility = View.GONE
    }

    override fun onClick(item: PixabayRepo) {
        startActivity(DetailsActivity.getIntent(this,item))
    }

}