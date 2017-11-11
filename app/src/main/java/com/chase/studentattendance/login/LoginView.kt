package com.chase.studentattendance.login

interface LoginView {

    fun showErrorMessageForUsernamePassword() {}
    fun showErrorMessageForMaxLoginAttempt() {}
    fun showLoginSuccessMessage() {}

}