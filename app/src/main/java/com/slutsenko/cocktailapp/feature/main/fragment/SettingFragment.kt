package com.slutsenko.cocktailapp.feature.main.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.FragmentSettingBinding
import com.slutsenko.cocktailapp.feature.main.MainActivityViewModel
import com.slutsenko.cocktailapp.feature.main.SettingFragmentViewModel
import com.slutsenko.cocktailapp.feature.splash.SplashActivity
import com.slutsenko.cocktailapp.presentation.ui.base.BaseFragment
import com.slutsenko.cocktailapp.presentation.ui.dialog.*
import com.slutsenko.common.Language
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlin.reflect.KClass

class SettingFragment : BaseFragment<SettingFragmentViewModel, FragmentSettingBinding>() {
    override val viewModelClass: KClass<SettingFragmentViewModel>
        get() = SettingFragmentViewModel::class
    override val contentLayoutResId: Int = R.layout.fragment_setting

    private val mainViewModel: MainActivityViewModel
        get() {
            return ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        }

    private lateinit var languageListBottomSheetDialogFragment: LanguageListBottomSheetDialogFragment

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)

        remoteConfigTitleBottomBar()

        chb_showTitle.setOnCheckedChangeListener { _, isChecked ->
            mainViewModel.showNavigationBarTitlesLiveData.value = isChecked
        }

        ll_language.setOnClickListener {
            languageListBottomSheetDialogFragment = LanguageListBottomSheetDialogFragment.newInstance(
                    Language.ENGLISH)
            languageListBottomSheetDialogFragment.show(childFragmentManager, LanguageListBottomSheetDialogFragment::class.java.name)
        }

        ll_profile.setOnClickListener {
            val profileFragment = ProfileFragment()
            childFragmentManager
                    .beginTransaction()
                    .add(R.id.fcv_setting_fragment, profileFragment, ProfileFragment::class.java.name)
                    .addToBackStack(ProfileFragment::class.java.name)
                    .commit()
        }

    }

    private fun remoteConfigTitleBottomBar() {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 1
        }
        val config = FirebaseRemoteConfig.getInstance()
        config.setConfigSettingsAsync(configSettings)

        Log.d("config", config.get("show_title_bottom_navigation_bar").asString())
        val isShowTitle = config.get("show_title_bottom_navigation_bar").asBoolean()
        config.fetchAndActivate()
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        if (isShowTitle) {
                            chb_showTitle.visibility = View.GONE
                        } else {
                            chb_showTitle.visibility = View.VISIBLE
                        }
                        val updated = task.result
                        Log.d("config", "Config params updated: $updated")
                    }
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
                        val sharedPref = activity?.getSharedPreferences("lang", Context.MODE_PRIVATE)
                                ?: return
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