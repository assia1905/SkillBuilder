package ma.ensa.mecro__learning.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ma.ensa.mecro__learning.R;
import ma.ensa.mecro__learning.model.Lesson;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private List<Lesson> lessons;
    private OnItemClickListener listener;

    // Setter pour définir la liste des leçons
    public void setLessonList(List<Lesson> lessons) {
        this.lessons = lessons;
        notifyDataSetChanged();
    }

    // Setter pour définir le listener des clics
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson, parent, false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        holder.textViewTitle.setText(lesson.getTitle());
        holder.textViewContent.setText(lesson.getContent());  // Affichage du contenu

        // Gestion du clic sur l'élément
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(lesson);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessons == null ? 0 : lessons.size();
    }

    // ViewHolder pour afficher chaque élément
    static class LessonViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewContent;  // Ajout d'un champ pour le contenu

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.lessonTitle);
            textViewContent = itemView.findViewById(R.id.lessonContent);  // Initialisation du champ contenu
        }
    }

    // Interface pour gérer les clics sur les leçons
    public interface OnItemClickListener {
        void onItemClick(Lesson lesson);
    }
}
