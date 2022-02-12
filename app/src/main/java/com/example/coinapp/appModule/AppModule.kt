package com.example.coinapp.appModule

import com.example.coinapp.Network.CoinApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //coins
    var BASE_URL = "https://api.coinpaprika.com/v1/"

    @Provides
    @Singleton
    fun getRerofitServiceInstance(retrofit: Retrofit):CoinApiService{
        return retrofit.create(CoinApiService::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

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