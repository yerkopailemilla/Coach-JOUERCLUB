package cl.jouer_club.coach_jouerclub.api.workshops;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import cl.jouer_club.coach_jouerclub.models.WorkshopModel;
import cl.jouer_club.coach_jouerclub.models.WorkshopResponse;
import retrofit2.Call;
import retrofit2.Response;

public class GetWorkshops extends AsyncTask<String, Integer, List<WorkshopModel>> {

    @Override
    protected List<WorkshopModel> doInBackground(String... strings) {
        WorkshopService workshopService = new WorkshopsInterceptor().get();
        Call<WorkshopResponse> workshopResponseCall = workshopService.getWorkshops();
        try {
            Response<WorkshopResponse> response = workshopResponseCall.execute();
            if (200 == response.code() && response.isSuccessful()){
                WorkshopResponse workshopResponse = response.body();
                List<WorkshopModel> workshopModels = workshopResponse.getWorkshops();
                return workshopModels;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }    }
}
