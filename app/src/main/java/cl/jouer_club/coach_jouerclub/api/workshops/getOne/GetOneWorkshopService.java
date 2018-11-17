package cl.jouer_club.coach_jouerclub.api.workshops.getOne;

import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopResponseOne;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetOneWorkshopService {

    @GET("workshops/{id}")
    Call<WorkshopResponseOne> getOne(@Path("id") int workshopId);
}
