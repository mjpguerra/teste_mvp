package com.example.lojong.presentation.insight.quotes

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lojong.R
import com.example.lojong.base.BaseFragment
import com.example.lojong.base.extensions.*
import com.example.lojong.repository.remote.insight.model.Quote
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.container_insight.*


class QuotesFragment : BaseFragment(), QuotesContract.View {


    override val resLayout = R.layout.container_insight

    override val presenter by injectPresenter(this)

    private lateinit var adapter: QuoteItemsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setupRecyclerView()
        presenter.loadQuotes()
    }


    override fun setViews() {
        emptyItemsTextView?.text = getString(R.string.quotes_not_found)
    }

    override fun showLoading() {
        progressBar.isVisible = true
    }

    override fun hideLoading() {
        progressBar.isVisible = false
    }

    override fun updateAdapter(items: List<Quote>) {
        adapter.data = items
    }

    override fun onShareClick(bitmap: Bitmap?, position: Int) {
        bitmap?.let {
            saveImageToCacheStorage(it, activity!!)
            shareImage(activity!!)
        }.whenNull {
            onShareQuoteError()
        }
    }

    override fun onLoadQuotesSuccessful(items: List<Quote>) {
        updateAdapter(items)
    }

    override fun onShareQuoteError() {
        Snackbar.make(insightBaseCoordinatorLayout,getString(R.string.quotes_sharing_failed), Snackbar.LENGTH_LONG)
            .setBackgroundTint(ResourcesCompat.getColor(resources, R.color.design_default_color_error, null))
            .setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
            .show()
    }

    override fun onLoadQuotesError() {
        Snackbar.make(insightBaseCoordinatorLayout,getString(R.string.quotes_load_failed), Snackbar.LENGTH_LONG)
            .setBackgroundTint(ResourcesCompat.getColor(resources, R.color.design_default_color_error, null))
            .setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
            .show()    }

    override fun handleItemsVisibility(shouldShowItems: Boolean) {
        itemsRecyclerView?.isVisible = shouldShowItems
        emptyItemsTextView?.isVisible = !shouldShowItems
    }

    private fun setupRecyclerView(){
        adapter = QuoteItemsAdapter(context!!, this::onShareClick)
        itemsRecyclerView?.adapter = adapter
        itemsRecyclerView?.layoutManager = LinearLayoutManager(activity)
    }

}