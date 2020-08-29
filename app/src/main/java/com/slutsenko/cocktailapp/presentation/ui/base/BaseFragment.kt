package com.slutsenko.cocktailapp.presentation.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.slutsenko.cocktailapp.presentation.extension.baseViewModels
import com.slutsenko.cocktailapp.presentation.ui.dialog.BaseBottomSheetDialogFragment
import com.slutsenko.cocktailapp.presentation.ui.dialog.BaseDialogFragment
import com.slutsenko.cocktailapp.presentation.ui.dialog.DialogButton
import com.slutsenko.cocktailapp.presentation.ui.dialog.DialogType
import kotlin.reflect.KClass

abstract class BaseFragment<ViewModel : BaseViewModel, DataBinding : ViewDataBinding> : Fragment(),
        BaseDialogFragment.OnDialogFragmentClickListener<Any, DialogButton, DialogType<DialogButton>>,
        BaseDialogFragment.OnDialogFragmentDismissListener<Any, DialogButton, DialogType<DialogButton>>,
        BaseBottomSheetDialogFragment.OnBottomSheetDialogFragmentClickListener<Any, DialogButton, DialogType<DialogButton>>,
        BaseBottomSheetDialogFragment.OnBottomSheetDialogFragmentDismissListener<Any, DialogButton, DialogType<DialogButton>> {
    abstract val viewModelClass: KClass<ViewModel>
    protected val viewModel: ViewModel by baseViewModels()
    protected open lateinit var dataBinding: DataBinding

    protected open fun configureDataBinding(binding: DataBinding) {}
    private val log = "BaseLog"

    protected abstract val contentLayoutResId: Int

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(log, this::class.java.toString() + " On create view")

        dataBinding = DataBindingUtil.inflate(inflater, contentLayoutResId, container, false)
        dataBinding.lifecycleOwner = this@BaseFragment
        configureDataBinding(dataBinding)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureView(savedInstanceState)
        Log.d(log, this::class.java.toString() + " On view created")
    }

    protected open fun configureView(savedInstanceState: Bundle?) {
        //stub
    }

    override fun onDialogFragmentDismiss(
            dialog: DialogFragment,
            type: DialogType<DialogButton>,
            data: Any?
    ) {

    }

    override fun onDialogFragmentClick(
            dialog: DialogFragment,
            buttonType: DialogButton,
            type: DialogType<DialogButton>,
            data: Any?
    ) {

    }

    override fun onBottomSheetDialogFragmentDismiss(
            dialog: DialogFragment,
            type: DialogType<DialogButton>,
            data: Any?
    ) {

    }

    override fun onBottomSheetDialogFragmentClick(
            dialog: DialogFragment,
            buttonType: DialogButton,
            type: DialogType<DialogButton>,
            data: Any?
    ) {

    }
}