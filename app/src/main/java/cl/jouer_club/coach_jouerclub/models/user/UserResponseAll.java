package cl.jouer_club.coach_jouerclub.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cl.jouer_club.coach_jouerclub.models.Meta;

public class UserResponseAll {

    @SerializedName("data")
    @Expose
    private List<UserModel> users = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public UserResponseAll() {
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
