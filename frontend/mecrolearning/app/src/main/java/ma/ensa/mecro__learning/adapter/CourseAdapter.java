package ma.ensa.mecro__learning.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ma.ensa.mecro__learning.R;
import ma.ensa.mecro__learning.model.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> courses;
    private OnItemClickListener listener;

    public void setCourses(List<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        CourseViewHolder viewHolder = new CourseViewHolder(view);

        // Gestion du clic sur l'élément
        view.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            if (listener != null && position != RecyclerView.NO_POSITION) {
                listener.onItemClick(courses.get(position));
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.textViewName.setText(course.getName());
        holder.textViewDescription.setText(course.getDescription());
    }

    @Override
    public int getItemCount() {
        return courses == null ? 0 : courses.size();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewDescription;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.courseName);
            textViewDescription = itemView.findViewById(R.id.courseDescription);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Course course);
    }
}
