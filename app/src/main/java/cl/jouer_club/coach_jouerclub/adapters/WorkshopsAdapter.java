package cl.jouer_club.coach_jouerclub.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopModel;
import cl.jouer_club.coach_jouerclub.views.workshops.WorkshopClickedListener;

public class WorkshopsAdapter extends RecyclerView.Adapter<WorkshopsAdapter.WorkshopsViewHolder>{

    private List<WorkshopModel> workshops;
    private WorkshopClickedListener workshopClickedListener;

    public WorkshopsAdapter(List<WorkshopModel> workshops, WorkshopClickedListener workshopClickedListener) {
        this.workshops = workshops;
        this.workshopClickedListener = workshopClickedListener;
    }

    @NonNull
    @Override
    public WorkshopsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_workshop, parent, false);
        return new WorkshopsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WorkshopsViewHolder holder, int position) {
        String name = workshops.get(position).getNombre();
        String description = workshops.get(position).getDescripcion();

        holder.workshop_name_tv.setText(name);
        holder.workshop_description_tv.setText(description);

        holder.workshop_detail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int auxPosition = holder.getAdapterPosition();
                WorkshopModel auxWorkshop = workshops.get(auxPosition);
                workshopClickedListener.workshopClicked(auxWorkshop);
            }
        });

    }

    @Override
    public int getItemCount() {
        return workshops.size();
    }

    public class WorkshopsViewHolder extends RecyclerView.ViewHolder{

        public TextView workshop_name_tv, workshop_description_tv;
        public Button workshop_detail_btn;

        public WorkshopsViewHolder(View itemView) {
            super(itemView);

            workshop_name_tv = itemView.findViewById(R.id.workshop_name_tv);
            workshop_description_tv = itemView.findViewById(R.id.workshop_description_tv);
            workshop_detail_btn = itemView.findViewById(R.id.workshop_detail_btn);
        }
    }


}


