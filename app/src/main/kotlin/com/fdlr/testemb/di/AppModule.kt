package com.fdlr.testemb.di

import com.fdlr.testemb.coin.di.coinModule
import com.fdlr.testemb.data.BuildConfig
import com.fdlr.testemb.data.repository.CoinRepositoryImpl
import com.fdlr.testemb.data.source.local.MBDatabase
import com.fdlr.testemb.data.source.remote.AuthInterceptor
import com.fdlr.testemb.data.source.remote.api.CoinApi
import com.fdlr.testemb.domain.repository.CoinRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val repositoryModule = module {
    singleOf(::CoinRepositoryImpl){ bind<CoinRepository>() }
}

private val databaseModule = module {
    single { MBDatabase.getDatabase(get()) }
    single { get<MBDatabase>().assetDao() }
    single { get<MBDatabase>().exchangeRateDao() }
}

private val networkModule = module {
    single {
        AuthInterceptor(BuildConfig.COINAPI_KEY)
    }

    single {
        HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(CoinApi::class.java)
    }
}

val appModule = listOf(
    *coinModule.toTypedArray(),
    networkModule,
    repositoryModule,
    databaseModule
)