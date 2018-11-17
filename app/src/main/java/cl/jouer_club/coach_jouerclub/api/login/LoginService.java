package cl.jouer_club.coach_jouerclub.api.login;

import cl.jouer_club.coach_jouerclub.models.user.UserModel;
import cl.jouer_club.coach_jouerclub.models.user.UserResponseOne;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("login")
    Call<UserResponseOne> login(@Body UserModel userModel);

}
