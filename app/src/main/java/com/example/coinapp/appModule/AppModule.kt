package com.example.coinapp.appModule

import android.content.Context
import androidx.room.Room
import com.example.coinapp.Network.CoinApiService
import com.example.coinapp.db.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //coins
    private var BASE_URL = "https://api.coinpaprika.com/v1/"

    @Provides
    @Singleton
    fun getRerofitServiceInstance(retrofit: Retrofit): CoinApiService {
        return retrofit.create(CoinApiService::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        Database::class.java,
        "coin_database"
    ).build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun provideCoinDao(db: Database) = db.coinDao()
//    @Provides
//    @Singleton
//    fun getdatabase(context: Context): DataBaseRoom {
//        val tempInstance = DataBaseRoom.INSTANCE
//        if (tempInstance != null) {
//            return tempInstance
//        }
//        synchronized(this) {
//            val instance = Room.databaseBuilder(
//                context.applicationContext,
//                DataBaseRoom::class.java,
//                "user_database"
//            ).build()
//            DataBaseRoom.INSTANCE = instance
//            return instance
//        }
//
//    }
}