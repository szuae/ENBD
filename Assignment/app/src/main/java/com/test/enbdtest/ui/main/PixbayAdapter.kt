package com.test.enbdtest.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.enbdtest.R
import com.test.enbdtest.entity.PixabayRepo
import com.test.enbdtest.extn.loadImage
import kotlinx.android.synthetic.main.image_grid_item.view.*


class PixbayAdapter (private val oncItemClick : onItemClick): RecyclerView.Adapter<PixbayAdapter.ViewHolder>() {

    private var pixabayList = mutableListOf<PixabayRepo>()

    fun update(list: List<PixabayRepo>) {
        this.pixabayList.addAll(list)
        notifyDataSetChanged()
    }

    fun clearAndUpdate(list: List<PixabayRepo>) {
        pixabayList.clear()
        update(list)
    }

    override fun getItemCount() = pixabayList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_grid_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.udpate(pixabayList[position])
    }

    inner class ViewHolder(private val view: View) :RecyclerView.ViewHolder(view) {
        fun udpate(repo:PixabayRepo){
            view.pixbayImage.loadImage(repo.previewURL)
            view.mainTitleView.text =repo.tags
            view.viewText.text = view.resources.getString(R.string.viewDetails,repo.views,repo.downloads)
            view.setOnClickListener{
                oncItemClick.onClick(repo)
            }
        }
    }

    interface onItemClick{
        fun onClick(item :PixabayRepo)
    }

}
