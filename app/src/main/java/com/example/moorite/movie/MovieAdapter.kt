package com.example.moorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.core.domain.model.Movie
import com.example.moorite.databinding.ItemsBinding
import com.example.moorite.detail.DetailMovieActivity

class MovieAdapter:  RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listData = ArrayList<Movie>()
    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsBinding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsBinding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listData[position]
        holder.bind(movie)
    }

    class MovieViewHolder(private val binding: ItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                txtTitle.text = movie.movieTitle

                val foto = "https://image.tmdb.org/t/p/w342/" + movie.moviePoster
                Glide.with(itemView.context)
                    .load(foto)
                    .apply(RequestOptions.overrideOf(200, 400))
                    .into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movie.movieId)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}