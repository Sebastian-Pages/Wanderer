<div style="text-align: justify">

# <center>Projet d'Architecture Logiciel : Wanderer</center>

<br/>
par Antoine LE FLOHIC et Sebastian PAGES
<br/>

#  Sommaire

### [1. Introduction](#-1---Introduction)

### [2. Architecture du projet](#-2---Architecture-du-projet)

### [3 - Guide d'Utilisation](#-3---Guide-Utilisation)


# 1 - Introduction

Ce jeu appelé Wanderer est un mini jeu où le but est de survivre dans une arène avec son armée dans le but d'y battre le boss de fin. Pour cela il est possible de recruter jusqu'à 9 soldats dans notre armée et de les équiper d'armes et de boucliers que l'on trouve par terre. 
Notre armée possède donc un total de points de vie ( visible pour le joueur ), une attaque et une défence ( invisible pour le joueur ). 
Lors d'un recrutement de nouvelles unitées ou de l'ajout d'équipements il faut avoir un score assez élevé pour pouvoir le dépenser ( le prix des nouveaux soldats est de 1 soldat = 1 de score).
Pour gagner en score il faut donc battre des armées enemies dans le but de dépenser ce score ou d'Invoquer le boss final lorsque notre score est supérieur à 10.


# 2 - Architecture du projet

Voici la structure du projet. 
```
src/
│
├── soldier/              # Racine du simulateur
│   ├── dominion/           # Implémentation du jeu Dominion
│   ├── instruction/        # Bibliothèque d'instructions
│   ├── advice/             # Bibliothèque des conseils
│   ├── player/             # Implémentation des joueurs
│   ├── log/                # Implémentation des logs
│   └── enum/
│
├── test/                 # Racine du parseur
│
├── data/                   # Contient des copies des fichiers .json venant du parser
│
├── presets/                # Contient les presets 
│
├── tests/                  # Tests unitaires
│
└── simulatorConfig.json
```

Les desciptions qui suivent ne sont pas exhaustives (i.e nous ne parlons pas de toutes les classes, ni de tous les fichiers). Elles ont pour but de présenter les quelques points importants du projet (donnant par la même occasion une justification à leur existance).

### **src :**

- Contient les deux fonctions main à exécuter, le simulateur minimal, ainsi que tous les autres modules.<br/>

- Le fichier [main-parser.py](src/main-parser.py) permet la gestion des arguments du parseur et son lancement pour parser les pages web du wiki.<br/>

- Le fichier [main-simulator.py](src/main-simulator.py) permet la gestion des arguments du simulateur et sa construction. <br/>

- Le fonctionnement des deux programmes à exécuter est détaillé dans le [chapitre 5](##-5---Exécuter-les-programmes) de ce document, avec des exemples de commandes à effectuer dans le terminal.

- Le fichier [dominion-transmis.py](src/dominion-transmis.py) est le simulateur minimal tel que donné au début du projet. Il est entièrement fonctionnel et exécutable.

- Le fichier [simulatorConfig.json](src/simulatorConfig.json) permet de donner au simulateur les différents fichiers/dossiers utiles à son bon fonctionnement. Il peut être modifié à votre guise.


# 3 - Guide Utilisation

Pour jouer à ce jeu :

- Run le Main

ou

- Lancer l'executable

On peut ajuster les Parametres d'une partie dans le fichier [Settings](src/soldier/gameManagement/Settings.java)

</div>
