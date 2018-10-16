package cl.jouer_club.coach_jouerclub.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WorkshopResponse {

    @SerializedName("data")
    @Expose
    private List<WorkshopModel> workshops = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public WorkshopResponse() {
    }

    public List<WorkshopModel> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<WorkshopModel> workshops) {
        this.workshops = workshops;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
