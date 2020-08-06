package com.slutsenko.cocktailapp.presentation.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.slutsenko.cocktailapp.R
import kotlinx.android.synthetic.main.layout_dialog_bottom_sheet_list_component.*

abstract class ListBaseBottomSheetDialogFragment<
        Data,
        ButtonType : DialogButton,
        Type : DialogType<ButtonType>>
protected constructor() : SimpleBaseBottomSheetDialogFragment<Data, ButtonType, Type, SimpleBaseBottomSheetDialogFragment.SimpleDialogBuilder>() {

    override val contentLayoutResId = R.layout.layout_dialog_bottom_sheet_simple
    override val extraContentLayoutResId: Int = R.layout.layout_dialog_bottom_sheet_list_component

    abstract val dialogListDataAdapter: DialogListDataAdapter<Data>
    protected open var listAdapter = DialogListAdapter()

    open var listData: List<Data> = mutableListOf()

    override fun configureExtraContent(container: FrameLayout, savedInstanceState: Bundle?) {
        super.configureExtraContent(container, savedInstanceState)
        listAdapter.newData = listData
        rv_dialog_language_container.apply {
            setHasFixedSize(true)
            adapter = this@ListBaseBottomSheetDialogFragment.listAdapter
        }
    }

    protected inner class DialogListAdapter(private val selectedButton: Any? = null) : RecyclerView.Adapter<DialogListAdapter.ButtonViewHolder>(),
            View.OnClickListener {

        private val layoutId = R.layout.item_language_buttton

        var newData: List<Data> = arrayListOf()
            set(value) {
                field = value
                notifyDataSetChanged()
            }


        @Suppress("UNCHECKED_CAST")
        override fun onClick(v: View?) {
            callOnClick(v ?: return, getButtonType(v))
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
            val itemView: View = LayoutInflater.from(parent.context)
                    .inflate(layoutId, parent, false)
            return ButtonViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return newData.size
        }

        override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
            with(holder.itemView as AppCompatButton) {
                text = dialogListDataAdapter.getName(newData[position])
                tag = newData[position]
                setOnClickListener(this@DialogListAdapter)
            }
        }

        inner class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    }


    interface DialogListDataAdapter<Data> {
        fun getName(data: Data): CharSequence
    }
}