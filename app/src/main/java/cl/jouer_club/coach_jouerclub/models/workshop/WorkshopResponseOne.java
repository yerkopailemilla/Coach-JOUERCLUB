package cl.jouer_club.coach_jouerclub.models.workshop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopModel;

public class WorkshopResponseOne {

    @SerializedName("data")
    @Expose
    private WorkshopModel workshop = null;

    public WorkshopResponseOne() {
    }

    public WorkshopModel getWorkshop() {
        return workshop;
    }

    public void setWorkshop(WorkshopModel workshop) {
        this.workshop = workshop;
    }
}
