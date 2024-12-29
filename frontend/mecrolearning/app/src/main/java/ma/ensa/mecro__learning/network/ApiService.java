package ma.ensa.mecro__learning.network;

import ma.ensa.mecro__learning.model.Category;
import ma.ensa.mecro__learning.model.Course;
import ma.ensa.mecro__learning.model.Lesson;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface ApiService {

    // Récupérer toutes les catégories
    @GET("categories")
    Call<List<Category>> getCategories();

    // Récupérer les cours associés à une catégorie spécifique par ID
    @GET("categories/{categoryId}/cours")
    Call<List<Course>> getCoursesByCategory(@Path("categoryId") int categoryId);

    // Récupérer les leçons associées à un cours par ID
    @GET("cours/{courseId}/lecons")
    Call<List<Lesson>> getLessonsByCourse(@Path("courseId") int courseId);

    // Ajouter une nouvelle catégorie
    @POST("categories")
    Call<Category> addCategory(@Body Category category);

    // Ajouter un nouveau cours
    @POST("cours")
    Call<Course> addCourse(@Body Course course);

    // Ajouter une nouvelle leçon
    @POST("lecons")
    Call<Lesson> addLesson(@Body Lesson lesson);

}
