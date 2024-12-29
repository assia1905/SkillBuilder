package ma.ensa.mecro__learning.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.ensa.mecro__learning.R;
import ma.ensa.mecro__learning.adapter.CourseAdapter;
import ma.ensa.mecro__learning.model.Course;
import ma.ensa.mecro__learning.network.ApiService;
import ma.ensa.mecro__learning.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCourses;
    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        recyclerViewCourses = findViewById(R.id.recyclerViewCourses);
        recyclerViewCourses.setLayoutManager(new LinearLayoutManager(this));

        // Initialiser l'adaptateur
        courseAdapter = new CourseAdapter();
        recyclerViewCourses.setAdapter(courseAdapter);

        // Configurer le listener de clic
        courseAdapter.setOnItemClickListener(course -> {
            // Navigation vers une autre activité avec les détails du cours
            Intent intent = new Intent(CourseActivity.this, LessonActivity.class);
            intent.putExtra("courseId", course.getId());
            intent.putExtra("courseName", course.getName());
            startActivity(intent);
        });

        // Récupérer l'ID de la catégorie depuis l'Intent
        int categoryId = getIntent().getIntExtra("categoryId", -1);
        if (categoryId != -1) {
            loadCourses(categoryId);
        } else {
            Toast.makeText(this, "Aucune catégorie sélectionnée.", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadCourses(int categoryId) {
        ApiService apiService = RetrofitInstance.getApiService();
        Call<List<Course>> call = apiService.getCoursesByCategory(categoryId);

        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    courseAdapter.setCourses(response.body());
                } else {
                    Toast.makeText(CourseActivity.this, "Erreur lors du chargement des cours : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(CourseActivity.this, "Échec de la connexion au serveur", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

