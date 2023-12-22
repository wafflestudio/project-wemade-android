package com.wafflestudio.projectwemade.feature.authentication

import androidx.lifecycle.ViewModel
import com.wafflestudio.projectwemade.model.dto.User
import com.wafflestudio.projectwemade.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val user: StateFlow<User?> get() = userRepository.user

    fun signUp(
        username: String,
        password: String,
        onUsernameDuplicated: () -> Unit,
        onInvalidUsername: () -> Unit,
    ) {
        if (username.isEmpty() || password.isEmpty()) {
            return
        }
        if (Regex("\\d{8}").matches(username).not()) {
            onInvalidUsername()
            return
        }
        userRepository.signUp(
            username = username,
            password = password,
            onUsernameDuplicated = onUsernameDuplicated
        )
    }

    fun signIn(
        username: String,
        password: String,
        onUserNotFound: () -> Unit,
        onPasswordMismatch: () -> Unit,
    ) {
        if (username.isEmpty() || password.isEmpty()) {
            return
        }
        userRepository.signIn(
            username = username,
            password = password,
            onUserNotFound = onUserNotFound,
            onPasswordMismatch = onPasswordMismatch
        )
    }
}