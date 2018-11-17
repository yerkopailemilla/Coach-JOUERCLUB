package cl.jouer_club.coach_jouerclub.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponseOne {

    @SerializedName("data")
    @Expose
    private UserModel user = null;

    public UserResponseOne() {
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
