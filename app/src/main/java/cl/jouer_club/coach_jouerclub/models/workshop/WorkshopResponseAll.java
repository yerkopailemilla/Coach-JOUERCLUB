package cl.jouer_club.coach_jouerclub.models.workshop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cl.jouer_club.coach_jouerclub.models.Meta;
import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopModel;

public class WorkshopResponseAll {

    @SerializedName("data")
    @Expose
    private List<WorkshopModel> workshops = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public WorkshopResponseAll() {
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
