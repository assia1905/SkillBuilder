package ma.ensa.mecro__learning.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.ensa.mecro__learning.R;
import ma.ensa.mecro__learning.adapter.LessonAdapter;
import ma.ensa.mecro__learning.model.Lesson;
import ma.ensa.mecro__learning.network.ApiService;
import ma.ensa.mecro__learning.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLessons;
    private LessonAdapter lessonAdapter;
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        recyclerViewLessons = findViewById(R.id.recyclerViewLessons);
        recyclerViewLessons.setLayoutManager(new LinearLayoutManager(this));

        // Initialiser l'adaptateur
        lessonAdapter = new LessonAdapter();
        recyclerViewLessons.setAdapter(lessonAdapter);

        // Récupérer l'ID du cours depuis l'intent
        courseId = getIntent().getIntExtra("courseId", -1);

        // Charger les leçons
        loadLessons();
    }

    private void loadLessons() {
        ApiService apiService = RetrofitInstance.getApiService();
        Call<List<Lesson>> call = apiService.getLessonsByCourse(courseId);

        call.enqueue(new Callback<List<Lesson>>() {
            @Override
            public void onResponse(Call<List<Lesson>> call, Response<List<Lesson>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("LessonActivity", "Leçons reçues : " + response.body().size());
                    lessonAdapter.setLessonList(response.body());
                } else {
                    Log.e("LessonActivity", "Erreur API : " + response.code());
                    Toast.makeText(LessonActivity.this, "Erreur lors du chargement des leçons", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<Lesson>> call, Throwable t) {
                Toast.makeText(LessonActivity.this, "Échec de la connexion au serveur", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
