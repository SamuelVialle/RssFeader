# RSS READER INTO RECYCLERVIEW

Cette exemple provient du site d'[Aysel Aydin](https://ayselaydin.medium.com/android-rss-reader-kullanımı-4aa567f75cd4)
Vous pouvez vous inspirer des différents code qu'elle laisse à disposition sur son [Github](https://github.com/AyselAydin?tab=repositories)

## Fonctionnement des RSS


## Création de l'app
### Ajout des permissions dans le manifest
Accès à internet
`<uses-permission android:name="android.permission.INTERNET"/>`

### Ajout des dépendances dans le fichier gradle.module
 * Glide pour la gestion des images depuis des URI
 * RecyclerView
 * Cardview 

### Création du Model pour les getters et les setters
Dans lequel nous allons faire référence à 3 objets :
 * title
 * link
 * imageUrl
 
### Ajout du RecyclerView à activity_main.xml
 Penser à ajouter un padding dans le constraint layout pour laisser un peu d'espace entre le bord de l'écran et le Recycler
 
### Ajout du custom layout pour une ligne du Recycler : item_news_layout.xml
 Le conteneur principal est un cardview, cela permet entre autre d'ajouter une élévation, création de l'ombre, et un bord arrondi.
 Son design est assez simple : une image de fond et le titre
 
### Ajout de la classe adapter : newsAdapter 
 
### Ajout la classe pour parser les fichiers xml des RSS
 Ce fichier va parser le fichier xml des RSS et en extraire les informations dont nous avons besoin à savoir le titre, l'URL de l'image et le lien vers l'article
 
### Intégration des différents articles dans MainActivity.java
 * Gestion de l'affichage des articles dans le RecyclerView
 * Initialisation de l'adapter à la liste des articles
 * Gestion du clic sur un des items du Recycler 

### Ajout de la seconde activité
Cette activité va gérer l'affichage de l'article sélectionné dans le RecyclerView

 
 

