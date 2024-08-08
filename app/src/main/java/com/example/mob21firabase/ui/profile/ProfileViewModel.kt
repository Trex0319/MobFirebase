package com.example.mob21firabase.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mob21firabase.core.service.AuthService
import com.example.mob21firabase.data.model.User
import com.example.mob21firabase.data.repo.TodoRepo
import com.example.mob21firabase.data.repo.UserRepo
import com.example.mob21firabase.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepo: UserRepo
): BaseViewModel() {
    val user = MutableLiveData<User>()

    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            errorHandler {
                userRepo.getUser()
            }?. let {
                user.value = it
            }
        }
    }
    fun updateProfile(imageName: String) {
        viewModelScope.launch {
            errorHandler {
                user.value?.let {
                    userRepo.updateUser(it.copy(profilePic = imageName))
                }
            }
        }
    }
}