package com.test.enbdtest.extn

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message :String,colorRes :Int){
    val snackBarView = Snackbar.make(this, message , Snackbar.LENGTH_LONG)
    val view = snackBarView.view
    if(view.layoutParams is FrameLayout.LayoutParams){
        var params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
    }else{
        var params = view.layoutParams as CoordinatorLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
    }
    val textView = view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.setTextColor(Color.WHITE)
    textView.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD)
    view.setBackgroundColor(ContextCompat.getColor(context,colorRes)) // for custom background
    snackBarView.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
    snackBarView.show()
}

fun AppCompatImageView.loadImage(media_url:String?){
    val requestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
    Glide.with(context)
        .load(media_url)
        .apply(requestOptions)
        .into(this)

}

private fun getAnimationPlaceHolder(): Drawable? {
    return ColorDrawable(-0xa0a0b)
}

