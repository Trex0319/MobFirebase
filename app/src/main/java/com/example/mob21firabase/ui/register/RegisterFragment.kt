package com.example.mob21firabase.ui.register

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mob21firabase.ui.base.BaseFragment
import com.example.mob21firebase.R
import com.example.mob21firebase.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override val viewModel: RegisterViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_register

    override fun onBindView(view: View) {
        super.onBindView(view)
        binding?.btnLogin?.setOnClickListener {
            findNavController().navigate(
                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            )
        }

        binding?.run {
            btnRegister.setOnClickListener {
                viewModel.register(
                    firstName = etFirstName.text.toString(),
                    lastName = etLastName.text.toString(),
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString(),
                    confirmPassword = etConfirmPassword.text.toString()
                )
            }
        }

        lifecycleScope.launch {
            viewModel.error.collect { errorMessage ->
                errorMessage?.let {
                    Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        lifecycleScope.launch {
            viewModel.success.collect {
                findNavController().navigate(
                    RegisterFragmentDirections.actionRegisterFragmentToHomeFragment()
                )
            }
        }
    }
}