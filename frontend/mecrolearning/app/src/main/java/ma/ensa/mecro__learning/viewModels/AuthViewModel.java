package ma.ensa.mecro__learning.viewModels;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ma.ensa.mecro__learning.model.User;
import ma.ensa.mecro__learning.network.ApiClient;
import ma.ensa.mecro__learning.network.AuthApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();
    // Utilisez getClient() au lieu de getRetrofitInstance()
    private final AuthApi authApi = ApiClient.getClient().create(AuthApi.class);

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public void login(User user) {
        authApi.login(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    userLiveData.setValue(response.body());
                } else {
                    userLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userLiveData.setValue(null);
            }
        });
    }
}
