package com.example.lojong.presentation.insight.videos

import com.example.lojong.base.extensions.launchIO
import com.example.lojong.base.extensions.launchMain
import com.example.lojong.repository.Repository
import com.example.lojong.repository.remote.insight.model.Video
import com.example.lojong.repository.remote.insight.responses.whenever

class VideosPresenter(
    override var view: VideosContract.View,
    private val repository: Repository
) : VideosContract.Presenter {

    override fun init() {}

    override fun loadVideos() {
        view.showLoading()
        launchIO {
            repository.remote.insightResources.getVideos().whenever(
                isBody = { onLoadVideosSuccessful(it) },
                isError = { onLoadVideosError() },
                isEmptyBody = { onLoadVideosError() },
                isOk = { onLoadVideosError() }
            )
        }
    }

    override fun onLoadVideosSuccessful(items: List<Video>) {
        launchMain {
            view.handleItemsVisibility(shouldShowItems = items.isNotEmpty())
            view.onLoadVideosSuccessful(items)
            view.hideLoading()
        }
    }

    override fun onLoadVideosError() {
        launchMain {
            view.handleItemsVisibility(shouldShowItems = false)
            view.onLoadVideosError()
            view.hideLoading()
        }
    }
}