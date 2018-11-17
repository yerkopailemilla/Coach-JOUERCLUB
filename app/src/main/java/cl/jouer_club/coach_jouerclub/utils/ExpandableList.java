package cl.jouer_club.coach_jouerclub.utils;

import android.view.View;
import android.widget.Toast;

import cl.jouer_club.coach_jouerclub.views.workshops.DetailWorkshopActivity;

public class ExpandableList {

    private View btn, lyt;

    public ExpandableList(View btn, View lyt) {
        this.btn = btn;
        this.lyt = lyt;
    }

    public void toggleSectionPassenger() {
        boolean show = Tools.toggleArrow(btn);
        if (show) {
            ViewAnimation.expand(lyt);
        } else {
            ViewAnimation.collapse(lyt);
        }
    }
}
