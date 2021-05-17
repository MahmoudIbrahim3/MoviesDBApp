package com.moviedb.presentation.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviedb.R
import com.moviedb.core.entities.MovieEntity
import com.moviedb.presentation.utils.AppUtils

class MoviesRecyclerViewAdapter()
    : RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener
    private var items = ArrayList<MovieEntity>()
    val onMovieClickedLiveData = MutableLiveData<MovieEntity>()

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as MovieEntity
            onMovieClickedLiveData.postValue(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movies_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context

        val item = items[position]
        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.overview
        holder.tvDate.text = AppUtils.formatDate(context, item.release_date)

        Glide.with(context)
            .load(context.getString(R.string.base_url_image) + item.poster_path)
            .into(holder.ivThumbnail)

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = items.size

    fun addItems(items: List<MovieEntity>) {
        this.items.addAll(items)
    }

    fun getItems() = items

    fun resetItems() = items.clear()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivThumbnail: ImageView = view.findViewById(R.id.ivPosterImage)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
    }
}