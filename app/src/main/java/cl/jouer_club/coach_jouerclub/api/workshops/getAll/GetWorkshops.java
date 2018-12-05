package cl.jouer_club.coach_jouerclub.api.workshops.getAll;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import cl.jouer_club.coach_jouerclub.data.SessionsPreferences;
import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopModel;
import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopResponseAll;
import retrofit2.Call;
import retrofit2.Response;

public class GetWorkshops extends AsyncTask<String, Integer, List<WorkshopModel>> {

    private Context context;

    public GetWorkshops(Context context) {
        this.context = context;
    }

    @Override
    protected List<WorkshopModel> doInBackground(String... strings) {
        int coach = new SessionsPreferences(context).getSessionId();

        WorkshopService workshopService = new WorkshopsInterceptor().get();
        Call<WorkshopResponseAll> workshopResponseCall = workshopService.getWorkshops(coach);
        try {
            Response<WorkshopResponseAll> response = workshopResponseCall.execute();
            if (200 == response.code() && response.isSuccessful()){
                WorkshopResponseAll workshopResponse = response.body();
                List<WorkshopModel> workshopModels = workshopResponse.getWorkshops();
                return workshopModels;
            } else {
                switch (response.code()){

                }
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
