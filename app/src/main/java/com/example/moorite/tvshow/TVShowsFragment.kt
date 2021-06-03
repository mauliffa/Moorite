package com.example.moorite.tvshow

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moorite.R
import com.example.moorite.databinding.FragmentTvShowsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TVShowsFragment : Fragment() {

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TVShowViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val tvShowAdapter = TVShowAdapter()
            viewModel.getTV().observe(viewLifecycleOwner, { tv ->
                if (tv != null) {
                    when (tv) {
                        is com.example.core.data.Resource.Loading -> {
                            Log.d(ContentValues.TAG,"Observe loading")
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is com.example.core.data.Resource.Success -> {
                            Log.d(ContentValues.TAG,"Observe success")
                            binding.progressBar.visibility = View.GONE
                            tvShowAdapter.setData(tv.data)
                        }
                        is com.example.core.data.Resource.Error -> {
                            Log.d(ContentValues.TAG,"Observe error")
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = tv.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvTvshow) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}