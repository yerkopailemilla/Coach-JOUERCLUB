package cl.jouer_club.coach_jouerclub.api.workshops.get;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cl.jouer_club.coach_jouerclub.BuildConfig;
import cl.jouer_club.coach_jouerclub.api.workshops.get.WorkshopService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WorkshopsInterceptor {

    private static final String BASE_URL = "https://www.jouer-club.cl/";
    public static final String API_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjVjMDhmZTY5ZDNlZjYwMGM0YzUyYzQ0YzAyNjMzMWVlNTI3MTkxMjc2ODg0YjQwMTk3MDY1MDRhZjlmYzYxNDgzYTQ1ZGQxYmM4ZmE5MWY2In0.eyJhdWQiOiIzIiwianRpIjoiNWMwOGZlNjlkM2VmNjAwYzRjNTJjNDRjMDI2MzMxZWU1MjcxOTEyNzY4ODRiNDAxOTcwNjUwNGFmOWZjNjE0ODNhNDVkZDFiYzhmYTkxZjYiLCJpYXQiOjE1MzkxNDU1MjcsIm5iZiI6MTUzOTE0NTUyNywiZXhwIjoxNTQxNzM3NTI3LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.Yjdg730T-g_fbm94cFEVE1WvYORjujTlgq4fKLN-_o7_xCYtUsGaEgtMizzQWXU8lvLKEFCQjGXoovnbwxg3bjhI5NeA6B4LOFldDJcmEdDuLv2Gef3hm8kizts1pBVvirP_LB2MhkVBaCydEDEBiH7MX9h4NziFnTFAGYiR_OVZ-QrC-Nm1x94lwLslz9S5sFcRgl8NAWNfS_y87m-8MEzc_Yscxutv9cjQnE-puQ4xI7hpp5r3JzU1LkdvONqr-O8ECTD5UAtVKlsM7zU0mnm58rdCpTZTPwSCsk_8EkRSy8oqM3WkQ6d8yEFZYw8hpLyT-akVWyyaK1NblgPvrU3AWEPsv9qJdpkTB8PndV4JuK-XmYP0GuVbNzhIKEms6u_eclUEJeD4R8coskWgUKRIZ8FKfvmmQWTJB_I62dbknPVloWTP-adlu0zY9CeaNyyby5toC67AbAffFjat9YZSxkU8aUnWMoIet7ydm4q12BdwsT6uE1MEZe-rTVt9IBg3QJ_P013WCRfaKg3l2fXfnD9DAtpI5t9OtsEuCcKryLKweo5QttcDMltk9DZyWarmNb-mmYXLT6NL4ivFXDYKbvldiN6SZKmGWRB-echUyhzmxaEI6ks_TClDpXJQky70dXCfPboaxuwUM6wrmB1Kp5gS8_k4gDF_Zv5vcvQ";

    public WorkshopService get(){
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

        WorkshopService service = interceptor.create(WorkshopService.class);
        return service;
    }

}


