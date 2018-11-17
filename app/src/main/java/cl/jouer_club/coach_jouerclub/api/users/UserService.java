package cl.jouer_club.coach_jouerclub.api.users;

import cl.jouer_club.coach_jouerclub.models.user.UserResponseAll;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {

    @GET("workshops/{id}/participants")
    Call<UserResponseAll> getWorkshopParticipants(@Path("id") int workshopId);

}
