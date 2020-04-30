package com.example.lojong.presentation.insight.videos

import android.view.View
import com.example.lojong.R
import com.example.lojong.base.BaseAdapter
import com.example.lojong.base.extensions.loadImage
import com.example.lojong.repository.remote.insight.model.Video
import kotlinx.android.synthetic.main.item_video.view.*

class VideoItemsAdapter(
    private val playVideoAction: (Video, Int) -> Unit,
    private val shareAction: (Video, Int) -> Unit): BaseAdapter<Video>() {

    override val layoutResource = R.layout.item_video

    override fun bind(itemView: View, item: Video, position: Int) {
        itemView.videoTitle.text = item.name
        itemView.videoImage.loadImage(item.image_url, false, R.drawable.ic_no_image_found)
        itemView.playVideoButton.setOnClickListener { playVideoAction.invoke(item, position) }
        itemView.videoDescription.text = item.description
        itemView.shareVideoButton.setOnClickListener { shareAction.invoke(item, position) }
    }

}