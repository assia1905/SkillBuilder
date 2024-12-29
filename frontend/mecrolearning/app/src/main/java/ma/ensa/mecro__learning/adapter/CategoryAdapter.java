package ma.ensa.mecro__learning.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ma.ensa.mecro__learning.R;
import ma.ensa.mecro__learning.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories; // Liste des catégories
    @Nullable
    private OnItemClickListener listener; // Listener pour gérer les clics

    /**
     * Setter pour définir la liste des catégories
     *
     * @param categories la liste des catégories
     */
    public void setCategories(@NonNull List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged(); // Notifie que les données ont changé
    }

    /**
     * Setter pour définir le listener des clics
     *
     * @param listener l'instance du listener
     */
    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate le layout pour chaque élément
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        // Vérifie que la liste n'est pas nulle et que la position est valide
        if (categories != null && position < categories.size()) {
            Category category = categories.get(position); // Récupère la catégorie à la position actuelle

            // Définit les données dans les TextViews
            holder.textViewName.setText(category.getName() != null ? category.getName() : "Nom non disponible");
            holder.textViewDescription.setText(category.getDescription() != null ? category.getDescription() : "Description non disponible");


            // Définit le clic sur l'élément
            holder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(category); // Appelle le listener avec la catégorie cliquée
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size(); // Retourne le nombre d'éléments dans la liste
    }

    /**
     * ViewHolder interne pour gérer les vues d'un élément
     */
    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewDescription;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            // Récupère les références des TextViews
            textViewName = itemView.findViewById(R.id.CategoryName);
            textViewDescription = itemView.findViewById(R.id.CategoryDescription);
        }
    }

    /**
     * Interface pour gérer les clics sur un élément
     */
    public interface OnItemClickListener {
        void onItemClick(Category category);
    }
}
