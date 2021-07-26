package br.com.themovies.ui.adapters

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.themovies.R
import br.com.themovies.extensions.loadImage
import br.com.themovies.model.response.upcomingmovies.ResultResponse
import com.google.android.material.textview.MaterialTextView


class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: MaterialTextView = itemView.findViewById(R.id.tv_title)
    private val voteAverage: MaterialTextView = itemView.findViewById(R.id.tv_vote_average)
    private val releaseDate: MaterialTextView = itemView.findViewById(R.id.tv_release_date)
    private val posterPath: ImageView = itemView.findViewById(R.id.iv_poster_path)

    fun bind(movie: ResultResponse) {
        title.text = movie.title
        voteAverage.text = movie.voteAverage.toString()
        releaseDate.text = movie.releaseDate
        posterPath.loadImage(
            context = itemView.context,
            url = movie.posterPath
        )
    }
}