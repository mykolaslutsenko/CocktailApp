package com.slutsenko.cocktailapp.ui.dialog

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.slutsenko.cocktailapp.R
import kotlinx.android.synthetic.main.layout_dialog_simple.*

abstract class SimpleBaseDialogFragment<
        Data,
        ButtonType : DialogButton,
        Type : DialogType<ButtonType>,
        Builder : SimpleBaseDialogFragment.SimpleDialogBuilder>
protected constructor() : BaseDialogFragment<Data, ButtonType, Type>() {

    override val contentLayoutResId = R.layout.layout_dialog_simple
    protected open val extraContentLayoutResId: Int = 0

    protected open lateinit var dialogBuilder: Builder
    override var data: Data? = null

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        @Suppress("SENSELESS_COMPARISON")
        check(dialogBuilder != null) {
            "${SimpleBaseDialogFragment::class.java.simpleName}. " +
                    "Property dialogBuilder must not be implemented and must not be null after " +
                    "super.onViewCreated(view, savedInstanceState) called and afterwards"
        }
        tv_dialog_title.text = dialogBuilder.titleText.takeIf { it.isNotEmpty() }
                ?: runCatching { requireContext().getString(dialogBuilder.titleTextResId) }
                        .getOrElse { throw NotImplementedError("Must supply dialog title for ${this::class.java.simpleName}") }

        tv_dialog_description.setText(
                dialogBuilder.descriptionText.takeIf { it.isNotEmpty() }
                        ?: runCatching { requireContext().getString(dialogBuilder.descriptionTextResId) }.getOrNull()
        )
        if (!tv_dialog_description.text.isBlank()) {
            tv_dialog_description.visibility = View.VISIBLE
        }

        val leftButtonText = dialogBuilder.leftButtonText.takeIf { it.isNotEmpty() }
                ?: runCatching { requireContext().getString(dialogBuilder.leftButtonTextResId) }.getOrNull()

        val rightButtonText = dialogBuilder.rightButtonText.takeIf { it.isNotEmpty() }
                ?: runCatching { requireContext().getString(dialogBuilder.rightButtonTextResId) }.getOrNull()

        btn_dialog_left.isVisible = !leftButtonText.isNullOrEmpty()
        btn_dialog_right.isVisible = !rightButtonText.isNullOrEmpty()

        space_dialog_buttons.isVisible =
                btn_dialog_left.isVisible &&  btn_dialog_right.isVisible
        ll_dialog_buttons.isVisible = btn_dialog_left.isVisible ||  btn_dialog_right.isVisible

        btn_dialog_left.text = leftButtonText ?: ""
        btn_dialog_right.text = rightButtonText ?: ""

        if (dialogBuilder.isCloseButtonVisible) {
           iv_dialog_close.setOnClickListener {
                dismiss()
            }
            iv_dialog_close.isVisible = true
        } else {
            iv_dialog_close.setOnClickListener(null)
            iv_dialog_close.isGone = true
        }

        isCancelable = dialogBuilder.isCancelable


        btn_dialog_left.setOnClickListener(this)
        btn_dialog_right.setOnClickListener(this)
        iv_dialog_close.setOnClickListener(this)

        if (extraContentLayoutResId != 0) {
            fl_dialog_extra_contents?.let {
                layoutInflater.inflate(extraContentLayoutResId, fl_dialog_extra_contents)
                configureExtraContent(fl_dialog_extra_contents, savedInstanceState)
            }
        }
        isCancelable = dialogBuilder.isCancelable
    }

    protected open fun configureExtraContent(container: FrameLayout, savedInstanceState: Bundle?) {
        //stub
    }

    override fun obtainClickableViews(): List<View> = listOf(
            btn_dialog_left,
            btn_dialog_right
    )


    open class SimpleDialogBuilder constructor() : Parcelable {
        /**
         * Use either [titleTextResId] or [titleText].
         * If both defined - text takes precedence.
         * If none - empty string ("") will be set
         */
        @StringRes
        var titleTextResId = 0
        var titleText: CharSequence = ""

        @StringRes
        var descriptionTextResId = 0
        var descriptionText: CharSequence = ""

        @StringRes
        var leftButtonTextResId = 0
        var leftButtonText: CharSequence = ""

        @StringRes
        var rightButtonTextResId = 0
        var rightButtonText: CharSequence = ""

        var isCancelable: Boolean = true
        var isCloseButtonVisible: Boolean = false

        constructor(parcel: Parcel) : this() {
            titleTextResId = parcel.readInt()
            titleText = parcel.readString() ?: ""
            descriptionTextResId = parcel.readInt()
            descriptionText = parcel.readString() ?: ""
            leftButtonTextResId = parcel.readInt()
            leftButtonText = parcel.readString() ?: ""
            rightButtonTextResId = parcel.readInt()
            rightButtonText = parcel.readString() ?: ""
            isCancelable = parcel.readByte() != 0.toByte()
            isCloseButtonVisible = parcel.readByte() != 0.toByte()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(titleTextResId)
            parcel.writeString(titleText.toString())
            parcel.writeInt(descriptionTextResId)
            parcel.writeString(descriptionText.toString())
            parcel.writeInt(leftButtonTextResId)
            parcel.writeString(leftButtonText.toString())
            parcel.writeInt(rightButtonTextResId)
            parcel.writeString(rightButtonText.toString())
            parcel.writeByte(if (isCancelable) 1 else 0)
            parcel.writeByte(if (isCloseButtonVisible) 1 else 0)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<SimpleDialogBuilder> {
            override fun createFromParcel(parcel: Parcel): SimpleDialogBuilder {
                return SimpleDialogBuilder(
                        parcel
                )
            }

            override fun newArray(size: Int): Array<SimpleDialogBuilder?> {
                return arrayOfNulls(size)
            }
        }
    }
}