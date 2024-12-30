# SkillBuilder Project

Le projet **SkillBuilder** est une application éducative composée de deux parties :
- **Backend** : une API RESTful construite avec **Spring Boot**.
- **Frontend** : une application mobile développée avec **Android Studio**.

## Architecture du projet

Ce projet est divisé en deux principales sections :
1. **Backend** : Le backend fournit une API RESTful qui sert de couche de communication entre l'application mobile et la base de données. Il est construit avec **Spring Boot**, un framework Java populaire pour la création d'applications web et d'APIs.
2. **Frontend** : L'application mobile, développée avec **Android Studio**, interagit avec l'API backend pour récupérer, afficher et gérer des données liées aux utilisateurs, catégories, cours et leçons.

### Structure du projet
 - SkillBuilder /backend # Contient le code du backend (Spring Boot)
 - frontend # Contient le code du frontend (Android Studio) 
 - README.md # Ce fichier

## Fonctionnalités principales

### Backend (Spring Boot)

Le backend de l'application est construit avec **Spring Boot** et expose une série de **points de terminaison API REST** pour gérer les utilisateurs, les catégories, les cours et les leçons.

#### Fonctionnalités backend :
- **Login** : Authentification des utilisateurs via JWT.
- **Catégories** : Gestion des catégories disponibles pour les cours.
- **Cours** : Gestion des cours dans chaque catégorie.
- **Leçons** : Gestion des leçons associées à chaque cours.

### Prérequis pour le backend

- JDK 11 ou version supérieure
- Maven pour la gestion des dépendances
- Une base de données MySQL pour le développement

### Pour exécuter le backend

1. Clonez le repository ou naviguez dans le dossier `backend`.
2. Exécutez la commande suivante pour démarrer l'application :
   ```bash
   ./mvnw spring-boot:run
3. L'application démarrera sur http://localhost:8080. Vous pouvez accéder aux API REST via cette URL.
## Endpoints API
 - POST /api/login : Authentification des utilisateurs avec un email et mot de passe.
 - GET /api/categories : Récupérer la liste des catégories disponibles.
 - GET /api/courses : Récupérer la liste des cours.
 - GET /api/courses/{id}/lessons : Récupérer les leçons d'un cours donné.
 - POST /api/courses : Ajouter un nouveau cours.
 - POST /api/lessons : Ajouter une nouvelle leçon.
### Frontend (Android Studio)
 Le frontend est une application mobile développée avec Android Studio. Cette application permet aux utilisateurs de se connecter, de parcourir les catégories, les cours et les leçons. L'application interagit avec l'API backend pour récupérer et afficher ces données.

## Fonctionnalités frontend :
 - Login : Authentification des utilisateurs avec un formulaire de login (email et mot de passe).
 - Catégories : Affichage de toutes les catégories disponibles.
 - Cours : Affichage des cours d'une catégorie sélectionnée.
 - Leçons : Accès aux leçons associées à chaque cours.
 - Affichage des données : Liste des catégories, des cours et des leçons.
## Prérequis pour le frontend
 - Android Studio : L'IDE pour le développement Android.
 - SDK Android : Assurez-vous d'avoir la dernière version du SDK Android installée.
 - Une connexion Internet pour accéder à l'API backend.
## Pour exécuter l'application Android
 - Ouvrez le projet dans Android Studio.
 - Assurez-vous que le backend est en cours d'exécution.
 - Sélectionnez un émulateur ou un appareil Android connecté.
 - Cliquez sur "Run" dans Android Studio pour démarrer l'application mobile.
## Configuration des URLs API
 Dans l'application Android, assurez-vous que les URLs de l'API sont correctement configurées pour pointer vers votre serveur backend (http://localhost:8080 ou l'URL de votre serveur de production).

## Exemple de flux utilisateur :
 - L'utilisateur se connecte avec son email et son mot de passe.
 - L'utilisateur sélectionne une catégorie de cours.
 - Après avoir choisi une catégorie, l'utilisateur peut voir la liste des cours associés.
 - En sélectionnant un cours, l'utilisateur accède à la liste des leçons.
## Configuration et Déploiement
### Backend
 - Clonez le projet et naviguez dans le dossier backend.
 - Configurez les paramètres de connexion à la base de données dans application.properties pour MySQL.
 - Lancez l'application avec Maven :
    - ./mvnw spring-boot:run
### Frontend
 - Ouvrez le projet dans Android Studio.
 - Assurez-vous que le backend fonctionne et que l'URL de l'API est correcte.
 - Déployez l'application sur un appareil ou un émulateur Android.
## Contribuer
 Si vous souhaitez contribuer à ce projet, voici quelques étapes pour commencer :
 1. Fork ce repository.
 2. Clonez votre fork localement.
 3. Créez une branche pour votre fonctionnalité ou correction de bug.
 4. Apportez vos modifications et soumettez un pull request.
## Video Demonstration
 - https://github.com/user-attachments/assets/8c93e125-b839-406d-8c54-7472778e6749

## License
Ce projet est sous la licence MIT. Pour plus de détails, veuillez consulter le fichier LICENSE.

### Explication des ajouts :
1. **Login** : Ajout de la fonctionnalité de login pour les utilisateurs, avec un endpoint API pour l'authentification.
2. **Catégories, Cours, Leçons** : Ajout des sections pour gérer et afficher les catégories, cours et leçons. Chaque entité est associée à des endpoints API pour interagir avec la base de données.
3. **Frontend** : Le frontend mobile permet aux utilisateurs de s'authentifier, de consulter les catégories, cours et leçons, et d'interagir avec l'API.
## Auteurs
 - BOUJNAH Assia
 - KHALIL Fatima




