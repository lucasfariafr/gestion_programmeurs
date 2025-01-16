
# Gestion des Programmeurs

Ce projet est une application de gestion des programmeurs avec une interface Spring Boot, intégrant une base de données MySQL. Il permet de gérer et consulter des informations sur les programmeurs via une interface web. L'application utilise Docker pour faciliter le déploiement des services nécessaires. 

À noter : Vous pouvez également lancer l'application sans interface graphique en console avec l'intégralité des fonctionnalités ne figurant pas avec l'interface Spring Boot.

## Prérequis

Avant de commencer, assurez-vous d'avoir installé :

- **Docker** : https://www.docker.com/products/docker-desktop/
- **Java 21 SDK** : https://www.oracle.com/fr/java/technologies/downloads/#java21

## Démarrage rapide 

1.  **Construisez et lancez les services Docker** :
    
Exécutez le script `start.bat` pour démarrer l'application et ses services Docker :

1.  Double-cliquez sur le fichier `start.bat` pour démarrer le déploiement automatique des services.
2.  Le script va automatiquement :
    -   Vérifier que Docker et Java sont installés et en cours d'exécution.
    -   Construire l'image Docker pour l'application Spring Boot.
    -   Démarrer les services Docker nécessaires via `docker-compose`.
    -   Afficher les URLs où accéder à phpMyAdmin et à l'application Spring Boot.

2.  **Une fois les services démarrés, vous pouvez accéder à** :
    
    -   **phpMyAdmin** : [http://localhost:8081](http://localhost:8081) (pour gérer la base de données)
    -   **Application Spring Boot** : [http://localhost:8080](http://localhost:8080) (pour accéder à l'interface web)
3.  **Pour arrêter les services**, appuyez sur **Entrée** dans le terminal où le script est en cours d'exécution.
4. **Pour lancer l'application avec la console**:
Exécuter la classe Start dans le package `app.console`

## Technologies utilisées

-   **Spring Boot** : pour créer l'application web.
-   **Docker** : pour la conteneurisation des services (base de données MySQL, phpMyAdmin et l'application Spring Boot).
-   **MySQL** : pour la gestion des données.
-   **phpMyAdmin** : pour interagir avec la base de données via une interface web

## Choix de SGBD

Nous avons décidé d'utiliser MySQL car il permet une intégration facile avec Spring Boot via la bibliothèque Spring Data JPA. Cela permet d'utiliser également phpMyAdmin pour gérer la base de données via une interface graphique. 

## Fonctionnalités supplémentaires rajoutées au cahier des charges
- Interface graphique avec Spring Boot
- Une page statistique qui présenter deux KPI : le nombre de programmeurs et un graphique du niveau des salaires 

## Améliorations possibles (Pas eu le temps)
- Ajout d'une entité Administrateur dans la base de données
- Ajout d'un responsable qui peut gérer sa propre équipe
- Filtre pour la page répertoire permettant d'implémenter la fonctionnalité de recherche par ID ou autre
