package com.example.mob21firabase.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.mob21firabase.core.service.StorageService
import com.example.mob21firabase.ui.base.BaseFragment
import com.example.mob21firabase.ui.base.BaseViewModel
import com.example.mob21firebase.R
import com.example.mob21firebase.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment :BaseFragment<FragmentProfileBinding>() {
    override val viewModel: ProfileViewModel by viewModels ()
    private lateinit var imagePickerLauncher: ActivityResultLauncher<String>
    private var imageName: String? = null
    @Inject
    lateinit var storageService: StorageService

    override fun getLayoutResource() = R.layout.fragment_profile

    override fun onBindView(view: View) {
        super.onBindView(view)
        imagePickerLauncher = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) {
            it?.let { uri ->
                val newName = if(imageName == null || imageName == "null") null else imageName
                binding?.ivProfile?.setImageURI(it)
                storageService.uploadImage(uri = it, newName){ name ->
                    if(name != null){
                        viewModel.updateProfile(name)
                        imageName = name
                    }
                }
            }
        }
        binding?.fabEdit?.setOnClickListener {
            imagePickerLauncher.launch("image/*")
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        viewModel.user.observe(viewLifecycleOwner) {
            imageName = it.profilePic
            binding?.run {
                etName.text = it.firstName
                etEmail.text = it.email
                showProfile(ivProfile,imageName)
            }
        }
    }

    private fun showProfile(imageView: ImageView, name: String?) {
        if (name.isNullOrEmpty()) return
        storageService.getImageUri(name) {
            Glide.with(this)
                .load(it)
                .placeholder(R.drawable.ic_person)
                .centerCrop()
                .into(imageView)
        }
    }
}