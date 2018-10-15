package cl.jouer_club.coach_jouerclub.views;


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

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.adapters.WorkshopsAdapter;
import cl.jouer_club.coach_jouerclub.api.workshops.GetWorkshops;
import cl.jouer_club.coach_jouerclub.models.WorkshopModel;

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workshop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recylerVWorkshops = view.findViewById(R.id.recylerVWorkshops);
        recylerVWorkshops.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recylerVWorkshops.setLayoutManager(linearLayoutManager);

        new DisplayWorkshops().execute();
    }

    private class DisplayWorkshops extends GetWorkshops {

        @Override
        protected void onPostExecute(WorkshopModel[] workshopModels) {
            WorkshopsAdapter adapter = new WorkshopsAdapter(workshopModels);
            recylerVWorkshops.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            Log.d("WORKSHOPS", String.valueOf(workshopModels.length));        }
    }

}
