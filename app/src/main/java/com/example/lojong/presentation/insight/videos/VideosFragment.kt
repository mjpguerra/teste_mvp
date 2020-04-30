package com.example.lojong.presentation.insight.videos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lojong.R
import com.example.lojong.base.BaseFragment
import com.example.lojong.base.extensions.injectPresenter
import com.example.lojong.base.extensions.isVisible
import com.example.lojong.repository.remote.insight.model.Video
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.container_insight.*

class VideosFragment : BaseFragment(), VideosContract.View {
    override val resLayout = R.layout.container_insight
    override val presenter by injectPresenter(this)

    private lateinit var adapter: VideoItemsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setupRecyclerView()
        presenter.loadVideos()
    }

    override fun setViews() {
        emptyItemsTextView?.text = getString(R.string.videos_not_found)
    }

    override fun onLoadVideosSuccessful(items: List<Video>) {
        updateAdapter(items)
    }

    override fun handleItemsVisibility(shouldShowItems: Boolean) {
        itemsRecyclerView?.isVisible = shouldShowItems
        emptyItemsTextView?.isVisible = !shouldShowItems
    }

    override fun onLoadVideosError() {
        Snackbar.make(
            insightBaseCoordinatorLayout,
            getString(R.string.videos_load_failed),
            Snackbar.LENGTH_LONG
        ).setBackgroundTint(
            ResourcesCompat.getColor(
                resources,
                R.color.design_default_color_error,
                null
            )
        ).setTextColor(ResourcesCompat.getColor(resources, R.color.white, null)).show()
    }

    override fun showLoading() {
        progressBar.isVisible = true
    }

    override fun hideLoading() {
        progressBar.isVisible = false
    }

    override fun updateAdapter(items: List<Video>) {
        adapter.data = items
    }

    override fun onShareButtonClick(videoItem: Video, position: Int) {
        val shareBody = videoItem.description + "\n" + videoItem.url2
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(
            Intent.createChooser(
                sharingIntent,
                getString(R.string.share_by)
            )
        )
    }

    override fun onPlayVideoClick(videoItem: Video, position: Int) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse(videoItem.aws_url), "video/*")
        activity?.let { ContextCompat.startActivity(it.applicationContext, intent, null) }
    }

    private fun setupRecyclerView() {
        adapter = VideoItemsAdapter(this::onPlayVideoClick, this::onShareButtonClick)
        itemsRecyclerView?.adapter = adapter
        itemsRecyclerView?.layoutManager = LinearLayoutManager(activity)
    }
}