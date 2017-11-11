package com.chase.studentattendance.login

import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class LoginPresenterTest {

    @Test
    fun checkIfLoginAttemptIsExceeded() {
        val loginView = mock(LoginView::class.java)
        val loginPresenter = LoginPresenter(loginView)
        Assert.assertEquals(1, loginPresenter.incrementLoginAttempt())
        Assert.assertEquals(2, loginPresenter.incrementLoginAttempt())
        Assert.assertEquals(3, loginPresenter.incrementLoginAttempt())
        Assert.assertTrue(loginPresenter.isLoginAttemptExceeded())
    }

    @Test
    fun checkIfLoginAttemptIsNotExceeded() {
        val loginView = mock(LoginView::class.java)
        val loginPresenter = LoginPresenter(loginView)
        Assert.assertFalse(loginPresenter.isLoginAttemptExceeded())
    }

    @Test
    fun checkUsernameAndPasswordIsCorrect() {
        val loginView = mock(LoginView::class.java)
        val loginPresenter = LoginPresenter(loginView)
        loginPresenter.doLogin("Chase", "Tdd")
        verify(loginView).showLoginSuccessMessage()
    }

    @Test
    fun checkUsernameAndPasswordIsIncorrect() {
        val loginView = mock(LoginView::class.java)
        val loginPresenter = LoginPresenter(loginView)
        loginPresenter.doLogin("notChase", "notTDD")
        verify(loginView).showErrorMessageForUsernamePassword()
    }

    @Test
    fun checkIfLoginAttemptIsExceededAndViewMethodCalled() {
        val loginView = mock(LoginView::class.java)
        val loginPresenter = LoginPresenter(loginView)
        loginPresenter.doLogin("notChase", "notTDD")
        loginPresenter.doLogin("notChase", "notTDD")
        loginPresenter.doLogin("notChase", "notTDD")
        loginPresenter.doLogin("notChase", "notTDD")
        verify(loginView).showErrorMessageForMaxLoginAttempt()
    }
}