package com.example.lojong.presentation.insight.articles

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lojong.R
import com.example.lojong.base.BaseFragment
import com.example.lojong.base.extensions.injectPresenter
import com.example.lojong.base.extensions.isVisible
import com.example.lojong.repository.remote.insight.model.Article
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.container_insight.*

class ArticlesFragment : BaseFragment(), ArticlesContract.View {
    override val resLayout = R.layout.container_insight
    override val presenter by injectPresenter(this)

    private lateinit var adapter: ArticleItemsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setupRecyclerView()
        presenter.loadArticles()
    }

    override fun setViews() {
        emptyItemsTextView?.text = getString(R.string.articles_not_found)
    }

    override fun handleItemsVisibility(shouldShowItems: Boolean) {
        itemsRecyclerView?.isVisible = shouldShowItems
        emptyItemsTextView?.isVisible = !shouldShowItems
    }

    override fun hideLoading() {
        progressBar.isVisible = false
    }

    override fun showLoading() {
        progressBar.isVisible = true
    }

    override fun onLoadArticlesError() {
        Snackbar.make(insightBaseCoordinatorLayout,getString(R.string.articles_load_failed), Snackbar.LENGTH_LONG)
            .setBackgroundTint(ResourcesCompat.getColor(resources, R.color.design_default_color_error, null))
            .setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
            .show()
    }

    override fun onLoadArticlesSuccessful(items: List<Article>) {
        updateAdapter(items)
    }

    override fun onShareClick(articleItem: Article, position: Int) {
        val shareBody = articleItem.text + "\n" + articleItem.url
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

    override fun updateAdapter(items: List<Article>) {
        adapter.data = items
    }

    private fun setupRecyclerView(){
        adapter = ArticleItemsAdapter(this::onShareClick)
        itemsRecyclerView?.adapter = adapter
        itemsRecyclerView?.layoutManager = LinearLayoutManager(activity)
    }
}