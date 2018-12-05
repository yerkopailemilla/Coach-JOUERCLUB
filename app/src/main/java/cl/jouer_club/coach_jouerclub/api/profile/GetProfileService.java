package cl.jouer_club.coach_jouerclub.api.profile;

import cl.jouer_club.coach_jouerclub.models.user.UserResponseOne;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetProfileService {

    @GET("coaches/{coach}")
    Call<UserResponseOne> getUserProfile(@Path("coach") int coachId);

}
