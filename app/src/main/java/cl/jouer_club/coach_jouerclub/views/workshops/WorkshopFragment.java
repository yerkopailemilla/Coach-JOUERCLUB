package cl.jouer_club.coach_jouerclub.views.workshops;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.adapters.WorkshopsAdapter;
import cl.jouer_club.coach_jouerclub.api.workshops.getAll.GetWorkshops;
import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopModel;
import cl.jouer_club.coach_jouerclub.views.workshops.create_workshops.CreateWorkshopActivity;

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
        new DisplayWorkshops(getContext()).execute();
    }

    private class DisplayWorkshops extends GetWorkshops implements WorkshopClickedListener {

        private ProgressDialog dialog;

        public DisplayWorkshops(Context context) {
            super(context);
        }

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
            if (workshopModels != null && workshopModels.size() > 0){
                WorkshopsAdapter workshopsAdapter = new WorkshopsAdapter(workshopModels, this);
                recylerVWorkshops.setAdapter(workshopsAdapter);
                workshopsAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        }


        @Override
        public void workshopClicked(WorkshopModel workshopModel) {
            Intent goToDetail = new Intent(getContext(), DetailWorkshopActivity.class);
            goToDetail.putExtra(DetailWorkshopActivity.WORKSHOP, workshopModel);
            startActivity(goToDetail);
        }
    }

}
