package com.example.mob21firabase.ui.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.mob21firabase.core.service.AuthService
import com.example.mob21firabase.data.model.User
import com.example.mob21firabase.data.repo.TodoRepo
import com.example.mob21firabase.data.repo.TodoRepoFirebase
import com.example.mob21firabase.data.repo.UserRepo
import com.example.mob21firabase.ui.base.BaseViewModel
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val authService: AuthService,
    private val userRepo: UserRepo,
    private val repo: TodoRepo
) : BaseViewModel() {
    val success: MutableSharedFlow<Unit> = MutableSharedFlow()

//    init {
//        getGreetings()
//    }

    fun login(email: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            errorHandler {
                validate(email, pass)
                authService.loginWithEmailAndPass(email, pass)
            }?.let {
                success.emit(Unit)
            }
    }

    }

    fun login(credential: GoogleIdTokenCredential) {
        viewModelScope.launch {
            authService.signInWithGoogle(credential)
            userRepo.createUser(
                User(credential.displayName?: "", "",  "")
            )
        }
    }

    private fun validate(email: String, pass: String) {
        if(email.isEmpty() || pass.isEmpty()) {
            throw Exception("Stupid!!!")
        }
    }

    private fun getGreetings() {
        viewModelScope.launch {
            errorHandler {
//                repo.getGreetings()
            }?.let {
                Log.d("debugging", it.toString())
            }
        }
    }
}