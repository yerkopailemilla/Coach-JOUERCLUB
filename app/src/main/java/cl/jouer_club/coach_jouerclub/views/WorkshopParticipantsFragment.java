package cl.jouer_club.coach_jouerclub.views;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.adapters.WorkshopParticipantsAdapter;
import cl.jouer_club.coach_jouerclub.api.users.GetUsersTask;
import cl.jouer_club.coach_jouerclub.models.user.UserModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkshopParticipantsFragment extends Fragment {

    private static final String WORKSHOP_ID = "cl.jouer_club.coach_jouerclub.views.KEY.WORKSHOP_ID";
    private int workshopId;
    private RecyclerView recyclerWorkshopParticipants;


    public static WorkshopParticipantsFragment newInstance(int workshopId) {
        WorkshopParticipantsFragment workshopParticipantsFragment = new WorkshopParticipantsFragment();
        Bundle args = new Bundle();
        args.putInt(WORKSHOP_ID, workshopId);
        workshopParticipantsFragment.setArguments(args);
        return workshopParticipantsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            workshopId = getArguments().getInt(WORKSHOP_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workshop_participants, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerWorkshopParticipants = view.findViewById(R.id.recylerVWorkshopParticipants);
        new ShowUsers(2).execute();
    }

    public class ShowUsers extends GetUsersTask {

        public ShowUsers(int workshopId) {
            super(workshopId);
        }

        @Override
        protected void onPostExecute(List<UserModel> userModels) {
            recyclerWorkshopParticipants.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerWorkshopParticipants.setLayoutManager(linearLayoutManager);
            if (userModels != null && userModels.size() > 0){
                WorkshopParticipantsAdapter workshopParticipantsAdapter = new WorkshopParticipantsAdapter(userModels);
                recyclerWorkshopParticipants.setAdapter(workshopParticipantsAdapter);
                workshopParticipantsAdapter.notifyDataSetChanged();
            }
        }
    }


}
