package com.example.lojong.base.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.lojong.BuildConfig
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

val Boolean.shouldShowView: Int
    get() = if (this) View.VISIBLE
    else View.GONE

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun ImageView.loadImage(imageUrl: String, circle: Boolean = false, errorPlaceholder: Int? = null) {

    val requestOptions = RequestOptions().apply {
        if (circle) circleCrop()
        if (errorPlaceholder != null) placeholder(
            AppCompatResources.getDrawable(
                context.applicationContext,
                errorPlaceholder
            )
        )
    }

    Glide.with(context.applicationContext)
        .applyDefaultRequestOptions(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
        .load(imageUrl)
        .apply(requestOptions)
        .into(this)
}


fun saveImageToCacheStorage(bitmap: Bitmap, context: Context) {
    try {
        val cachePath = File(context!!.cacheDir, "images")
        cachePath.mkdirs()
        val stream = FileOutputStream("$cachePath/image.png")
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun shareImage(context: Activity) {
    val imagePath = File(context!!.cacheDir, "images")
    val newFile = File(imagePath, "image.png")
    val contentUri =
        FileProvider.getUriForFile(context!!, BuildConfig.APPLICATION_ID + ".fileprovider", newFile)

    if (contentUri != null) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        shareIntent.setDataAndType(contentUri, context?.contentResolver?.getType(contentUri))
        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
        context.startActivity(Intent.createChooser(shareIntent, "Choose an app"))
    }
}
