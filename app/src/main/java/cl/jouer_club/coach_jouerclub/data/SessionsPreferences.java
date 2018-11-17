package cl.jouer_club.coach_jouerclub.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionsPreferences {

    private Context context;
    private final String USER_SESSION = "cl.jouer_club.coach_jouerclub.data.GROUP_KEY.USER_SESSION";
    private final String USER_SESSION_ID = "cl.jouer_club.coach_jouerclub.data.KEY.USER_SESSION_ID";
    private final String USER_SESSION_NICKNAME = "cl.jouer_club.coach_jouerclub.data.KEY.USER_SESSION_NICKNAME";


    public SessionsPreferences(Context context) {
        this.context = context;
    }

    public void setUserSession(int id, String nickname) {
        SharedPreferences sPref = context.getSharedPreferences(USER_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sPref.edit();
        prefEditor.putInt(USER_SESSION_ID, id);
        prefEditor.putString(USER_SESSION_NICKNAME, nickname);
        prefEditor.commit();
    }

    public int getSessionId() {
        SharedPreferences sPref = context.getSharedPreferences(USER_SESSION, Context.MODE_PRIVATE);
        return sPref.getInt(USER_SESSION_ID, 0);
    }

    public String getSessionNickname() {
        SharedPreferences sPref = context.getSharedPreferences(USER_SESSION, Context.MODE_PRIVATE);
        return sPref.getString(USER_SESSION_NICKNAME, null);
    }
}
