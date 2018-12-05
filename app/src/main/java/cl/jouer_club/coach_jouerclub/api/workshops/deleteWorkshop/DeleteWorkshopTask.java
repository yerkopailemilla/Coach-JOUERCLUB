package cl.jouer_club.coach_jouerclub.api.workshops.deleteWorkshop;

import android.os.AsyncTask;

import java.io.IOException;

import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopModel;
import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopResponseOne;
import retrofit2.Call;
import retrofit2.Response;

public class DeleteWorkshopTask extends AsyncTask<String, Integer, WorkshopModel> {

    private int workshopId;

    public DeleteWorkshopTask(int workshopId) {
        this.workshopId = workshopId;
    }

    @Override
    protected WorkshopModel doInBackground(String... strings) {
        DeleteWorkshopService deleteWorkshopService = new DeleteWorkshopInterceptor().get();
        Call<WorkshopResponseOne> workshopResponseOneCall = deleteWorkshopService.deleteWoekshop(workshopId);
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
        }    }
}
