package com.app.restoapps.di.login;

/**
 * TODO: Add a class header comment!
 */
public interface LoginContract {

    interface LoginView{
        void moveToMainPage();
    }

    interface LoginPresenter{
        void doLogin();
    }

}
