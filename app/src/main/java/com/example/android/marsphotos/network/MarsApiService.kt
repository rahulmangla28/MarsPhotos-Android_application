package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

// Moshi builder to build and create a Moshi object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit builder to build and create a Retrofit object
// MoshiConvertorFactory convert JSON file fetched from internet to string and other relevant types
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {
     @GET("photos")  // GET is request type and /photos is endpoint
     suspend fun getPhotos() : List<MarsPhoto>
}

// A public Api object that exposes the lazy-initialized Retrofit service
object MarsApi {
    //  lazy initialization to make sure it is initialized at its first usage.
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java) // work??
    }
}