package com.moviedb.di.module

import android.content.Context
import com.moviedb.data.remote.api.MovieApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moviedb.R
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideRetrofit(context: Context, gson: Gson?, okHttpClient: OkHttpClient?): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(context.getString(R.string.base_url))
            .client(okHttpClient)
            .build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}