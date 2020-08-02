package com.slutsenko.cocktailapp.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.util.Language



class LanguageListBottomSheetDialogFragment :
        ListBaseBottomSheetDialogFragment<Language?, ListDialogButton, LanguageDialogType>() {

    override val dialogType: LanguageDialogType = LanguageDialogType
    override var data: Language? = Language.ENGLISH
    private var selectedLanguage: Language? = Language.ENGLISH

    override val dialogListDataAdapter: DialogListDataAdapter<Language?> =
            object : DialogListDataAdapter<Language?> {
                override fun getName(data: Language?): CharSequence {
                    return data?.value ?: ""
                }
            }
    override var dialogBuilder: SimpleDialogBuilder = SimpleDialogBuilder()

    //override var listData: List<Language?> = Language.values().sortedBy { it.ordinal }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogBuilder = requireArguments().getParcelable(EXTRA_KEY_BUILDER)!!
        val language = requireArguments().getInt(EXTRA_KEY_SELECTED_LANGUAGE)
        selectedLanguage = Language.values()[language]
        listAdapter = DialogListAdapter(selectedLanguage)
    }

    override var listData: List<Language?> = mutableListOf<Language?>().apply {
        addAll(Language.values())
    }.toList()

    override fun getButtonType(view: View): ListDialogButton {
        return when (view.id) {
            R.id.btn_language -> ItemListDialogButton
            else -> throw NotImplementedError("handle another dialog button types")
        }
    }

    override fun obtainDataForView(view: View): Language? {
        return when (getButtonType(view)) {
            is ItemListDialogButton -> view.tag as? Language?
            else -> super.obtainDataForView(view)
        }
    }

    companion object {
        fun newInstance(selectedLanguage: Language? = null): LanguageListBottomSheetDialogFragment {
            return LanguageListBottomSheetDialogFragment()
                    .apply {
                        arguments = bundleOf(
                                EXTRA_KEY_BUILDER to SimpleDialogBuilder().apply {
                                    titleTextResId = R.string.change_language
                                    //descriptionTextResId = R.string.change_language
                                    isCancelable = true
                                },
                                EXTRA_KEY_SELECTED_LANGUAGE to selectedLanguage?.ordinal
                        )
                    }
        }

        private const val EXTRA_KEY_BUILDER = "EXTRA_KEY_BUILDER"
        private const val EXTRA_KEY_SELECTED_LANGUAGE = "EXTRA_KEY_SELECTED_LANGUAGE"
    }



}