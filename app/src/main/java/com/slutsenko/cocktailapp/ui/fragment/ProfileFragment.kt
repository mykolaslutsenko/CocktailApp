package com.slutsenko.cocktailapp.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.auth.LoginActivity
import com.slutsenko.cocktailapp.base.BaseFragment
import com.slutsenko.cocktailapp.ui.activity.SplashActivity
import com.slutsenko.cocktailapp.ui.dialog.*
import com.slutsenko.cocktailapp.util.Language
import com.slutsenko.cocktailapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment<MainViewModel>() {
    override val contentLayoutResId: Int = R.layout.fragment_profile
    override val viewModel: MainViewModel by activityViewModels()
    private lateinit var bottomSheetDialogFragment: RegularBottomSheetDialogFragment
    private lateinit var languageListBottomSheetDialogFragment: LanguageListBottomSheetDialogFragment

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)

        chb_showTitle.setOnCheckedChangeListener { _, isChecked ->
            viewModel.showNavigationBarTitlesLiveData.value = isChecked
        }

        btn_logOut.setOnClickListener {
            bottomSheetDialogFragment = RegularBottomSheetDialogFragment.newInstance {
                titleText = getString(R.string.logout)
                descriptionText = getString(R.string.logout_desc)
                leftButtonText = getString(R.string.cancel)
                rightButtonText = getString(R.string.accept)
            }
            bottomSheetDialogFragment.show(childFragmentManager, RegularBottomSheetDialogFragment::class.java.simpleName)
        }

        btn_changeLang.setOnClickListener {
            languageListBottomSheetDialogFragment = LanguageListBottomSheetDialogFragment.newInstance(
                    Language.ENGLISH)
            languageListBottomSheetDialogFragment.show(childFragmentManager, LanguageListBottomSheetDialogFragment::class.java.simpleName)
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
            RegularDialogType -> {
                when (buttonType) {
                    RightDialogButton -> {
                        val intent = Intent(requireContext(), LoginActivity::class.java)
                        requireContext().startActivity(intent)
                        requireActivity().finishAffinity()
                    }
                    LeftDialogButton -> {
                        dialog.dismiss()
                    }
                }
            }
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
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}