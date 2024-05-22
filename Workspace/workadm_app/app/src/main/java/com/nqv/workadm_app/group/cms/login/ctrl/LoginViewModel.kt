package com.nqv.workadm_app.group.cms.login.ctrl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState = _loginState

    fun setLoginState(state: LoginState) {
        _loginState.value = state
    }
}