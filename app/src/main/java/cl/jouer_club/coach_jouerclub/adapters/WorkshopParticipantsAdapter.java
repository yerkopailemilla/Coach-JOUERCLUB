package cl.jouer_club.coach_jouerclub.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.models.user.UserModel;

public class WorkshopParticipantsAdapter extends RecyclerView.Adapter<WorkshopParticipantsAdapter.WorkshopParticipantsHolder> {

    private List<UserModel> users;

    public WorkshopParticipantsAdapter(List<UserModel> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public WorkshopParticipantsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_workshop_participants, parent, false);
        return new WorkshopParticipantsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkshopParticipantsHolder holder, int position) {
        String alias = users.get(position).getAlias();
        String email = users.get(position).getCorreo();

        holder.txtJouerAlias.setText(alias);
        holder.txtJouerEmail.setText(email);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class WorkshopParticipantsHolder extends RecyclerView.ViewHolder {

        private TextView txtJouerAlias, txtJouerEmail;

        public WorkshopParticipantsHolder(View itemView) {
            super(itemView);
            txtJouerAlias = itemView.findViewById(R.id.txtJouerAlias);
            txtJouerEmail = itemView.findViewById(R.id.txtJouerMail);
        }
    }
}
