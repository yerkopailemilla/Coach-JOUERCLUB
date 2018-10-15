package cl.jouer_club.coach_jouerclub.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.models.WorkshopModel;

public class WorkshopsAdapter extends RecyclerView.Adapter<WorkshopsAdapter.WorkshopsViewHolder>{

    private WorkshopModel[] workshops;

    public WorkshopsAdapter(WorkshopModel[] workshops) {
        this.workshops = workshops;
    }

    @NonNull
    @Override
    public WorkshopsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_workshop, parent, false);
        return new WorkshopsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkshopsViewHolder holder, int position) {
        String name = workshops[position].getNombre();
        String description = workshops[position].getDescripcion();

        holder.workshop_name_tv.setText(name);
        holder.workshop_description_tv.setText(description);

    }

    @Override
    public int getItemCount() {
        return workshops.length;
    }

    public class WorkshopsViewHolder extends RecyclerView.ViewHolder{

        public TextView workshop_name_tv, workshop_description_tv;

        public WorkshopsViewHolder(View itemView) {
            super(itemView);

            workshop_name_tv = itemView.findViewById(R.id.workshop_name_tv);
            workshop_description_tv = itemView.findViewById(R.id.workshop_description_tv);
        }
    }


}


