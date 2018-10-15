package cl.jouer_club.coach_jouerclub.api.workshops;

import cl.jouer_club.coach_jouerclub.models.WorkshopModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WorkshopService {

    @GET("workshops")
    Call<WorkshopModel[]> getWorkshops();

}
