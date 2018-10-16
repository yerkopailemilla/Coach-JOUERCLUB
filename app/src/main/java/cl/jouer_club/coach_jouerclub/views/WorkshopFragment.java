package cl.jouer_club.coach_jouerclub.views;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.adapters.WorkshopsAdapter;
import cl.jouer_club.coach_jouerclub.api.workshops.GetWorkshops;
import cl.jouer_club.coach_jouerclub.api.workshops.WorkshopService;
import cl.jouer_club.coach_jouerclub.api.workshops.WorkshopsInterceptor;
import cl.jouer_club.coach_jouerclub.models.WorkshopModel;
import cl.jouer_club.coach_jouerclub.models.WorkshopResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkshopFragment extends Fragment {

    private RecyclerView recylerVWorkshops;

    public WorkshopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workshop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recylerVWorkshops = view.findViewById(R.id.recylerVWorkshops);

        /*WorkshopService workshopService = new WorkshopsInterceptor().get();
        Call<WorkshopResponse> workshopResponseCall = workshopService.getWorkshops();
        workshopResponseCall.enqueue(new Callback<WorkshopResponse>() {
            @Override
            public void onResponse(Call<WorkshopResponse> call, Response<WorkshopResponse> response) {
                if (response.isSuccessful()){
                    WorkshopResponse workshopResponse = response.body();
                    List<WorkshopModel> workshopModels = workshopResponse.getWorkshops();
                    recylerVWorkshops.setHasFixedSize(true);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recylerVWorkshops.setLayoutManager(linearLayoutManager);
                    WorkshopsAdapter adapter = new WorkshopsAdapter(workshopModels);
                    recylerVWorkshops.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<WorkshopResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Falló", Toast.LENGTH_SHORT).show();

            }
        });*/

        new DisplayWorkshops().execute();
    }

    private class DisplayWorkshops extends GetWorkshops {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(getContext());
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            dialog.setProgress(values[1]);
        }

        @Override
        protected void onPostExecute(List<WorkshopModel> workshopModels) {
            recylerVWorkshops.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recylerVWorkshops.setLayoutManager(linearLayoutManager);
            WorkshopsAdapter workshopsAdapter = new WorkshopsAdapter(workshopModels);
            recylerVWorkshops.setAdapter(workshopsAdapter);
            dialog.dismiss();
            workshopsAdapter.notifyDataSetChanged();
            Log.d("WORKSHOPS", String.valueOf(workshopModels.size()));
        }
    }

}
