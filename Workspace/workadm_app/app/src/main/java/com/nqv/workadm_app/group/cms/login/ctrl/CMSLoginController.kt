package com.nqv.workadm_app.group.cms.login.ctrl

import com.google.gson.Gson
import com.nqv.workadm_app.api.CallApi
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8


suspend fun handleLogin(username: String, password: String) : LoginState {
    val hashedPassword = hashPassword(password)
    println(username)
    println(password)
    val ref = mutableMapOf<String, Any>()
    val svClass = "sv_name"
    val svName = "sv_class"
    ref[svClass] = "/login"
    ref[svName] = "svAutLogin"
    ref["user_name"] = username
    ref["user_pass"] = hashedPassword
    val gson = Gson()
    val jsonString = gson.toJson(ref)

    try {
        val response = CallApi.loginController.login(jsonString)

        if (response.isSuccessful) {
            return LoginState(isLoading = false, isSuccess = true)
        } else {
            return LoginState(isLoading = false, isError = true)
        }
    } catch (e: Exception) {
        return LoginState(isLoading = false, isError = true)
    }
}

fun hashPassword(password: String): String {
    val bytes = password.toByteArray(UTF_8)
    val digest = MessageDigest.getInstance("SHA-256")
    val hashedBytes = digest.digest(bytes)
    return hashedBytes.joinToString("") { "%02x".format(it) }
}
