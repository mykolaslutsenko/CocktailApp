package com.slutsenko.cocktailapp.di

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.google.gson.GsonBuilder
import com.slutsenko.cocktailapp.auth.LoginViewModel
import com.slutsenko.cocktailapp.data.db.impl.CocktailAppRoomDatabase
import com.slutsenko.cocktailapp.data.db.impl.dao.CocktailDao
import com.slutsenko.cocktailapp.data.db.impl.dao.base.BaseDao
import com.slutsenko.cocktailapp.data.db.impl.source.CocktailDbSourceImpl
import com.slutsenko.cocktailapp.data.db.source.BaseDbSource
import com.slutsenko.cocktailapp.data.db.source.CocktailDbSource
import com.slutsenko.cocktailapp.data.network.impl.deserializer.BooleanDeserializer
import com.slutsenko.cocktailapp.data.network.impl.deserializer.Iso8601DateDeserializer
import com.slutsenko.cocktailapp.data.network.impl.deserializer.model.CocktailNetModelDeserializer
import com.slutsenko.cocktailapp.data.network.impl.extension.deserializeType
import com.slutsenko.cocktailapp.data.network.impl.source.CocktailNetSourceImpl
import com.slutsenko.cocktailapp.data.network.model.cocktail.CocktailNetModel
import com.slutsenko.cocktailapp.data.network.source.CocktailNetSource
import com.slutsenko.cocktailapp.data.network.source.base.BaseNetSource
import com.slutsenko.cocktailapp.data.repository.impl.mapper.CocktailRepoModelMapper
import com.slutsenko.cocktailapp.data.repository.impl.mapper.LocalizedStringRepoModelMapper
import com.slutsenko.cocktailapp.data.repository.impl.mapper.base.BaseRepoModelMapper
import com.slutsenko.cocktailapp.data.repository.impl.source.CocktailRepositoryImpl
import com.slutsenko.cocktailapp.data.repository.source.CocktailRepository
import com.slutsenko.cocktailapp.data.repository.source.base.BaseRepository
import com.slutsenko.cocktailapp.extension.log
import com.slutsenko.cocktailapp.presentation.mapper.CocktailModelMapper
import com.slutsenko.cocktailapp.presentation.mapper.LocalizedStringModelMapper
import com.slutsenko.cocktailapp.presentation.mapper.base.BaseModelMapper
import com.slutsenko.cocktailapp.presentation.viewmodel.AboutCocktailViewModel
import com.slutsenko.cocktailapp.presentation.viewmodel.MainActivityViewModel
import com.slutsenko.cocktailapp.presentation.viewmodel.MainFragmentViewModel
import com.slutsenko.cocktailapp.presentation.viewmodel.SearchViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier


object Injector {

    private lateinit var appContext: Context

    private val baseGsonBuilder: GsonBuilder
        get() = GsonBuilder()
                .registerTypeAdapter(deserializeType<Boolean>(), BooleanDeserializer(false))
                .registerTypeAdapter(deserializeType<Date>(), Iso8601DateDeserializer())
                .setPrettyPrinting()
                .serializeNulls()

    val cocktailRetrofit by lazy {

        //val provideRepository = provideRepository<BaseRepository>(appContext)

        provideRetrofit(
                appContext,
                "https://www.thecocktaildb.com/api/json/v1/1/",
                setOf(),
                setOf(
                        GsonConverterFactory.create(
                               baseGsonBuilder
                                //register deserializer for cocktail list for only cocktail retrofit
                                        .registerTypeAdapter(
                                                deserializeType<CocktailNetModel>(),
                                                CocktailNetModelDeserializer()
                                        )
                                        .create()
                        )
                ),
                provideOkHttpClientBuilder(),
                *arrayOf(
//                        TokenInterceptor { provideRepository.token },
//                        AppVersionInterceptor(),
//                        PlatformInterceptor(),
//                        PlatformVersionInterceptor()
                )
        )
    }

    fun init(applicationContext: Context) {
        require(applicationContext is Application) { "Context must be application context" }
        appContext = applicationContext
        //init database
        CocktailAppRoomDatabase.instance(applicationContext)
    }

    class ViewModelFactory(
            val application: Application,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = (owner as? Activity)?.intent?.extras
                    ?: (owner as? Fragment)?.arguments
    ) : AbstractSavedStateViewModelFactory(
            owner,
            defaultArgs
    ) {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
        ): T {
//            return when {
//                modelClass.isAssignableFrom(MainViewModel::class.java) -> {
//                    MainViewModel(provideRepository(appContext), handle) as T
//                }
//                else -> throw NotImplementedError("Must provide viewModel for class ${modelClass.simpleName}")
//            }
            return when (modelClass) {
                MainActivityViewModel::class.java ->
                    MainActivityViewModel(application, provideRepository(appContext), provideModelMapper(appContext), handle) as T
                SearchViewModel::class.java ->
                    SearchViewModel(application, provideRepository(appContext), provideModelMapper(appContext), handle) as T
                LoginViewModel::class.java ->
                    LoginViewModel(application, provideRepository(appContext), provideModelMapper(appContext), handle) as T
                AboutCocktailViewModel::class.java ->
                    AboutCocktailViewModel(application, provideRepository(appContext), provideModelMapper(appContext), handle) as T
                MainFragmentViewModel::class.java ->
                    MainFragmentViewModel(application, provideRepository(appContext), provideModelMapper(appContext), handle) as T

                else -> throw NotImplementedError("Must provide viewModel for class ${modelClass.simpleName}")
            }
        }
    }

    inline fun <reified T : BaseRepository> provideRepository(context: Context): T {
        return when (T::class.java) {
            CocktailRepository::class.java -> CocktailRepositoryImpl(
                    provideDbDataSource(context),
                    provideNetDataSource(context),
                    provideRepoModelMapper(context)
            ) as T
            else -> throw IllegalStateException("Must provide repository for class ${T::class.java.simpleName}")
        }
    }

    inline fun <reified T : BaseDbSource> provideDbDataSource(context: Context): T {
        return when (T::class.java) {
//            CocktailDbSource::class.java -> CocktailDbSourceImpl(
//                CocktailAppRoomDatabase.instance(context).cocktailDao()
//            ) as T
            CocktailDbSource::class.java -> CocktailDbSourceImpl(provideDao(context)) as T
            else -> throw IllegalStateException("Must provide repository for class ${T::class.java.simpleName}")
        }
    }

    inline fun <reified T : BaseRepoModelMapper<*, *, *>> provideRepoModelMapper(context: Context): T {
        return when (T::class.java) {
            CocktailRepoModelMapper::class.java -> CocktailRepoModelMapper(provideNestedRepoModelMapper(context))
            else -> throw IllegalStateException("Must provide repository for class ${T::class.java.simpleName}")
        } as T
    }

    inline fun <reified T : BaseModelMapper<*, *>> provideModelMapper(context: Context): T {
        return when (T::class.java) {
            CocktailModelMapper::class.java -> CocktailModelMapper(provideNestedModelMapper(context))
            else -> throw IllegalStateException("Must provide repository for class ${T::class.java.simpleName}")
        } as T
    }

    inline fun <reified T : BaseRepoModelMapper<*, *, *>> provideNestedRepoModelMapper(context: Context): T {
        return when (T::class.java) {
            LocalizedStringRepoModelMapper::class.java -> LocalizedStringRepoModelMapper()
            else -> throw IllegalStateException("Must provide repository for class ${T::class.java.simpleName}")
        } as T

    }

    inline fun <reified T : BaseModelMapper<*, *>> provideNestedModelMapper(context: Context): T {
        return when (T::class.java) {
            LocalizedStringModelMapper::class.java -> LocalizedStringModelMapper()
            else -> throw IllegalStateException("Must provide repository for class ${T::class.java.simpleName}")
        } as T

    }

    inline fun <reified T : BaseDao> provideDao(context: Context): T {
        return when (T::class.java) {
            CocktailDao::class.java -> CocktailAppRoomDatabase.instance(context).cocktailDao()
            else -> throw IllegalStateException("Must provide repository for class ${T::class.java.simpleName}")
        } as T
    }

    private fun provideOkHttpClientBuilder(
            readTimeoutSeconds: Long = 120,
            writeTimeoutSeconds: Long = 120
    ): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
//        try {
//            val trustAllCerts = arrayOf(
//                    object : X509TrustManager {
//                        @Throws(CertificateException::class)
//                        override fun checkClientTrusted(
//                                chain: Array<X509Certificate>,
//                                authType: String
//                        ) = Unit
//
//                        @Throws(CertificateException::class)
//                        override fun checkServerTrusted(
//                                chain: Array<X509Certificate>,
//                                authType: String
//                        ) = Unit
//
//                        override fun getAcceptedIssuers(): Array<X509Certificate?> = emptyArray()
//                    }
//            )
//
//            // Install the all-trusting trust manager
//            val sslContext = SSLContext.getInstance("TLS"/*TlsVersion.TLS_1_3.javaName*//*"SSL"*/)
//            sslContext.init(null, trustAllCerts, SecureRandom())
//            // Create an ssl socket factory with our all-trusting manager
//            val sslSocketFactory = sslContext.socketFactory
//
//            builder.sslSocketFactory(sslSocketFactory, trustAllCerts.first() as X509TrustManager)
//
//        } catch (e: Exception) { // should never happen
//            e.printStackTrace()
//        }

        return builder
                .hostnameVerifier(HostnameVerifier { _, _ -> true })
                .readTimeout(readTimeoutSeconds, TimeUnit.SECONDS)
                .writeTimeout(writeTimeoutSeconds, TimeUnit.SECONDS)
    }

    inline fun <reified T : BaseNetSource> provideNetDataSource(context: Context): T {
        "LOG provideNetDataSource class = ${T::class.java.simpleName}".log
        return when (T::class.java) {
            CocktailNetSource::class.java -> CocktailNetSourceImpl(provideCocktailService()) as T
            else -> throw IllegalStateException("Must provide NetDataSource for class ${T::class.java.simpleName}")
        }
    }

    inline fun <reified T> provideCocktailService(): T {
        "LOG provideCocktailService class = ${T::class.java.simpleName}".log
        return cocktailRetrofit.create(T::class.java) as T
    }

    private fun provideRetrofit(
            context: Context,
            hostName: String,
            callAdapterFactories: Set<CallAdapter.Factory> = setOf(),
            converterFactories: Set<Converter.Factory> = setOf(),
            okHttpClientBuilder: OkHttpClient.Builder,
            vararg interceptors: Interceptor
    ): Retrofit {

        //interceptors.forEach { okHttpClientBuilder.addInterceptor(it) }

        //configureOkHttpInterceptors(context, okHttpClientBuilder)
        val builder = Retrofit.Builder()

        callAdapterFactories.forEach {
            builder.addCallAdapterFactory(it)
        }

        converterFactories.forEach {
            builder.addConverterFactory(it)
        }


        builder
                .client(okHttpClientBuilder.build())
                .baseUrl(hostName)

        return builder.build()
    }

//    fun <T : ViewModel> provideViewModelFactory(context: Context, clazz: Class<T>): ViewModelProvider.AndroidViewModelFactory {
//        return when (clazz) {
//            MainViewModel::class.java -> provideMainActivityViewModelFactory(context)
//            else -> throw IllegalStateException("Must provide factory for class ${clazz.simpleName}")
//        }
//    }
//
//    private fun provideMainActivityViewModelFactory(context: Context): MainViewModelFactory {
//        return MainViewModelFactory(
//            getApplication(context),
//            provideRepository(context),
//            provideRepository(context),
//            provideRepository(context),
//            provideRepository(context)
//        )
//    }
}