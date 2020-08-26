package com.slutsenko.cocktailapp.presentation.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.auth.LoginActivity
import com.slutsenko.cocktailapp.presentation.ui.base.BaseFragment
import com.slutsenko.cocktailapp.presentation.ui.dialog.*
import com.slutsenko.cocktailapp.presentation.viewmodel.MainFragmentViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlin.reflect.KClass

class ProfileFragment : BaseFragment<MainFragmentViewModel>() {
    override val viewModelClass: KClass<MainFragmentViewModel>
        get() = MainFragmentViewModel::class
    override val contentLayoutResId: Int = R.layout.fragment_profile
    private lateinit var bottomSheetDialogFragment: RegularBottomSheetDialogFragment

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)
        profile_btn_logOut.setOnClickListener {
            bottomSheetDialogFragment = RegularBottomSheetDialogFragment.newInstance {
                titleText = getString(R.string.logout)
                descriptionText = getString(R.string.logout_desc)
                leftButtonText = getString(R.string.cancel)
                rightButtonText = getString(R.string.accept)
            }
            bottomSheetDialogFragment.show(childFragmentManager, RegularBottomSheetDialogFragment::class.java.name)
        }
        profile_btn_back.setOnClickListener {

        }

        profile_btn_changeImage.setOnClickListener {

        }

        profile_btn_changeUserData.setOnClickListener {

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
        }
    }

}