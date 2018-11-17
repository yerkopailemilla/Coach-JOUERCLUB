package cl.jouer_club.coach_jouerclub.api.workshops.getOne;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cl.jouer_club.coach_jouerclub.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static cl.jouer_club.coach_jouerclub.api.workshops.getAll.WorkshopsInterceptor.API_TOKEN;
import static cl.jouer_club.coach_jouerclub.api.workshops.getAll.WorkshopsInterceptor.BASE_URL;

public class GetOneWorkshopInterceptor {

    public GetOneWorkshopService get(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Debug de requests/responses solo en ambiente de desarrollo
        if (BuildConfig.DEBUG){
            httpClient.addInterceptor(logging);
        }

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization", "Bearer " + API_TOKEN)
                        .build();

                Response response = chain.proceed(request);

                return response;
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        GetOneWorkshopService service = interceptor.create(GetOneWorkshopService.class);
        return service;
    }

}
