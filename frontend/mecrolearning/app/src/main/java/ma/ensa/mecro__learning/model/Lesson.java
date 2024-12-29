package ma.ensa.mecro__learning.model;

import com.google.gson.annotations.SerializedName;

public class Lesson {
    @SerializedName("id")
    private int id;

    @SerializedName("titre")
    private String title;

    @SerializedName("contenu")
    private String content;

    @SerializedName("courseId")
    private int courseId;

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}

