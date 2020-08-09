package com.slutsenko.cocktailapp.presentation.extension

import androidx.annotation.MainThread
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner
import com.slutsenko.cocktailapp.di.Injector
import com.slutsenko.cocktailapp.presentation.ui.base.BaseActivity
import com.slutsenko.cocktailapp.presentation.ui.base.BaseFragment
import com.slutsenko.cocktailapp.presentation.ui.base.BaseViewModel


//region Activity
@MainThread
inline fun <reified ViewModel : BaseViewModel, reified DataBinding: ViewDataBinding> BaseActivity<ViewModel, DataBinding>.viewModels(
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<ViewModel> {
    val factoryPromise = factoryProducer ?: {
        Injector.ViewModelFactory(application, this)
    }

    return ViewModelLazy(ViewModel::class, { viewModelStore }, factoryPromise)
}

@MainThread
fun <ViewModel: BaseViewModel, DataBinding:ViewDataBinding> BaseActivity<ViewModel, DataBinding>.baseViewModels(
    factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<ViewModel> {
    val factoryPromise = factoryProducer ?: {
        Injector.ViewModelFactory(application, this)
    }
    return ViewModelLazy(viewModelClass, { viewModelStore }, factoryPromise)
}
//endregion

//region Fragment
@MainThread
inline fun <reified ViewModel : BaseViewModel> BaseFragment<ViewModel>.viewModels(
        owner: SavedStateRegistryOwner = this,
        saveStateOwner: SavedStateRegistryOwner = this,
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<ViewModel> {
    val factoryPromise = factoryProducer ?: {
        Injector.ViewModelFactory(requireActivity().application, saveStateOwner)
    }

    return ViewModelLazy(ViewModel::class, { viewModelStore }, factoryPromise)
}

@MainThread
fun <ViewModel: BaseViewModel> BaseFragment<ViewModel>.baseViewModels(
        owner: ViewModelStoreOwner = this,
        saveStateOwner: SavedStateRegistryOwner = this,
        factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<ViewModel> {
    val factoryPromise = factoryProducer ?: {
        Injector.ViewModelFactory(
                application = requireActivity().application,
                owner = saveStateOwner)
    }

    return ViewModelLazy(viewModelClass, { viewModelStore }, factoryPromise)
}
//endregion