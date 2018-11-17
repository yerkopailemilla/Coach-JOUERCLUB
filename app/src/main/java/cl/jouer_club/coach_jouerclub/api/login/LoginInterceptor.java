package cl.jouer_club.coach_jouerclub.api.login;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static cl.jouer_club.coach_jouerclub.api.workshops.getAll.WorkshopsInterceptor.API_TOKEN;
import static cl.jouer_club.coach_jouerclub.api.workshops.getAll.WorkshopsInterceptor.BASE_URL;

public class LoginInterceptor {

    public LoginService postlogin() {
    /*Mostly the same of what is done with post, but this time the waiting time for response after post is increase
    and, very important, there are no retry. Here there is a 1 min waiting period, if for any reason the server did
    got processed the request but took 1 min and 1 sec to response, you dont want to retry cause it would create
    another object duplicated. One min waiting time for a server is a lot, it should work with this basis. If it doesnt
    then dont make it worse by doing retry*/
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

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

        LoginService service = interceptor.create(LoginService.class);
        return service;
    }

}
