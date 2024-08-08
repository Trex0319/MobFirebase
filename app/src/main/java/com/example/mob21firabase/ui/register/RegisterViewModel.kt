package com.example.mob21firabase.ui.register

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob21firabase.core.service.AuthService
import com.example.mob21firabase.core.utils.ValidationUtil
import com.example.mob21firabase.data.model.User
import com.example.mob21firabase.data.model.ValidationField
import com.example.mob21firabase.data.repo.UserRepo
import com.example.mob21firabase.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authService: AuthService,
    private val userRepo: UserRepo
) : BaseViewModel() {
    val success: MutableSharedFlow<Unit> = MutableSharedFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun register(firstName: String, lastName: String, email: String, password: String, confirmPassword: String) {
        val error = ValidationUtil.validate(
            ValidationField(firstName,"[a-zA-Z ]{2,20}", "Enter a valid name"),
            ValidationField(lastName,"[a-zA-Z ]{2,20}", "Enter a valid name"),
            ValidationField(email,"[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+", "Enter a valid name"),
            ValidationField(password,"[a-zA-Z0-9#$%]{6,20}", "Enter a valid password")
        )
        if(error == null) {
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.emit(true)
                errorHandler {
                    authService.createUserWithEmailAndPass(email, password)
                }?.let {
                    userRepo.createUser(
                        User(firstName, lastName, email)
                    )
                    success.emit(Unit)
                }
                _isLoading.emit(false)
            }
        } else {
            viewModelScope.launch {
                _error.emit(error)
            }
        }
    }
}