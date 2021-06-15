package com.example.loginpage;

import com.example.loginpage.models.Insignia;
import com.example.loginpage.models.Usuario;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Minimo2API {
    String ENDPOINT = "http://10.0.2.2:8080/myapp/Minimo2/";


    @GET("user/{id}")
    Call<Usuario> getInfoUser(@Path("id") String id);

    @GET("userInsignias/{id}")
    Call<List<Insignia>> getInsigniasByUser(@Path("id") String id);

    @GET("insignias")
    Call<List<Insignia>> getInsignias();
}
