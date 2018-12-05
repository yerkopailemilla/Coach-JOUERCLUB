package cl.jouer_club.coach_jouerclub.views.profile;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.api.profile.GetUserProfile;
import cl.jouer_club.coach_jouerclub.data.SessionsPreferences;
import cl.jouer_club.coach_jouerclub.models.user.UserModel;
import cl.jouer_club.coach_jouerclub.views.login.LoginActivity;
import cl.jouer_club.coach_jouerclub.views.main.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView txtNickProfile, txtEmailProfile, txtPhoneProfile, txtSinceProfile;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtNickProfile = view.findViewById(R.id.txtNickProfile);
        txtEmailProfile = view.findViewById(R.id.txtEmailProfile);
        txtPhoneProfile = view.findViewById(R.id.txtPhoneProfile);
        txtSinceProfile = view.findViewById(R.id.txtSinceProfile);

        new DisplayUserInfo(getContext()).execute();

        Button btnLogOut = view.findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(logoutApp);
    }

    View.OnClickListener logoutApp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showLogOutDialog();
        }
    };

    private void showLogOutDialog(){
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Cerrar sesión")
                .setMessage("Estás saliendo de la aplicación, ¿Quieres continuar?. Si aceptas, tendrás que volver a logearte.")
                .setPositiveButton("Salir de la app", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new SessionsPreferences(getContext()).removeUserSession();

                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.red_400));
    }

    public class DisplayUserInfo extends GetUserProfile {

        private ProgressDialog dialog;

        public DisplayUserInfo(Context context) {
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
        protected void onPostExecute(UserModel userModel) {
            if (userModel != null){
                txtNickProfile.setText(userModel.getAlias());
                txtEmailProfile.setText(userModel.getCorreo());
                txtPhoneProfile.setText(userModel.getCelular());
                txtSinceProfile.setText(userModel.getFechaCreacion());
                dialog.dismiss();
            }
        }
    }
}


