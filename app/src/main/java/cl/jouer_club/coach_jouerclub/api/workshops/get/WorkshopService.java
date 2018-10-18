package cl.jouer_club.coach_jouerclub.api.workshops.get;

import cl.jouer_club.coach_jouerclub.models.WorkshopModel;
import cl.jouer_club.coach_jouerclub.models.WorkshopResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WorkshopService {

    @GET("workshops")
    Call<WorkshopResponse> getWorkshops();

}
