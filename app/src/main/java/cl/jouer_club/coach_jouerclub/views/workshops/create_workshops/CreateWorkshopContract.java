package cl.jouer_club.coach_jouerclub.views.workshops.create_workshops;

import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopModel;

public interface CreateWorkshopContract {

    interface View {
        void createSuccess();
        void createFailed(String error);
    }

    interface Presenter {
        void createWorkshop(String name,
                            String description,
                            int players,
                            String lat,
                            String lng,
                            String begin,
                            String end,
                            String status,
                            int coach);
    }

}
