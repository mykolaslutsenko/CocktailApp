package com.slutsenko.cocktailapp.feature.main.fragment

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.feature.auth.LoginActivity
import com.slutsenko.cocktailapp.databinding.FragmentProfileBinding
import com.slutsenko.cocktailapp.presentation.extension.log
import com.slutsenko.cocktailapp.presentation.extension.convertBitmapToFile
import com.slutsenko.cocktailapp.presentation.extension.convertMbToBinaryBytes
import com.slutsenko.cocktailapp.presentation.extension.scaleToSize
import com.slutsenko.cocktailapp.presentation.ui.base.BaseFragment
import com.slutsenko.cocktailapp.presentation.ui.dialog.*
import com.slutsenko.cocktailapp.feature.main.SettingFragmentViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.File
import kotlin.reflect.KClass

class ProfileFragment : BaseFragment<SettingFragmentViewModel, FragmentProfileBinding>() {
    override val viewModelClass: KClass<SettingFragmentViewModel>
        get() = SettingFragmentViewModel::class
    override val contentLayoutResId: Int = R.layout.fragment_profile
    private lateinit var bottomSheetDialogFragment: RegularBottomSheetDialogFragment

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)

        viewModel.userLiveData.observe(requireActivity(), Observer {
            viewModel.firstNameLiveData.value = it?.name
            viewModel.lastNameLiveData.value = it?.lastName
            viewModel.emailLiveData.value = it?.email
            viewModel.avatarLiveData.value = it?.avatar
        })

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
            requireActivity().onBackPressed()
        }

        profile_btn_changeImage.setOnClickListener {
            ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    21
            )
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 22)
        }


        profile_btn_changeUserData.setOnClickListener {
            val editProfileFragment = EditProfileFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.fcv_setting_fragment, editProfileFragment, EditProfileFragment::class.java.name)
                    .addToBackStack(EditProfileFragment::class.java.name)
                    .commit()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val uri = data?.data ?: return

        val bitmap = BitmapFactory.decodeStream(
                requireActivity().contentResolver.openInputStream(uri))
                .scaleToSize(convertMbToBinaryBytes(1))
        val imageFile = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                System.currentTimeMillis().toString() + ".jpeg"
        )
        bitmap.convertBitmapToFile(imageFile)
        viewModel.uploadAvatar(imageFile) { fraction ->
            "LOG PROGRESS = fraction=$fraction, percent=${fraction * 100.0F}%".log
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
                        requireContext().startActivity(Intent(requireContext(), LoginActivity::class.java))
                        viewModel.deleteUser()
                        requireActivity().finishAffinity()
                    }
                    LeftDialogButton -> {
                        dialog.dismiss()
                    }
                }
            }
        }
    }

    override fun configureDataBinding(binding: FragmentProfileBinding) {
        super.configureDataBinding(binding)
        binding.viewModel = viewModel
    }
}

