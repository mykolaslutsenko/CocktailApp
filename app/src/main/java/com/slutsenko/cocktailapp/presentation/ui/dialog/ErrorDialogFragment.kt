package com.slutsenko.cocktailapp.presentation.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.slutsenko.cocktailapp.R

class ErrorDialogFragment :
        SimpleBaseDialogFragment<Any, SingleDialogButton, ErrorDialogType, SimpleBaseDialogFragment.SimpleDialogBuilder>() {

    override val dialogType: ErrorDialogType = ErrorDialogType

    override var dialogBuilder: SimpleDialogBuilder = SimpleDialogBuilder()
    override var data: Any? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogBuilder = requireArguments().getParcelable(EXTRA_KEY_BUILDER)!!
    }

    override fun obtainDataForView(view: View): Any? {
        return data
    }

    override fun getButtonType(view: View): SingleDialogButton {
        return when (view.id) {
            R.id.btn_dialog_left -> ActionSingleDialogButton
            else -> throw NotImplementedError("handle another dialog button types")
        }
    }

    companion object {
        fun newInstance(builder: SimpleDialogBuilder.() -> Unit): ErrorDialogFragment {
            return getInstance(builder)
        }


        fun getInstance(
                builder: SimpleDialogBuilder.() -> Unit
        ): ErrorDialogFragment {
            val fragment = ErrorDialogFragment()
            fragment.arguments = bundleOf(
                    EXTRA_KEY_BUILDER to (SimpleDialogBuilder().apply(builder))
            )
            return fragment
        }

        private const val EXTRA_KEY_BUILDER = "EXTRA_KEY_BUILDER"
    }
}