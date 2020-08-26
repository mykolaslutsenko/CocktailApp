package com.slutsenko.cocktailapp.presentation.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.presentation.ui.activity.SplashActivity
import com.slutsenko.cocktailapp.presentation.ui.base.BaseFragment
import com.slutsenko.cocktailapp.presentation.ui.dialog.*
import com.slutsenko.cocktailapp.presentation.viewmodel.MainFragmentViewModel
import com.slutsenko.cocktailapp.util.Language
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlin.reflect.KClass

class SettingFragment : BaseFragment<MainFragmentViewModel>() {
    override val viewModelClass: KClass<MainFragmentViewModel>
        get() = MainFragmentViewModel::class
    override val contentLayoutResId: Int = R.layout.fragment_setting


    private lateinit var languageListBottomSheetDialogFragment: LanguageListBottomSheetDialogFragment

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)

        chb_showTitle.setOnCheckedChangeListener { _, isChecked ->
            viewModel.showNavigationBarTitlesLiveData.value = isChecked
            //mainActivityViewModel.showNavigationBarTitlesLiveData.value = isChecked
        }



        ll_language.setOnClickListener {
            languageListBottomSheetDialogFragment = LanguageListBottomSheetDialogFragment.newInstance(
                    Language.ENGLISH)
            languageListBottomSheetDialogFragment.show(childFragmentManager, LanguageListBottomSheetDialogFragment::class.java.name)
        }

        ll_profile.setOnClickListener{
            val profileFragment = ProfileFragment()
            childFragmentManager.beginTransaction()
                    .add(R.id.fcv_setting_fragment, profileFragment, ProfileFragment::class.java.name)
                    .addToBackStack(ProfileFragment::class.java.name)
                    .commit()
        }

    }


    override fun onBottomSheetDialogFragmentClick(
            dialog: DialogFragment,
            buttonType: DialogButton,
            type: DialogType<DialogButton>,
            data: Any?
    ) {
        super.onBottomSheetDialogFragmentClick(dialog, buttonType, type, data)
        when (type) {
            LanguageDialogType -> {
                when (buttonType) {
                    ItemListDialogButton -> {
                        val selectedLanguage = data as Language
                        val sharedPref = activity?.getSharedPreferences("lang", Context.MODE_PRIVATE) ?: return
                        sharedPref.edit().putString("language", selectedLanguage.locale).apply()
                        val intent = Intent(requireContext(), SplashActivity::class.java)
                        requireActivity().startActivity(intent)
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(): SettingFragment {
            return SettingFragment()
        }
    }
}