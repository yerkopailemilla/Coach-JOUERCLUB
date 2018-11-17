package cl.jouer_club.coach_jouerclub.api.users;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import cl.jouer_club.coach_jouerclub.models.user.UserModel;
import cl.jouer_club.coach_jouerclub.models.user.UserResponseAll;
import retrofit2.Call;
import retrofit2.Response;

public class GetUsersTask extends AsyncTask<String, Integer, List<UserModel>> {

    private int workshopId;

    public GetUsersTask(int workshopId) {
        this.workshopId = workshopId;
    }

    @Override
    protected List<UserModel> doInBackground(String... strings) {
        UserService userService = new GetUsersInterceptor().get();
        Call<UserResponseAll> userResponseAllCall = userService.getWorkshopParticipants(workshopId);
        try {
            Response<UserResponseAll> response = userResponseAllCall.execute();
            if (200 == response.code() && response.isSuccessful()){
                UserResponseAll userResponseAll = response.body();
                List<UserModel> userModels = userResponseAll.getUsers();
                return userModels;
            } else {
                switch (response.code()){
                    case 404:
                        break;

                    default:
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
