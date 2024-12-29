package ma.ensa.mecro__learning.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.ensa.mecro__learning.R;
import ma.ensa.mecro__learning.adapter.CategoryAdapter;
import ma.ensa.mecro__learning.model.Category;
import ma.ensa.mecro__learning.network.ApiService;
import ma.ensa.mecro__learning.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCategories;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerViewCategories = findViewById(R.id.recyclerViewCategories);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this));

        // Initialiser l'adaptateur
        categoryAdapter = new CategoryAdapter();
        recyclerViewCategories.setAdapter(categoryAdapter);

        // Gérer les clics sur les catégories
        categoryAdapter.setOnItemClickListener(category -> {
            Intent intent = new Intent(CategoryActivity.this, CourseActivity.class);
            intent.putExtra("categoryId", category.getId()); // Passer l'ID de la catégorie
            startActivity(intent);
        });

        // Charger les catégories depuis l'API
        loadCategories();
    }

    private void loadCategories() {
        ApiService apiService = RetrofitInstance.getApiService();
        Call<List<Category>> call = apiService.getCategories();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> categories = response.body();

                    // Loguer les données des catégories
                    for (Category category : categories) {
                        Log.d("Category", "ID: " + category.getId() + ", Name: " + category.getName() + ", Description: " + category.getDescription());
                    }

                    categoryAdapter.setCategories(categories);  // Mettre à jour l'adaptateur
                } else {
                    Toast.makeText(CategoryActivity.this, "Erreur lors du chargement des catégories", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "Échec de la connexion au serveur", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
