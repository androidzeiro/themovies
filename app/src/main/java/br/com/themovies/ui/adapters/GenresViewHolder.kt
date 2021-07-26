package br.com.themovies.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.themovies.R
import br.com.themovies.model.response.detailsmovie.GenreResponse
import com.google.android.material.chip.Chip


class GenresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: Chip = itemView.findViewById(R.id.chip_genres)

    fun bind(genre: GenreResponse) {
        title.text = genre.name
    }
}