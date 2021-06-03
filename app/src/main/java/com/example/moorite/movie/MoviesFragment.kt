package com.example.moorite.movie

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moorite.R
import com.example.moorite.databinding.FragmentMoviesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val movieAdapter = MovieAdapter()
            viewModel.getMovie().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is com.example.core.data.Resource.Loading -> {
                            Log.d(TAG,"Observe loading")
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is com.example.core.data.Resource.Success -> {
                            Log.d(TAG,"Observe success")
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.setData(movies.data)
                        }
                        is com.example.core.data.Resource.Error -> {
                            Log.d(TAG,"Observe error")
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = movies.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvMovie) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}