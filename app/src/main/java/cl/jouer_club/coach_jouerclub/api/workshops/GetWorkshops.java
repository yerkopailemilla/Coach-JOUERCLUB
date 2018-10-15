package cl.jouer_club.coach_jouerclub.api.workshops;

import android.os.AsyncTask;

import java.io.IOException;

import cl.jouer_club.coach_jouerclub.models.WorkshopModel;
import retrofit2.Call;
import retrofit2.Response;

public class GetWorkshops extends AsyncTask<String, Void, WorkshopModel[]> {

    @Override
    protected WorkshopModel[] doInBackground(String... strings) {
        WorkshopService workshopService = new WorkshopsInterceptor().get();
        Call<WorkshopModel[]> call = workshopService.getWorkshops();

        try {

            Response<WorkshopModel[]> response = call.execute();
            if (200 == response.code() && response.isSuccessful()){
                return response.body();
            } else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
