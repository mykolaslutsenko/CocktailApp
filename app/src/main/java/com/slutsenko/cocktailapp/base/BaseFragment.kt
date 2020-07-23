package com.slutsenko.cocktailapp.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.slutsenko.cocktailapp.ui.dialog.BaseBottomSheetDialogFragment
import com.slutsenko.cocktailapp.ui.dialog.BaseDialogFragment
import com.slutsenko.cocktailapp.ui.dialog.DialogButton
import com.slutsenko.cocktailapp.ui.dialog.DialogType

abstract class BaseFragment<ViewModel : BaseViewModel> : Fragment(),
        BaseDialogFragment.OnDialogFragmentClickListener<Any, DialogButton, DialogType<DialogButton>>,
        BaseDialogFragment.OnDialogFragmentDismissListener<Any, DialogButton, DialogType<DialogButton>>,
        BaseBottomSheetDialogFragment.OnBottomSheetDialogFragmentClickListener<Any, DialogButton, DialogType<DialogButton>>,
        BaseBottomSheetDialogFragment.OnBottomSheetDialogFragmentDismissListener<Any, DialogButton, DialogType<DialogButton>> {

    protected abstract val viewModel: ViewModel

    private val log = "BaseLog"

    protected abstract val contentLayoutResId: Int

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(log, this::class.java.toString() + " On create view")
        return inflater.inflate(contentLayoutResId, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureView(savedInstanceState)
        Log.d(log, this::class.java.toString() + " On view created")
    }

    protected open fun configureView(savedInstanceState: Bundle?) {
        //stub
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(log, this::class.java.toString() + " On attach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(log, this::class.java.toString() + " On create")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(log, this::class.java.toString() + " On view state restored")
    }

    override fun onStart() {
        super.onStart()
        Log.d(log, this::class.java.toString() + " On start")
    }

    override fun onResume() {
        super.onResume()
        Log.d(log, this::class.java.toString() + " On resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(log, this::class.java.toString() + " On pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(log, this::class.java.toString() + " On stop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(log, this::class.java.toString() + " On destroyed view")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(log, this::class.java.toString() + " On destroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(log, this::class.java.toString() + " On detach")
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