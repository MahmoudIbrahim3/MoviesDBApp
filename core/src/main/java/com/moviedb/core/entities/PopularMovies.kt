package com.moviedb.core.entities

data class PopularMovies (
	val page : Int,
	val results : List<MovieEntity>,
	val total_pages : Int,
	val total_results : Int
)