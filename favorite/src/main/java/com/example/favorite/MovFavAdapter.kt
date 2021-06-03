package com.example.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.core.domain.model.Movie
import com.example.favorite.databinding.ItemFavBinding
import com.example.moorite.detail.DetailMovieActivity

class MovFavAdapter:  RecyclerView.Adapter<MovFavAdapter.FavMovieViewHolder>() {

    private var listData = ArrayList<Movie>()
    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        val itemsFavBinding = ItemFavBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavMovieViewHolder(itemsFavBinding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        val movie = listData[position]
         holder.bind(movie)
    }

    inner class FavMovieViewHolder (private val binding: ItemFavBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                txtFavTitle.text = movie.movieTitle

                val foto = "https://image.tmdb.org/t/p/w342/" + movie.moviePoster
                Glide.with(itemView.context)
                    .load(foto)
                    .apply(RequestOptions.overrideOf(200, 400))
                    .into(binding.imgFavPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movie.movieId)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}