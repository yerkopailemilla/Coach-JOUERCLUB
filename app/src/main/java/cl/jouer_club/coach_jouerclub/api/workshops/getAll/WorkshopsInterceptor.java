package cl.jouer_club.coach_jouerclub.api.workshops.getAll;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cl.jouer_club.coach_jouerclub.BuildConfig;
import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WorkshopsInterceptor {

    public static final String BASE_URL = "https://www.jouer-club.cl/";
    public static final String API_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImRmMjZkMmI0OTM5MDJiZTVhM2RmNjQzZDNjNjAyOTNmYmQ0ZmQ4YzAzY2ZjM2Q2NzQ5N2ZiODIwNDdmNTNiNDljZDEzODZkOGZmMzVhNGNlIn0.eyJhdWQiOiIxIiwianRpIjoiZGYyNmQyYjQ5MzkwMmJlNWEzZGY2NDNkM2M2MDI5M2ZiZDRmZDhjMDNjZmMzZDY3NDk3ZmI4MjA0N2Y1M2I0OWNkMTM4NmQ4ZmYzNWE0Y2UiLCJpYXQiOjE1NDIyMzE4MjcsIm5iZiI6MTU0MjIzMTgyNywiZXhwIjoxNTQ0ODIzODI3LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.nx9Dhe1zlSPkD28qRiHFmwXsS8AhPp_RO3HEzNQ4IB2QcitYvid8-1aQqjD5eUghCRtAZleyqn2sL9RCxBcSBHh27wNYXVsSTl-XizsZniBrIlVGpMx4Ka6zY_GMMDMAmZ90acJrrTSHOzUUMHiqEXrxzE2droPuLK9IFd_TyBEwA0OAGo9_xzPz9F_qcEz1mYhzW5NxbWhMITdntfmTa31beU4zVeV5SaCJlBxMGwuzlx4irEonnNm5URGugBeccu-19J43Ww51C0kyN7XCInx_ymrlU10tJMkDJP-fg9GhHsoz6oeRG7M947SFF-EuXtjNO-VqwtSZkf0JDB_EoodtwQtlry9ddgUOeJSF9XDcwDkEDpecBuy6aMfvn2Cfk2b4h4zbIgBi9Gukg177LnbMqL7Wm2PpPT9OFLGgL1ZS-Fd2gwoWUMq1vsUfvInBJQ4XFEiMiKNfw8oRII_cn87vSTeDZ2U4fE-P1fVN4wT6T_jV72WlUuMmmhZCboHjfqEBwvrGWTxGSO42MNjJq5r7TQGurgYurSM9eQ-sE5-iedu3uB2byp84feC_eivM7hltrDUlitfCxneiylXoe3SH_aXAnfLXFzS2vGN1qBF_97CzKWniEN-3D8ZG4OsKqXfJiwxWBytHoUvIRJThXyd8bx0PvsNUkaCDL0z4Thg";

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


