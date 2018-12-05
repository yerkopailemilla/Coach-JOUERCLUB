package cl.jouer_club.coach_jouerclub.views.workshops.create_workshops;

import android.util.Log;
import android.widget.Toast;

import cl.jouer_club.coach_jouerclub.api.workshops.post.CreateWorkshopService;
import cl.jouer_club.coach_jouerclub.api.workshops.post.CreateWorshopInterceptor;
import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateWorkshopPresenter implements CreateWorkshopContract.Presenter {

    private CreateWorkshopContract.View view;

    public CreateWorkshopPresenter(CreateWorkshopContract.View view) {
        this.view = view;
    }

    @Override
    public void createWorkshop(String name, String description, int players, String lat, String lng,
                               String begin, String end, String status, int coach) {

        if (name.trim().length() > 0){
            if (description.trim().length() > 0){
                if (players > 0){
                    if (!begin.contains("FECHA")){
                        WorkshopModel workshopModel = new WorkshopModel(name, description, players, lat, lng, begin, end, status, coach);

                        CreateWorkshopService createWorkshopService = new CreateWorshopInterceptor().postCreateWorkshop();
                        Call<WorkshopModel> workshopModelCall = createWorkshopService.createWorkshop(workshopModel);
                        workshopModelCall.enqueue(new Callback<WorkshopModel>() {
                            @Override
                            public void onResponse(Call<WorkshopModel> call, Response<WorkshopModel> response) {
                                if (response.isSuccessful()){
                                    response.body();
                                    view.createSuccess();
                                }else {
                                    switch (response.code()){
                                        case 403:
                                            view.createFailed("No es posible crear este encuentro. Procura que la fecha sea próxima o bien que la fecha de termino sea posterior a la de inicio.");
//                            Toasty.warning(AddMeetingActivity.this, "No es posible crear este encuentro. Procura que la fecha sea próxima o bien que la fecha de termino sea posterior a la de inicio.", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 500:
                                            view.createFailed("Estamos teniendo problemas para procesar tu solicitud.");
//                            Toasty.error(AddMeetingActivity.this, "Estamos teniendo problemas para procesar tu solicitud.", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 422:
                                            view.createFailed("Verifica que todos los campos estén completos.");
//                            Toasty.warning(AddMeetingActivity.this, "Verifica que todos los campos estén completos.", Toast.LENGTH_SHORT).show();
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<WorkshopModel> call, Throwable t) {
                                Log.d("WORKSHOP", "onFailure: ERROR_ADD_WORKSHOP: " + t.getMessage());
                                view.createFailed("Verifica que todos los campos estén completos.");
//                Toasty.error(AddMeetingActivity.this, "Oh! Algo salió mal. Vuelve a intentarlo.", Toast.LENGTH_LONG).show();

                            }
                        });
                    } else {
                        view.createFailed("Selecciona una fecha y hora");
                    }
                } else {
                    view.createFailed("Debes ingresar una cantidad máxima de participantes");
                }
            } else {
                view.createFailed("Ingresa una descripción para tu taller");
            }
        } else {
            view.createFailed("Ingresa un nombre para tu taller");
        }

    }

}
