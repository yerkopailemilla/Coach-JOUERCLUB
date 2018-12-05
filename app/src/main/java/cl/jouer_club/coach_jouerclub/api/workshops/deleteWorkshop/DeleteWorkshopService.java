package cl.jouer_club.coach_jouerclub.api.workshops.deleteWorkshop;

import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopResponseOne;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface DeleteWorkshopService {

    @DELETE("workshops/{id}")
    Call<WorkshopResponseOne> deleteWoekshop (@Path("id") int workshopId);

}
