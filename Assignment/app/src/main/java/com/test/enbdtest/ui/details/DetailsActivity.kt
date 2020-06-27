package com.test.enbdtest.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.data.util.NetworkUtil
import com.test.enbdtest.R
import com.test.enbdtest.entity.PixabayRepo
import com.test.enbdtest.extn.loadImage
import kotlinx.android.synthetic.main.activity_details.*

private const val KEY_ARGUMENT = "arguments-in-activity"
class DetailsActivity : AppCompatActivity(){

    companion object{
        fun getIntent(context:Context,repo : PixabayRepo):Intent{
            val intent = Intent(context,DetailsActivity::class.java)
            intent.putExtra(KEY_ARGUMENT,repo)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val repo = intent?.getParcelableExtra<PixabayRepo?>(KEY_ARGUMENT)
        if(NetworkUtil(this).isInternetAvailable()){
            fullSizeImage.loadImage(repo?.largeImageURL)
        }else{
            fullSizeImage.loadImage(repo?.previewURL)
        }
        likeCount.text = getString(R.string.likeCount,repo?.likes)
        comments.text = getString(R.string.commentsCount,repo?.comments)
    }
}