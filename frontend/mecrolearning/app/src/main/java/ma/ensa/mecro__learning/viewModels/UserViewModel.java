package ma.ensa.mecro__learning.viewModels;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ma.ensa.mecro__learning.model.User;
import ma.ensa.mecro__learning.network.ApiClient;
import ma.ensa.mecro__learning.network.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> updateStatus = new MutableLiveData<>();
    private final UserApi userApi = ApiClient.getClient().create(UserApi.class);

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Boolean> getUpdateStatus() {
        return updateStatus;
    }

    public void fetchUserDetails(int userId) {
        isLoading.setValue(true);
        userApi.getUserDetails(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                isLoading.setValue(false);
                if (response.isSuccessful()) {
                    userLiveData.setValue(response.body());
                } else {
                    userLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                isLoading.setValue(false);
                userLiveData.setValue(null);
            }
        });
    }

    // Méthode pour mettre à jour les informations de l'utilisateur
    public LiveData<Boolean> updateUserProfile(String name, String email, String phone) {
        isLoading.setValue(true);
        User updatedUser = new User(name, email, phone); // Supposons que vous avez un constructeur User qui prend ces paramètres.

        userApi.updateUserDetails(updatedUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                isLoading.setValue(false);
                if (response.isSuccessful()) {
                    updateStatus.setValue(true);  // Mise à jour réussie
                } else {
                    updateStatus.setValue(false); // Échec de la mise à jour
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                isLoading.setValue(false);
                updateStatus.setValue(false); // Échec de la mise à jour
            }
        });

        return updateStatus;
    }
}
