package com.example.lojong.presentation.insight.articles

import android.view.View
import com.example.lojong.R
import com.example.lojong.base.BaseAdapter
import com.example.lojong.base.extensions.loadImage
import com.example.lojong.repository.remote.insight.model.Article
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleItemsAdapter(
    private val shareAction: (Article, Int) -> Unit): BaseAdapter<Article>() {

    override val layoutResource = R.layout.item_article

    override fun bind(itemView: View, item: Article, position: Int) {
        itemView.articleTitle.text = item.title
        itemView.articleImage.loadImage(item.image_url, false, R.drawable.ic_no_image_found)
        itemView.articleDescription.text = item.text
        itemView.shareArticleButton.setOnClickListener { shareAction.invoke(item, position) }
    }

}