package ma.ensa.mecro__learning.network;
import okhttp3.OkHttpClient;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            // Construire le client OkHttp
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            // Ajouter un HttpLoggingInterceptor pour les logs réseau (utile en mode debug)
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // Niveau de log : BODY, HEADERS, BASIC
            httpClient.addInterceptor(loggingInterceptor);

            // Ajouter un Interceptor pour gérer les erreurs
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());

                    // Vérifier si la réponse HTTP est un succès
                    if (!response.isSuccessful()) {
                        String errorMessage = "Erreur HTTP : " + response.code();

                        // Personnaliser les messages d'erreur en fonction du code
                        switch (response.code()) {
                            case 400:
                                errorMessage = "Requête incorrecte (400)";
                                break;
                            case 401:
                                errorMessage = "Non autorisé (401)";
                                break;
                            case 403:
                                errorMessage = "Accès refusé (403)";
                                break;
                            case 404:
                                errorMessage = "Ressource non trouvée (404)";
                                break;
                            case 500:
                                errorMessage = "Erreur interne du serveur (500)";
                                break;
                            default:
                                errorMessage = "Erreur inattendue : " + response.code();
                        }

                        // Lever une exception avec le message d'erreur
                        throw new IOException(errorMessage);
                    }

                    return response;
                }
            });

            // Construire l'objet Retrofit avec le client OkHttp
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.4.5:9010/") // Remplacez par l'URL de base de votre API
                    .client(httpClient.build()) // Ajouter le client OkHttp personnalisé
                    .addConverterFactory(GsonConverterFactory.create()) // Convertisseur JSON
                    .build();
        }
        return retrofit;
    }
}

