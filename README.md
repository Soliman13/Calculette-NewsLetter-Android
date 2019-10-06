# Calculette-NewsLetter-Android

Ce projet a été développé par **El Mehdi AAYADI**, **Soliman ACHAHBAR** et **Mamadou Saliou BAH**.

Il est constitué de 3 modules : **calculatrice**, **gestionarticlenewsapi** et **gestionarticles**.

## calculatrice

Nous avons développé une calculatrice qui posséde les mêmes fonctionnalités que la calculatrice présente sur les terminaux Apple, à savoir les opérations de base ainsi que le pourcentage sur la saisie, la valeur absolue ou négative, ainsi que le clear. 

http://thesweetsetup.com/wp-content/uploads/2015/12/apple-calculator-portrait.jpg

Ajoutés à cela quelques drawables histoire d'apporter un petit design particulier à notre app !

## gestionarticlenewsapi

Le développement de ce module permet de recupérer les articles de "newsapi".

Le module est composé de 3 fragments accueil, article et le dernier fragment est utilisé en cas de non connexion internet. En mode déconnecté, l'application ne vas pas crasher mais affichera un message a l'utilisateur précisant qu'un problème de connexion est survenu.

Depuis la liste des articles on peut acceder à plus d'info avec un lien du site origine. Dans le cas où l'article ne contient pas d'image, il y aura une image par défaut de mbds.

## gestionarticles

Le développement de ce module se doit d'insérer et de récupérer les articles dans une base de données. 

Nous avons utilisé le plugin RoomDatabase d'Android pour mettre en place les entités et les couches DAO pour manipuler la BDD.

**Ce module n'est pas finalisé et présente un bug au niveau du multithreading qui n'est à l'heure actuelle pas fixé.**
