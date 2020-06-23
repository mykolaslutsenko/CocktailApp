package com.slutsenko.cocktailapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.slutsenko.cocktailapp.ui.dialog.BaseDialogFragment

abstract class BaseFragment : Fragment(),
        BaseDialogFragment.OnDialogFragmentClickListener<Cocktail>,
        BaseDialogFragment.OnDialogFragmentDismissListener<Cocktail> {

    protected abstract val contentLayoutResId: Int

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(contentLayoutResId, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureView(savedInstanceState)
    }

    protected open fun configureView(savedInstanceState: Bundle?) {
        //stub
    }

    override fun onBottomSheetDialogFragmentClick(dialog: DialogFragment, data: Cocktail?) {
        TODO("Not yet implemented")
    }

    override fun onBottomSheetDialogFragmentDismiss(dialog: DialogFragment, data: Cocktail?) {
        TODO("Not yet implemented")
    }
}