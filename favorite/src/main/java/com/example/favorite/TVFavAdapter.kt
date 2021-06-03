package com.example.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.core.domain.model.TVShow
import com.example.favorite.databinding.ItemFavBinding
import com.example.moorite.detail.DetailTVShowActivity

class TVFavAdapter: RecyclerView.Adapter<TVFavAdapter.FavTVShowViewHolder>() {

    private var listData = ArrayList<TVShow>()
    fun setData(newListData: List<TVShow>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTVShowViewHolder {
        val itemsFavBinding = ItemFavBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavTVShowViewHolder(itemsFavBinding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: FavTVShowViewHolder, position: Int) {
        val tv = listData[position]
        holder.bind(tv)
    }

    inner class FavTVShowViewHolder (private val binding: ItemFavBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TVShow) {
            with(binding) {
                txtFavTitle.text = tv.tvTitle

                val foto = "https://image.tmdb.org/t/p/w342/" + tv.tvPoster
                Glide.with(itemView.context)
                    .load(foto)
                    .apply(RequestOptions.overrideOf(200, 400))
                    .into(binding.imgFavPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTVShowActivity::class.java)
                    intent.putExtra(DetailTVShowActivity.EXTRA_TVSHOW_ID, tv.tvId)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}