package ma.ensa.mecro__learning.ui;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ma.ensa.mecro__learning.R;
import ma.ensa.mecro__learning.model.User;
import ma.ensa.mecro__learning.network.ApiClient;
import ma.ensa.mecro__learning.network.AuthApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private AuthApi authApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialisation des vues
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);

        // Initialisation de l'API
        authApi = ApiClient.getClient().create(AuthApi.class);

        // Gestion du clic sur le bouton de connexion
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                loginUser(email, password);
            }
        });
    }

    private void loginUser(String email, String password) {
        // Afficher la barre de progression
        progressBar.setVisibility(View.VISIBLE);

        // Appel API pour se connecter
        User user = new User(email, password);
        Call<User> call = authApi.login(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    Toast.makeText(LoginActivity.this, "Bienvenue " + user.getName(), Toast.LENGTH_SHORT).show();

                    // Rediriger vers DashboardActivity
                    Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                    intent.putExtra("user", user); // Passer l'objet sérialisé
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Échec de la connexion. Vérifiez vos informations.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Erreur : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
