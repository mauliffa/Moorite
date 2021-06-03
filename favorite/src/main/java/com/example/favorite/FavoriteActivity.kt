package com.example.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favorite.databinding.ActivityFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var movAdapter: MovFavAdapter
    private lateinit var tvAdapter: TVFavAdapter
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favModule)

        movAdapter = MovFavAdapter()
        tvAdapter = TVFavAdapter()

        binding.progressBar.visibility = View.VISIBLE

        viewModel.getFavoriteMovie().observe(this, { movies ->
            binding.progressBar.visibility = View.GONE
            movAdapter.setData(movies)
            if(movies.isEmpty()) {
                val text = getString(R.string.you_don_t_have_any_favorite_movie)
                binding.emptyMovFav.text = text
                binding.emptyMovFav.visibility = View.VISIBLE
            }  else {
                binding.emptyMovFav.visibility = View.GONE
            }
        })
        with(binding.rvFavMov) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = movAdapter
        }


        viewModel.getFavoriteTV().observe(this, { tv ->
            binding.progressBar.visibility = View.GONE
            tvAdapter.setData(tv)
            if(tv.isEmpty()) {
                val text = getString(R.string.you_don_t_have_any_favorite_tv_show)
                binding.emptyTVFav.text = text
                binding.emptyTVFav.visibility = View.VISIBLE
            } else {
                binding.emptyTVFav.visibility = View.GONE
            }
        })
        with(binding.rvFavTV) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = tvAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}