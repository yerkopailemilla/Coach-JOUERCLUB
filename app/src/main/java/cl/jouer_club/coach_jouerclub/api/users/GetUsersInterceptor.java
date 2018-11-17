package cl.jouer_club.coach_jouerclub.api.users;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cl.jouer_club.coach_jouerclub.BuildConfig;
import cl.jouer_club.coach_jouerclub.api.workshops.getAll.WorkshopService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetUsersInterceptor {

    public static final String BASE_URL = "https://www.jouer-club.cl/";
    public static final String API_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6Ijk3MDQzMGE0OGVkYWUyMWU4ODQ1ZjgyZTMzNjdjNjk5NTkyZGQ5YzczNTM3MDljNWZiMTdhYzFhOTcyZjkzYWNiYTliZmYxMGFmN2UxZDYwIn0.eyJhdWQiOiIxIiwianRpIjoiOTcwNDMwYTQ4ZWRhZTIxZTg4NDVmODJlMzM2N2M2OTk1OTJkZDljNzM1MzcwOWM1ZmIxN2FjMWE5NzJmOTNhY2JhOWJmZjEwYWY3ZTFkNjAiLCJpYXQiOjE1Mzk4ODgyNzEsIm5iZiI6MTUzOTg4ODI3MSwiZXhwIjoxNTQyNDgwMjcxLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.bqdC8XmYZ1pfbgsn-Gw8kzQU1jIlmRimjVulhIi87AfNx6ByUxdkBs3ogZ4bko4WVL8_57TzfBzYM0MTbJLhcDHXi-89H8z1yiT8CjLi0TwIOHorMKnsNB01tpnqpJHEUmHlNYDb-LXyp-I6b8OpT-o5Sy26gwkbY3qZGnjsvNwecer6gCDettvR4wZ9Sc6pkXMVeIHPB6cQduQWURpJ2c-ULQ1bkPGHF7seLrZX8Syg4-tQoZfEKkpWi0AmLA_4R7MbLjAsXSDNrY_JpjOTOBp_i6yQJankNarusnnCnW9nJCLr6kG27_H-hX5k2yYAGZfMJhU3hfBsZ2zA5AAr5Xj6V0NGD_BIjQG5cdlmaYVl5KUL_7LK7dy0NaMiZ5MWJG2suiIJCYPq7-TaDQFBm9KE9Ax1RbIsrMgVmJxkuaHeqFCZaCz_XXwZvesadh-bAlafGFTMy6eSSIGggPmN6VngI6BpITT6aeNGLXK9B6crz0nEuYTVzIjSLdBVqS3G62DqvBRhoeS_Ks7-cfhl4BLUy9pBCx4DbfQ5DPwOecec0lhL6st4LrouDr8i_aq2aQ-M9kZxZBM0MD22fg0dXuLkrqczmht1H7zCWEufu14MSuYsbTrI6BQ7qFVkHbvX-OViY4sSbKVGeWWZBH1RpSD6_2pQkbGMnIQHf1q_Ncs";

    public UserService get(){
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

        UserService service = interceptor.create(UserService.class);
        return service;
    }
}
