package com.example.moorite.detail

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moorite.R
import com.example.core.data.Resource
import com.example.core.domain.model.TVShow
import com.example.moorite.databinding.ActivityDetailTvShowBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailTVShowActivity : AppCompatActivity() {
    private var _binding: ActivityDetailTvShowBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailTVShowViewModel by viewModel()
    private var menu: Menu? = null

    companion object {
        const val EXTRA_TVSHOW_ID = "extra_tvshow_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_TVSHOW_ID)
            if (tvShowId != null) {

                binding.progressBar.visibility = View.VISIBLE
                viewModel.setSelectedTV(tvShowId)

                viewModel.detailTV.observe(this, { detailTVResource ->
                    if (detailTVResource != null) {
                        when (detailTVResource) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> if (detailTVResource.data != null) {
                                binding.progressBar.visibility = View.GONE
                                setTV(detailTVResource.data!!)
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

    private fun setTV(tvShow: TVShow){
        binding.txtTVTitle.text = tvShow.tvTitle
        binding.txtTVRelease.text = tvShow.tvRelease
        binding.txtTVSynopsis.text = tvShow.tvSynopsis
        binding.txtTVScore.text = tvShow.tvScore.toString()

        val foto = "https://image.tmdb.org/t/p/w342/" + tvShow.tvPoster
        Glide.with(this)
            .load(foto)
            .apply(RequestOptions().override(300, 600))
            .into(binding.imgTVPoster)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.detailTV.observe(this, { detailTVResource ->
            if (detailTVResource != null) {
                when (detailTVResource) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> if (detailTVResource.data != null) {
                        binding.progressBar.visibility = View.GONE
                        val state = detailTVResource.data!!.tvFavorite
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