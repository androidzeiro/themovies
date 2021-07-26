package br.com.themovies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import br.com.themovies.R
import br.com.themovies.model.response.upcomingmovies.ResultResponse

class MoviesAdapter(
    private val listenerBottom: () -> Unit,
    private val onClickItem: (movie: ResultResponse) -> Unit
) :
    ListAdapter<ResultResponse, MoviesViewHolder>(ItemDiffCallback()) {

    fun setList(list: List<ResultResponse>) {
        this.submitList(ArrayList(list))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        if (itemCount != 0) {
            if (position == (itemCount - 1)) {
                listenerBottom()
            }
        }
        holder.itemView.setOnClickListener {
            onClickItem.invoke(item)
        }
    }
}

private class ItemDiffCallback : DiffUtil.ItemCallback<ResultResponse>() {
    override fun areItemsTheSame(oldItem: ResultResponse, newItem: ResultResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultResponse, newItem: ResultResponse): Boolean {
        return oldItem == newItem
    }
}