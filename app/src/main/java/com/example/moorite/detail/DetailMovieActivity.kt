package com.example.moorite.detail

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.core.data.Resource
import com.example.moorite.R
import com.example.core.domain.model.Movie
import com.example.moorite.databinding.ActivityDetailMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {
    private var _binding: ActivityDetailMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailMovieViewModel by viewModel()
    private var menu: Menu? = null

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE_ID)
            if (movieId != null) {

                binding.progressBar.visibility = View.VISIBLE
                viewModel.setSelectedMovie(movieId)

                viewModel.detailMovie.observe(this, { detailMovieResource ->
                    if (detailMovieResource != null) {
                        when (detailMovieResource) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> if (detailMovieResource.data != null) {
                                binding.progressBar.visibility = View.GONE
                                setMovie(detailMovieResource.data!!)
                            }
                            is Resource.Error -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }

    }

    private fun setMovie(movie: Movie){
        binding.txtMovieTitle.text = movie.movieTitle
        binding.txtMovieRelease.text = movie.movieRelease
        binding.txtMovieSynopsis.text = movie.movieSynopsis
        binding.txtMovieScore.text = movie.movieScore.toString()

        val foto = "https://image.tmdb.org/t/p/w342/" + movie.moviePoster
        Glide.with(this)
            .load(foto)
            .apply(RequestOptions().override(300, 600))
            .into(binding.imgMoviePoster)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.detailMovie.observe(this, { detailMovieResource ->
            if (detailMovieResource != null) {
                when (detailMovieResource) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> if (detailMovieResource.data != null) {
                        binding.progressBar.visibility = View.GONE
                        val state = detailMovieResource.data!!.movieFavorite
                        Log.d(ContentValues.TAG,"value dari state adalah $state")
                        setFavoriteState(state)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_fav) {
            viewModel.addAndDeleteFav()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_fav)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_unfavorite)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}