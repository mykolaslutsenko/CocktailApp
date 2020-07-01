package com.slutsenko.cocktailapp.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.net.http.SslCertificate.restoreState
import android.net.http.SslCertificate.saveState
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.slutsenko.cocktailapp.R

abstract class BaseBottomSheetDialogFragment<Data, ButtonType : DialogButton, Type : DialogType<ButtonType>> protected constructor() :
        BottomSheetDialogFragment(),
        View.OnClickListener {

    protected var onBottomSheetDialogClickListener: OnBottomSheetDialogFragmentClickListener<Data, ButtonType, Type>? =
            null
        private set
    protected var onBottomSheetDialogDismissListener: OnBottomSheetDialogFragmentDismissListener<Data, ButtonType, Type>? =
            null
        private set

    protected abstract val dialogType: Type
    protected open var data: Data? = null
    protected abstract val contentLayoutResId: Int

    private val clickableViews = mutableListOf<View>()

    init {
        //this.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomSheetDialog)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(contentLayoutResId, container, false)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //prepareArguments(arguments)
        super.onViewCreated(view, savedInstanceState)
        clickableViews.clear()
        clickableViews.addAll(obtainClickableViews())
        clickableViews.forEach {
            it.setOnClickListener(this)
        }
        restoreState(savedInstanceState)
    }

    protected open fun obtainClickableViews(): List<View> {
        return emptyList()
    }

    protected abstract fun getButtonType(view: View): ButtonType

    @Suppress("UNUSED_PARAMETER")
    protected open fun obtainDataForView(view: View, buttonType: ButtonType): Data? {
        return data
    }

//    protected open fun setLoadingIfEligible(view: View, buttonType: ButtonType, enable: Boolean = true) {
//        when (view) {
//            is LoadingButton -> view.isLoading = enable
//            is ProgressWheel -> if (enable) view.spin() else view.stopSpinning()
//        }
//    }
//
//    open fun stopAllLoadingButtonsIfNeeded() {
//        clickableViews.forEach {
//            setLoadingIfEligible(it, getButtonType(it), false)
//        }
//    }

    override fun onClick(v: View?) {
        val buttonType = getButtonType(v ?: return)
        callOnClick(v, buttonType)
    }

    protected open fun callOnClick(v: View, buttonType: ButtonType) {
        var startLoadingAndSkipDismiss = false
        onBottomSheetDialogClickListener?.apply {
            val data = obtainDataForView(v, buttonType)
            val acceptClick = this.shouldBottomSheetDialogFragmentAcceptClick(
                    this@BaseBottomSheetDialogFragment,
                    dialogType,
                    buttonType,
                    data
            )

            if (!acceptClick) return

            startLoadingAndSkipDismiss = this.shouldBottomSheetDialogFragmentTypedButtonStartLoadingAndSkipDismiss(
                    this@BaseBottomSheetDialogFragment,
                    dialogType,
                    buttonType,
                    data
            ) ?: false
            //setLoadingIfEligible(v, buttonType, startLoadingAndSkipDismiss)

            this.onBottomSheetDialogFragmentClick(
                    this@BaseBottomSheetDialogFragment,
                    dialogType,
                    buttonType,
                    data
            )
        }
        if (!startLoadingAndSkipDismiss) dismiss()
    }

    @Suppress("UNUSED_PARAMETER")
    protected open fun configureDialog(dialog: BottomSheetDialog) {
        //stub
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.also {
            //it.attributes.windowAnimations = R.style.BottomSheetDialog
        }
        dialog.setOnDismissListener(this)
        configureDialog(dialog)
        return dialog
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onBottomSheetDialogDismissListener?.onBottomSheetDialogFragmentDismiss(this, dialogType, data)
    }

    //region Show Dialog Methods
    override fun show(manager: FragmentManager, tag: String?) {
        showFragmentInternal(manager, tag ?: this::class.java.name)
    }

    override fun show(transaction: FragmentTransaction, tag: String?): Int {
        return super.show(transaction, tag)
    }

    fun show(manager: FragmentManager) {
        showFragmentInternal(manager, this::class.java.name)
    }

    override fun showNow(manager: FragmentManager, tag: String?) {
        showFragmentInternal(manager, tag ?: this::class.java.name, true)
    }

    fun showNow(manager: FragmentManager) {
        showFragmentInternal(manager, this::class.java.name, true)
    }

    protected open fun showFragmentInternal(manager: FragmentManager, tag: String, showNow: Boolean = false): Int {
        // workaround to handle gap if multiple dialog instances are called
        // before first fragment begin transaction and added
        // Note that fragments are not added to manager backstack
        var committedTransactionCount = 0
        val uniquePairKey = manager.toString() to tag
        val isDialogTaggedInstanceInPendingTransaction = instanceSet.contains(uniquePairKey)
        if (isDialogTaggedInstanceInPendingTransaction) return 0
        val fragment = manager.findFragmentByTag(tag)
        val onCommit: () -> Unit = { instanceSet.remove(uniquePairKey) }
        val transaction = manager.beginTransaction().runOnCommit(onCommit)
        try {
            if (fragment == null) {
                when {
                    showNow -> showNow(manager, tag, onCommit)
                    else -> committedTransactionCount = super.show(transaction, tag)
                }
                instanceSet.add(uniquePairKey)
            } else if (fragment is BottomSheetDialogFragment) {

                if (manager.fragments.lastOrNull() == fragment) {
                    instanceSet.remove(uniquePairKey)
                    return 0
                }

                if (this.javaClass == fragment.javaClass) {
                    setInitialSavedState(manager.saveFragmentInstanceState(fragment))
                }

                fragment.dismiss()

                when {
                    showNow -> showNow(manager, tag, onCommit)
                    else -> committedTransactionCount = super.show(transaction, tag)
                }
                instanceSet.add(uniquePairKey)
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            when {
                showNow -> super.showNow(manager, tag)
                else -> super.show(transaction, tag)
            }
            instanceSet.add(uniquePairKey)
        }
        return committedTransactionCount
    }

    private fun showNow(manager: FragmentManager, tag: String, onCommit: () -> Unit) {
        try {
            var field = DialogFragment::class.java.getDeclaredField("mDismissed")
            field.isAccessible = true
            field.set(this, false)

            field = DialogFragment::class.java.getDeclaredField("mShownByMe")
            field.isAccessible = true
            field.set(this, true)

            manager.beginTransaction().apply {
                runOnCommit(onCommit)
                add(this@BaseBottomSheetDialogFragment, tag)
                commitNow()
            }
        } catch (e: Exception) {
            //worst scenario. Possible duplicates
            e.printStackTrace()
            showNow(manager, tag)
            instanceSet.remove(manager.toString() to tag)
        }
    }
    //endregion

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        check(context is OnBottomSheetDialogFragmentClickListener<*, *, *>) {
            "Bottom Sheet Dialog must be attached to context " +
                    "(activity/fragment) that implements ${OnBottomSheetDialogFragmentClickListener::class.java.simpleName} " +
                    "listener"
        }
        onBottomSheetDialogClickListener = context as? OnBottomSheetDialogFragmentClickListener<Data, ButtonType, Type>
        check(context is OnBottomSheetDialogFragmentDismissListener<*, *, *>) {
            "Bottom Sheet Dialog must be attached to context " +
                    "(activity/fragment) that implements ${OnBottomSheetDialogFragmentDismissListener::class.java.simpleName} " +
                    "listener"
        }
        onBottomSheetDialogDismissListener = context as? OnBottomSheetDialogFragmentDismissListener<Data, ButtonType, Type>
    }

    override fun onDetach() {
        super.onDetach()
        onBottomSheetDialogClickListener = null
        onBottomSheetDialogDismissListener = null
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        saveState(outState)
//        super.onSaveInstanceState(outState)
//    }

    interface OnBottomSheetDialogFragmentDismissListener<Data, ButtonType : DialogButton, Type : DialogType<ButtonType>> {
        fun onBottomSheetDialogFragmentDismiss(
                dialog: BottomSheetDialogFragment,
                dialogType: Type,
                data: Data?
        )
    }

    interface OnBottomSheetDialogFragmentClickListener<Data, ButtonType : DialogButton, Type : DialogType<ButtonType>> {
        fun onBottomSheetDialogFragmentClick(
                dialog: BottomSheetDialogFragment,
                dialogType: Type,
                buttonType: ButtonType,
                data: Data?
        )

        fun shouldBottomSheetDialogFragmentAcceptClick(
                dialog: BottomSheetDialogFragment,
                dialogType: Type,
                buttonType: ButtonType,
                data: Data?
        ): Boolean {
            return true
        }

        /**
         * Will trigger loading animation fro view that are handled in [setLoadingIfEligible] in case this
         * method return true
         */
        fun shouldBottomSheetDialogFragmentTypedButtonStartLoadingAndSkipDismiss(
                dialog: BottomSheetDialogFragment,
                dialogType: Type,
                buttonType: ButtonType,
                data: Data?
        ): Boolean? {
            return null
        }
    }

    companion object {
        private val instanceSet = mutableSetOf<Pair<String, String>>()
    }
}