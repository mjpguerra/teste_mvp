package com.example.lojong.presentation.insight.videos

import com.example.lojong.base.BasePresenter
import com.example.lojong.base.BaseView
import com.example.lojong.repository.remote.insight.model.Video

interface VideosContract {
    interface View : BaseView<Presenter> {
        fun setViews()
        fun showLoading()
        fun hideLoading()
        fun updateAdapter(items: List<Video>)
        fun onShareButtonClick(videoItem: Video, position: Int)
        fun onPlayVideoClick(videoItem: Video, position: Int)
        fun onLoadVideosSuccessful(items: List<Video>)
        fun onLoadVideosError()
        fun handleItemsVisibility(shouldShowItems: Boolean)
    }
    interface Presenter : BasePresenter<View> {
        fun init()
        fun loadVideos()
        fun onLoadVideosSuccessful(items: List<Video>)
        fun onLoadVideosError()
    }
}