package cl.jouer_club.coach_jouerclub.api.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import cl.jouer_club.coach_jouerclub.models.user.UserModel;
import cl.jouer_club.coach_jouerclub.models.user.UserResponseOne;
import cl.jouer_club.coach_jouerclub.views.login.contract.LoginValidationContract;
import retrofit2.Call;
import retrofit2.Response;

public class LoginToApp extends AsyncTask<String, Integer, UserModel> {

    private String email, password;

    public LoginToApp(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    protected UserModel doInBackground(String... strings) {
        UserModel userModel = new UserModel(email, password);
        LoginService loginService = new LoginInterceptor().postlogin();
        Call<UserResponseOne> userResponseOneCall = loginService.login(userModel);
        try {
            Response<UserResponseOne> response = userResponseOneCall.execute();
            if (200 == response.code() && response.isSuccessful()){
                UserResponseOne userResponseOne = response.body();
                Log.d("LOGIN_TASK", String.valueOf(response.body()));
                UserModel sessionUser = userResponseOne.getUser();

                return sessionUser;
            } else {
                switch (response.code()){
                    case 401:
                        break;
                    case 422:
                        break;

                }
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

