package ma.ensa.mecro__learning.network;
import ma.ensa.mecro__learning.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi {
    @GET("/user/{userId}")
    Call<User> getUserDetails(@Path("userId") int userId);

    Call<User> updateUserDetails(User updatedUser);
}
