package cl.jouer_club.coach_jouerclub.views.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.api.login.LoginToApp;
import cl.jouer_club.coach_jouerclub.data.SessionsPreferences;
import cl.jouer_club.coach_jouerclub.models.user.UserModel;
import cl.jouer_club.coach_jouerclub.views.login.contract.LoginValidationContract;
import cl.jouer_club.coach_jouerclub.views.main.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText login_email_tv, login_password_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email_tv = findViewById(R.id.login_email_tv);
        login_password_tv = findViewById(R.id.login_password_tv);
        Button login_signIn_btn = findViewById(R.id.login_signIn_btn);

        login_signIn_btn.setOnClickListener(toMain);
    }

    View.OnClickListener toMain = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email = login_email_tv.getText().toString();
            String password = login_password_tv.getText().toString();

            if (email.trim().length() > 0){
                if (password.trim().length() > 0){
                    new SignIn(email, password).execute();
                } else {
                    Toast.makeText(LoginActivity.this, "Ingresa una contraseña.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Ingresa un correo electrónico.", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private class SignIn extends LoginToApp {

        public SignIn(String email, String password) {
            super(email, password);
        }

        @Override
        protected void onPostExecute(UserModel userModel) {
            if (userModel != null){
                new SessionsPreferences(getApplicationContext()).setUserSession(userModel.getIdentificador(), userModel.getAlias());
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }
    }
}
