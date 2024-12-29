package ma.ensa.mecro__learning.ui;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;

import ma.ensa.mecro__learning.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView logoImage;
    private TextView appName, welcomeMessage;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialiser les vues
        logoImage = findViewById(R.id.appLogo);
        appName = findViewById(R.id.appName);
        welcomeMessage = findViewById(R.id.welcomeMessage);
        startButton = findViewById(R.id.startButton);

        // Animation du logo (par exemple, zoom avant)
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(logoImage, "scaleX", 0.0f, 1.5f);
        scaleX.setDuration(1000); // Durée de l'animation
        scaleX.start();

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(logoImage, "scaleY", 0.0f, 1.5f);
        scaleY.setDuration(1000);
        scaleY.start();

        // Bouton "Start" pour naviguer vers la page de connexion
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer l'activité de connexion
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Fermer l'écran de démarrage
            }
        });
    }
}
