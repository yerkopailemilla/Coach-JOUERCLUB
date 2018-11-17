package cl.jouer_club.coach_jouerclub.api.workshops.getOne;

import android.os.AsyncTask;

import java.io.IOException;

import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopModel;
import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopResponseOne;
import retrofit2.Call;
import retrofit2.Response;

public class GetOneWorkshop extends AsyncTask<String, Integer, WorkshopModel> {

    private int workshopId;

    public GetOneWorkshop(int workshopId) {
        this.workshopId = workshopId;
    }

    @Override
    protected WorkshopModel doInBackground(String... strings) {
        GetOneWorkshopService getOneWorkshopService = new GetOneWorkshopInterceptor().get();
        Call<WorkshopResponseOne> workshopResponseOneCall = getOneWorkshopService.getOne(workshopId);
        try {
            Response<WorkshopResponseOne> response = workshopResponseOneCall.execute();
            if (200 == response.code() && response.isSuccessful()){
                WorkshopResponseOne workshopResponseOne = response.body();
                WorkshopModel workshopModel = workshopResponseOne.getWorkshop();
                return workshopModel;
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
