package cl.jouer_club.coach_jouerclub.api.workshops.post;

import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CreateWorkshopService {

    @POST("workshop")
    Call<WorkshopModel> createWorkshop(@Body WorkshopModel workshopModel);

}
