package ma.ensa.mecro__learning.model;

import com.google.gson.annotations.SerializedName;

public class Course {
    @SerializedName("id")

    private int id;

    @SerializedName("nom")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("categoryId")
    private int categoryId;

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
