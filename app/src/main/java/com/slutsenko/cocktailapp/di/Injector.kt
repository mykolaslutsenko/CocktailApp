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
import com.slutsenko.cocktailapp.auth.LoginViewModel
import com.slutsenko.cocktailapp.data.db.impl.CocktailAppRoomDatabase
import com.slutsenko.cocktailapp.data.db.impl.dao.CocktailDao
import com.slutsenko.cocktailapp.data.db.impl.dao.base.BaseDao
import com.slutsenko.cocktailapp.data.db.impl.source.CocktailDbSourceImpl
import com.slutsenko.cocktailapp.data.db.source.BaseDbSource
import com.slutsenko.cocktailapp.data.db.source.CocktailDbSource
import com.slutsenko.cocktailapp.data.repository.impl.mapper.CocktailRepoModelMapper
import com.slutsenko.cocktailapp.data.repository.impl.mapper.LocalizedStringRepoModelMapper
import com.slutsenko.cocktailapp.data.repository.impl.mapper.base.BaseRepoModelMapper
import com.slutsenko.cocktailapp.data.repository.impl.source.CocktailRepositoryImpl
import com.slutsenko.cocktailapp.data.repository.source.CocktailRepository
import com.slutsenko.cocktailapp.data.repository.source.base.BaseRepository
import com.slutsenko.cocktailapp.presentation.mapper.CocktailModelMapper
import com.slutsenko.cocktailapp.presentation.mapper.LocalizedStringModelMapper
import com.slutsenko.cocktailapp.presentation.mapper.base.BaseModelMapper
import com.slutsenko.cocktailapp.presentation.viewmodel.AboutCocktailViewModel
import com.slutsenko.cocktailapp.presentation.viewmodel.MainActivityViewModel
import com.slutsenko.cocktailapp.presentation.viewmodel.MainFragmentViewModel
import com.slutsenko.cocktailapp.presentation.viewmodel.SearchViewModel


object Injector {

    private lateinit var appContext: Context

    /**
     * Must be called at application class or other place (as soon as app starts before activities get created)
     */
    fun init(applicationContext: Context) {
        require(applicationContext is Application) { "Context must be application context" }
        appContext = applicationContext
        //init database
        CocktailAppRoomDatabase.instance(applicationContext)
    }

    class ViewModelFactory(
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
                    MainActivityViewModel(provideRepository(appContext), provideModelMapper(appContext), handle) as T
                SearchViewModel::class.java ->
                    SearchViewModel(provideRepository(appContext), provideModelMapper(appContext), handle) as T
                LoginViewModel::class.java ->
                    LoginViewModel(provideRepository(appContext), provideModelMapper(appContext), handle) as T
                AboutCocktailViewModel::class.java ->
                    AboutCocktailViewModel(provideRepository(appContext), provideModelMapper(appContext), handle) as T
                MainFragmentViewModel::class.java ->
                    MainFragmentViewModel(provideRepository(appContext), provideModelMapper(appContext), handle) as T
                else -> throw NotImplementedError("Must provide viewModel for class ${modelClass.simpleName}")
            }
        }
    }

    inline fun <reified T : BaseRepository> provideRepository(context: Context): T {
        return when (T::class.java) {
            CocktailRepository::class.java -> CocktailRepositoryImpl(
                    provideDbDataSource(context),
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