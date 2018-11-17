package cl.jouer_club.coach_jouerclub.api.workshops.getAll;

import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopResponseAll;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WorkshopService {

    @GET("workshops")
    Call<WorkshopResponseAll> getWorkshops();

}
