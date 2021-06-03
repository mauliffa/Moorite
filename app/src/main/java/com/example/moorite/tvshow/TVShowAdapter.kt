package com.example.moorite.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.core.domain.model.TVShow
import com.example.moorite.databinding.ItemsBinding
import com.example.moorite.detail.DetailTVShowActivity

class TVShowAdapter:  RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {

    private var listData = ArrayList<TVShow>()
    fun setData(newListData: List<TVShow>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val itemsBinding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(itemsBinding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val tv = listData[position]
        holder.bind(tv)
    }

    class TVShowViewHolder(private val binding: ItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TVShow) {
            with(binding) {
                txtTitle.text = tvShow.tvTitle

                val foto = "https://image.tmdb.org/t/p/w342/" + tvShow.tvPoster
                Glide.with(itemView.context)
                    .load(foto)
                    .apply(RequestOptions.overrideOf(200, 400))
                    .into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTVShowActivity::class.java)
                    intent.putExtra(DetailTVShowActivity.EXTRA_TVSHOW_ID, tvShow.tvId)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }
}