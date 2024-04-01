package io.rapidz.jetpackcomposetraining_assignment0.storage

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(context: Context){

    companion object {
        private const val PREF_NAME = "user_prefs"
        private const val LAST_LOGIN_USERNAME_KEY = "last_login_username"
    }

    private val sharedPreference : SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    fun getLastLoginUsername() : String? {
        return sharedPreference.getString(LAST_LOGIN_USERNAME_KEY, null)
    }

    fun setLastLoginUsername(username: String) {
        sharedPreference.edit().putString(LAST_LOGIN_USERNAME_KEY, username).apply()
    }
}