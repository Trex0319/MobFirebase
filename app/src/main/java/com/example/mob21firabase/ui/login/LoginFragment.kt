package com.example.mob21firabase.ui.login

import android.util.Log
import android.view.View
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mob21firabase.ui.base.BaseFragment
import com.example.mob21firebase.BuildConfig
import com.example.mob21firebase.R
import com.example.mob21firebase.databinding.FragmentLoginBinding
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val viewModel: LoginViewModel by viewModels()

    override fun getLayoutResource() = R.layout.fragment_login

    override fun onBindView(view: View) {
        super.onBindView(view)

        binding?.btnRegister?.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }

        binding?.btnLogin?.setOnClickListener {
            val email = binding?.etEmail?.text.toString()
            val password = binding?.etPassword?.text.toString()
            viewModel.login(email, password)
        }
        binding?.btnLoginWithGoogle?.setOnClickListener {
            signInWithGoogle()
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        lifecycleScope.launch {
            viewModel.success.collect {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                )
            }
        }
    }

    private fun signInWithGoogle() {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(BuildConfig.oauth_client_id)
            .setNonce("qwerqwer")
            .setAutoSelectEnabled(true)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch{
            try {
                val result = CredentialManager.create(requireContext()).getCredential(
                    context = requireContext(),
                    request = request
                )
                handleSignInResult(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    private fun handleSignInResult(result: GetCredentialResponse) {
        val credential = result.credential
        if(credential !is CustomCredential ) {
            return
        }

        if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            val googleTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            viewModel.login(googleTokenCredential)
            Log.d("debugging", googleTokenCredential.profilePictureUri.toString())
        }
    }
}