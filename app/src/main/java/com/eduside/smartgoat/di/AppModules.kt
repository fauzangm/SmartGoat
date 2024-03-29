package com.eduside.smartgoat.di

import android.content.Context
import com.eduside.smartgoat.BuildConfig.BASE_URL
import com.eduside.smartgoat.BuildConfig.DEBUG
import com.eduside.smartgoat.data.local.sp.SessionLogin
import com.eduside.smartgoat.data.remote.ApiServices
import com.eduside.smartgoat.data.remote.authentication.AccessTokenAuthenticator
import com.eduside.smartgoat.data.remote.authentication.AccessTokenInterceptor
import com.eduside.smartgoat.data.remote.authentication.AccessTokenProvider
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModules {
    private const val CONNECT_TIMEOUT: Long = 30
    private const val READ_TIMEOUT: Long = 30
    private const val WRITE_TIMEOUT: Long = 30

    @Provides
    @Singleton
    fun provideApiService(@ApplicationContext _context: Context): ApiServices {
        val apiServices: ApiServices
        val client = OkHttpClient.Builder()
        val sessionManager = SessionLogin(_context)
        val accessTokenProvider = AccessTokenProvider(sessionManager)

        if (DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(interceptor)
        }
        client
            .addInterceptor(AccessTokenInterceptor(accessTokenProvider))
            .authenticator(AccessTokenAuthenticator(accessTokenProvider))
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .cache(null)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(StethoInterceptor())
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()

        apiServices = retrofit.create(ApiServices::class.java)
        return apiServices
    }
}