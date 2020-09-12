package com.slutsenko.cocktailapp.presentation.ui.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import com.slutsenko.cocktailapp.presentation.extension.*
import com.slutsenko.cocktailapp.presentation.ui.dialog.BaseBottomSheetDialogFragment
import com.slutsenko.cocktailapp.presentation.ui.dialog.BaseDialogFragment
import com.slutsenko.cocktailapp.presentation.ui.dialog.DialogButton
import com.slutsenko.cocktailapp.presentation.ui.dialog.DialogType
import com.slutsenko.common.Language
import java.util.*
import kotlin.reflect.KClass

abstract class BaseActivity<ViewModel : BaseViewModel, DataBinding : ViewDataBinding> : AppCompatActivity(),
        BaseDialogFragment.OnDialogFragmentClickListener<Any, DialogButton, DialogType<DialogButton>>,
        BaseDialogFragment.OnDialogFragmentDismissListener<Any, DialogButton, DialogType<DialogButton>>,
        BaseBottomSheetDialogFragment.OnBottomSheetDialogFragmentClickListener<Any, DialogButton, DialogType<DialogButton>>,
        BaseBottomSheetDialogFragment.OnBottomSheetDialogFragmentDismissListener<Any, DialogButton, DialogType<DialogButton>> {
    abstract val viewModelClass: KClass<ViewModel>

    protected  val viewModel: ViewModel by baseViewModels()

    protected open lateinit var dataBinding: DataBinding

    protected open fun configureDataBinding(binding: DataBinding) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPref = getSharedPreferences("lang", Context.MODE_PRIVATE)
        val chooseLanguage = sharedPref.getString("language", Language.ENGLISH.locale)
        val locale = Locale(chooseLanguage!!)
        Locale.setDefault(locale)
        val resources = resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N /*24*/) {
            with(LocaleList(locale)) {
                LocaleList.setDefault(this)
                configuration.setLocales(this)
            }
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)

        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, myView())!!
        dataBinding.setLifecycleOwner(this@BaseActivity)
        activityCreated()
        configureDataBinding(dataBinding)
    }

    protected abstract fun myView(): Int
    protected abstract fun activityCreated()

    @CallSuper
    override fun onDialogFragmentDismiss(
            dialog: DialogFragment,
            type: DialogType<DialogButton>,
            data: Any?
    ) {
        (dialog.parentFragment as? BaseFragment<*,*>)?.onDialogFragmentDismiss(
                dialog,
                type,
                data
        )
    }

    @CallSuper
    override fun onDialogFragmentClick(
            dialog: DialogFragment,
            buttonType: DialogButton,
            type: DialogType<DialogButton>,
            data: Any?
    ) {
        (dialog.parentFragment as? BaseFragment<*,*>)?.onDialogFragmentClick(
                dialog,
                buttonType,
                type,
                data
        )
    }

    override fun onBottomSheetDialogFragmentDismiss(
            dialog: DialogFragment,
            type: DialogType<DialogButton>,
            data: Any?
    ) {
        (dialog.parentFragment as? BaseFragment<*,*>)?.onBottomSheetDialogFragmentDismiss(
                dialog,
                type,
                data
        )
    }

    override fun onBottomSheetDialogFragmentClick(
            dialog: DialogFragment,
            buttonType: DialogButton,
            type: DialogType<DialogButton>,
            data: Any?
    ) {
        (dialog.parentFragment as? BaseFragment<*,*>)?.onBottomSheetDialogFragmentClick(
                dialog,
                buttonType,
                type,
                data
        )
    }

    @MainThread
    protected inline fun <reified T> LiveData<T>.observe(noinline observer: (T) -> Unit) {
        this.observe(this@BaseActivity, observer)
    }

    @MainThread
    protected fun <T> LiveData<T?>.observeNotNull(observer: (T) -> Unit) {
        this.observeNotNull(this@BaseActivity, observer)
    }

    @MainThread
    protected inline fun <T> LiveData<T?>.observeTillDestroyNotNull(crossinline observer: (T) -> Unit) {
        this.observeTillDestroyNotNull(this@BaseActivity, observer)
    }

    @MainThread
    protected inline fun <T> LiveData<T>.observeTillDestroy(crossinline observer: (T) -> Unit) {
        this.observeTillDestroy(this@BaseActivity, observer)
    }

    @MainThread
    protected inline fun <T> LiveData<T>.observeNonNullOnce(crossinline observer: (T) -> Unit) {
        this.observeNotNullOnce(this@BaseActivity, observer)
    }
}