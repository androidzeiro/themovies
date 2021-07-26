package br.com.themovies.extensions

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import br.com.themovies.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

fun ImageView.loadImage(
    context: Context,
    url: String? = null,
    drawable: Int? = null,
    _errorPlaceHolder: Int? = null
) {
    if (context is Activity && context.isDestroyed) {
        return
    }
    val errorPlaceHolder = _errorPlaceHolder ?: R.drawable.ic_launcher_foreground
    val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(1200) // how long the shimmering animation takes to do one full sweep
        .setBaseAlpha(0.9f) //the alpha of the underlying children
        .setHighlightAlpha(0.7f) // the shimmer alpha amount
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()

    val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }

    Glide.with(context)
        .load(url ?: drawable)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                exception: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                val throwable = Throwable(exception)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        })
        .transition(DrawableTransitionOptions.withCrossFade(500))
        .error(errorPlaceHolder)
        .placeholder(shimmerDrawable)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .into(this)
}