package br.com.themovies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import br.com.themovies.R
import br.com.themovies.model.response.detailsmovie.GenreResponse

class GenresAdapter : ListAdapter<GenreResponse, GenresViewHolder>(ItemGenreDiffCallback()) {

    fun setList(list: List<GenreResponse>) {
        this.submitList(ArrayList(list))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genres, parent, false)
        return GenresViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

private class ItemGenreDiffCallback : DiffUtil.ItemCallback<GenreResponse>() {
    override fun areItemsTheSame(oldItem: GenreResponse, newItem: GenreResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GenreResponse, newItem: GenreResponse): Boolean {
        return oldItem == newItem
    }
}