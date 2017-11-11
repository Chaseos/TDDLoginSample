package com.chase.studentattendance.login

class LoginPresenter(private val loginView: LoginView) {

    private val MAX_LOGIN_ATTEMPT = 3
    var loginAttempt = 0

    fun incrementLoginAttempt(): Int {return ++loginAttempt}

    fun isLoginAttemptExceeded(): Boolean {return loginAttempt >= MAX_LOGIN_ATTEMPT}

    fun isLoginSuccess(userName: String, password: String): Boolean {
        if (isLoginAttemptExceeded()) return false
        if (userName == "Chase" && password == "Tdd") return true
        incrementLoginAttempt()
        return false
    }

    fun doLogin(userName: String, password: String) {
        if (isLoginAttemptExceeded()) {
            loginView.showErrorMessageForMaxLoginAttempt()
            return
        }

        if (userName == "Chase" && password == "Tdd") {
            loginView.showLoginSuccessMessage()
            return
        }

        incrementLoginAttempt()
        loginView.showErrorMessageForUsernamePassword()
    }
}