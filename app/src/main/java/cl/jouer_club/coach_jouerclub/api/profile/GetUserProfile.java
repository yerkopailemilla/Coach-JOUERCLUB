package cl.jouer_club.coach_jouerclub.api.profile;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

import cl.jouer_club.coach_jouerclub.data.SessionsPreferences;
import cl.jouer_club.coach_jouerclub.models.user.UserModel;
import cl.jouer_club.coach_jouerclub.models.user.UserResponseOne;
import retrofit2.Call;
import retrofit2.Response;

public class GetUserProfile extends AsyncTask<String, Integer, UserModel> {

    private Context context;

    public GetUserProfile(Context context) {
        this.context = context;
    }

    @Override
    protected UserModel doInBackground(String... strings) {
        int coach = new SessionsPreferences(context).getSessionId();

        GetProfileService getProfileService = new GetProfileInterceptor().get();
        Call<UserResponseOne> userResponseOneCall = getProfileService.getUserProfile(coach);
        try {
            Response<UserResponseOne> response = userResponseOneCall.execute();
            if (200 == response.code() && response.isSuccessful()){
                UserResponseOne userResponseOne = response.body();
                UserModel userModel = userResponseOne.getUser();
                return userModel;
            } else {
                switch (response.code()){

                }
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }    }
}
