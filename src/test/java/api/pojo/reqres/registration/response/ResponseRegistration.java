package api.pojo.reqres.registration.response;

import com.google.gson.annotations.SerializedName;

public class ResponseRegistration {

    @SerializedName("id")
    private int id;

    @SerializedName("token")
    private String token;

    @SerializedName("error")
    private String error;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}