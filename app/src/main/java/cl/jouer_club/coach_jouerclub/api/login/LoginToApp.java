package cl.jouer_club.coach_jouerclub.api.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.io.IOException;

import cl.jouer_club.coach_jouerclub.models.user.UserModel;
import cl.jouer_club.coach_jouerclub.models.user.UserResponseOne;
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
                UserModel sessionUser = userResponseOne.getUser();
                return sessionUser;
            } else {
                switch (response.code()){

                }
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

    /*private Context context;
    private int workshopId;

    public RemoveToWorkshop(Context context, int workshopId) {
        this.context = context;
        this.workshopId = workshopId;
    }

    @Override
    protected String doInBackground(String... strings) {
        String jouerId = getFromSharedPreferences("userId");
        RemoveToWorkshopService removeToWorkshopService = new RemoveToWorkshopInterceptor().postRemoveToWorkshop();
        Call<Jouer> stringCall = removeToWorkshopService.removeToWorkshop(jouerId, workshopId);
        try {
            Response<Jouer> response = stringCall.execute();
            if (200 == response.code() && response.isSuccessful()){
                Jouer stringResponse = response.body();
                String notification = stringResponse.getAlias();
                return notification;
            } else {
                switch (response.code()){

                }
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getFromSharedPreferences(String key){
        SharedPreferences preferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }*/
