package com.example.lojong.presentation.insight.quotes

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.example.lojong.R
import com.example.lojong.base.BaseAdapter
import com.example.lojong.base.enums.QuoteEnum
import com.example.lojong.base.extensions.isVisible
import com.example.lojong.repository.remote.insight.model.Quote
import kotlinx.android.synthetic.main.item_quote.view.*
import kotlinx.android.synthetic.main.item_quotes.view.quoteConstraintLayout
import kotlinx.android.synthetic.main.item_quotes.view.quoteDescription
import kotlinx.android.synthetic.main.item_quotes.view.shareQuoteButton

class QuoteItemsAdapter(private val context: Context , private val shareAction: (Bitmap, Int) -> Unit) : BaseAdapter<Quote>() {

    var quoteEnum: QuoteEnum = QuoteEnum.FIRST

    override val layoutResource = R.layout.item_quote

    override fun bind(itemView: View, item: Quote, position: Int) {

        val quoteType = quoteEnum
        val textColor = getTextColor(quoteType)

        itemView.quoteDescription.text = item.text
        itemView.quoteImage.setImageResource(getBackgroundItem(quoteType))
        itemView.quoteDescription.setTextColor(textColor)
        itemView.shareQuoteButton.background = AppCompatResources.getDrawable(context, getShareColorBackground(quoteType))

        itemView.shareQuoteButton.setOnClickListener {
            shareAction.invoke(getBitmapFromView(itemView)!!, position)
        }

        switchBackgroundEnum(quoteType)
    }

    private fun getTextColor(quoteEnum: QuoteEnum): Int {
        return ContextCompat.getColor(
            context,
            when (quoteEnum) {
                QuoteEnum.FIRST -> R.color.quote_blue_text_button
                QuoteEnum.SECOND -> R.color.quote_grey_text_button
                QuoteEnum.THIRD -> R.color.white
            }
        )
    }

    private fun getBackgroundItem(quoteEnum: QuoteEnum): Int {
        return when (quoteEnum) {
            QuoteEnum.FIRST -> R.drawable.first_quote_background
            QuoteEnum.SECOND -> R.drawable.second_quote_background
            QuoteEnum.THIRD -> R.drawable.third_quote_background
        }
    }

    private fun getShareColorBackground(quoteEnum: QuoteEnum): Int {
        return when (quoteEnum) {
            QuoteEnum.FIRST -> R.drawable.round_blue_background
            QuoteEnum.SECOND -> R.drawable.round_trans_background
            QuoteEnum.THIRD -> R.drawable.round_trans_background
        }
    }

    private fun switchBackgroundEnum(quoteType: QuoteEnum) {
        quoteEnum = when (quoteType) {
            QuoteEnum.FIRST -> QuoteEnum.SECOND
            QuoteEnum.SECOND -> QuoteEnum.THIRD
            QuoteEnum.THIRD -> QuoteEnum.FIRST
        }
    }

    private fun getBitmapFromView(view: View): Bitmap? {
        view.quoteConstraintLayout.shareQuoteButton.isVisible = false
        val returnedBitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        view.quoteConstraintLayout.shareQuoteButton.isVisible = true
        return returnedBitmap
    }
}