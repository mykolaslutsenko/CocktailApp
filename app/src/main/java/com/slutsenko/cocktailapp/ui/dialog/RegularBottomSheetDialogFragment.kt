package com.slutsenko.cocktailapp.ui.dialog

import android.view.View
import androidx.core.os.bundleOf
import com.slutsenko.cocktailapp.R

class RegularBottomSheetDialogFragment<Data> :
        SimpleBaseBottomSheetDialogFragment<Data, RegularDialogButton, RegularDialogType, SimpleBaseBottomSheetDialogFragment.SimpleDialogBuilder>() {

    override val dialogType: RegularDialogType = RegularDialogType

    override var dialogBuilder: SimpleDialogBuilder = SimpleDialogBuilder()
    override var data: Data? = null

    override fun getButtonType(view: View): RegularDialogButton {
        return when (view.id) {
            R.id.lb_dialog_bs_left -> LeftDialogButton
            R.id.lb_dialog_bs_right -> RightDialogButton
            else -> throw NotImplementedError("handle another dialog button types")
        }
    }

    companion object {
        fun newInstance(builder: SimpleDialogBuilder.() -> Unit): RegularBottomSheetDialogFragment<Any?> {
            return newInstance(null, builder)
        }

        /**
         * Note trick that data can be used as tag to distinguish dialogs
         * if multiple instances exists within one context (activity/fragment)
         */
        fun <Data> newInstance(
                data: Data? = null,
                builder: SimpleDialogBuilder.() -> Unit
        ): RegularBottomSheetDialogFragment<Data> {
            val fragment = RegularBottomSheetDialogFragment<Data>()
            fragment.arguments = bundleOf()
                EXTRA_KEY_BUILDER to (SimpleDialogBuilder().apply(builder))
            return fragment
        }

        private const val EXTRA_KEY_BUILDER = "EXTRA_KEY_BUILDER"
    }
}