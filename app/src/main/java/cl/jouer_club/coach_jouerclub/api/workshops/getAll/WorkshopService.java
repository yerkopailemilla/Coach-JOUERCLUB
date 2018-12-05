package cl.jouer_club.coach_jouerclub.api.workshops.getAll;

import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopResponseAll;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WorkshopService {

    @GET("coaches/{coach}/workshops")
    Call<WorkshopResponseAll> getWorkshops(@Path("coach") int coach);

}
