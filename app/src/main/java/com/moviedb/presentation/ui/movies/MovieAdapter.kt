package com.moviedb.presentation.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviedb.R
import com.moviedb.core.entities.MovieEntity
import com.moviedb.presentation.utils.AppUtils
import com.moviedb.presentation.utils.DiffUtilCallBack

class MovieAdapter:
    PagingDataAdapter<MovieEntity, MovieAdapter.ViewHolder>(DiffUtilCallBack()) {

    val onMovieClickedLiveData = MutableLiveData<MovieEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movies_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bindView(it) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivPosterImage: ImageView = view.findViewById(R.id.ivPosterImage)
        private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        private val tvDate: TextView = view.findViewById(R.id.tvDate)
        private val tvDescription: TextView = view.findViewById(R.id.tvDescription)

        fun bindView(movieEntity: MovieEntity) {
            with(movieEntity) {
                val context = itemView.context

                tvTitle.text = title
                tvDescription.text = overview
                release_date?.let {
                    tvDate.text = AppUtils.formatDateForMovieListItem(it)
                }

                Glide.with(context)
                    .load(context.getString(R.string.base_url_image) + poster_path)
                    .into(ivPosterImage)

                itemView.setOnClickListener {
                    onMovieClickedLiveData.postValue(movieEntity)
                }
            }
        }
    }
}