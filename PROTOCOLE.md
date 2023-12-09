# PREMIÈRE PARTIE : Les requêtes Engine -> NetworkPlayer

Les requêtes de l’engine suivent toujours la même forme : 

## Dans le name : Le nom de la requête, càd la raison pour laquelle le joueur est contacté : 
Les 6 requêtes possibles sont : 
“askGetOutOfJail” (Veux tu payer 50 pour sortir de prison)
“askRemoveInstantlySquat” (Veux tu enlever les squatteurs qui viennent d’apparaître sur la case [VOIR BODY] )
“askBuyProperty” (Veux tu acheter la propriété [VOIR BODY]
“thinkAndAnswer” (Réfléchis et renvoie moi ce que tu veux faire) 
"youLost" (Attention, pas de body ni de params dans ce cas)
"youWin" (Attention, pas de body ni de params dans ce cas)


## Dans le body : Les informations spécifiques vraies pour un instant T de la partie de jeu: 
Ils sont sous la forme suivante : 
“nom de la case en question (optionnel);Le compte en banque du joueur à l’instant T;Position du joueur à l’instant T (int entre 0 et 39)” 

### Exemples : 
b = “Boulevard de Belleville;1955;24” 
“;1200;10”
“Avenue Mozart;221;0”


## Dans les params : La map dans son intégralité càd toutes les cases achetables (couleurs gares et compagnies), et leur situation (nombre de maisons / est hypothéquée / est une prison)
La map est donc de la forme : 


### CLÉS ; VALEURS
{ “indice de la case achetable (de 0 à 28 inclus)” ; “Infos sur l’owner en int (0=noOwner,1=YouAreTheOwner, 2=OwnedBySomeoneElse);InfosSpécifiques en int (0,1,2,3,4,5 = nombre de maisons, 6=CaseHypothéquée, 7=CaseEstUnePrison)”

“indice de la case achetable (de 0 à 28 inclus)” ; “Infos sur l’owner en int (0=noOwner,1=YouAreTheOwner, 2=OwnedBySomeoneElse);InfosSpécifiques en int (0,1,2,3,4,5 = nombre de maisons, 6=CaseHypothéquée, 7=CaseEstUnePrison)”

“indice de la case achetable (de 0 à 28 inclus)” ; “Infos sur l’owner en int (0=noOwner,1=YouAreTheOwner, 2=OwnedBySomeoneElse);InfosSpécifiques en int (0,1,2,3,4,5 = nombre de maisons, 6=CaseHypothéquée, 7=CaseEstUnePrison)”

“indice de la case achetable (de 0 à 28 inclus)” ; “Infos sur l’owner en int (0=noOwner,1=YouAreTheOwner, 2=OwnedBySomeoneElse);InfosSpécifiques en int (0,1,2,3,4,5 = nombre de maisons, 6=CaseHypothéquée, 7=CaseEstUnePrison)”

etc fois 28
}


### Différentes valeurs pour les Infos sur l’owner en int : 
0 => N’appartient à personne
1 => T’appartient
2 => Appartient à quelqu’un d’autre

### Différentes valeurs pour les Infos Spécifiques en int : 
0 => 0 maisons dessus
1 => 1 maison dessus
.
.
.
5 => 5 maisons dessus = 1 hotel
6 => La case est hypothéquée 
7 => La case est une prison 

### Exemple clé ; valeur : 

Clé = “4” ; 
Valeur = 
“1;3”

Clé = “0”;
Valeur = 
“2;6”


# DEUXIÈME PARTIE : Les réponses NetworkPlayer -> Engine

Le format de réponse à l’Engine est le suivant : 

playerFacade.sendGameCommandToPlayer(monopoly, "Host", new GameCommand(String Réponse));

Pour chaque command.name(), l’Host attend une réponse différente ;

## “askGetOutOfJail” : 

Si le joueur accepte de payer 50 pour sortir de prison, il doit renvoyer au host la GameCommand : “YesOut”. sinon il renvoie n’importe quoi d’autre on comprendra qu’il veut pas 


## “askRemoveInstantlySquat” : 

Si le joueur accepte de payer 200 pour retirer le squatteur, il doit renvoyer au host la GameCommand : “YesGetRid”. sinon il renvoie n’importe quoi d’autre on comprendra qu’il veut pas 

## “askBuyProperty” : 

Si le joueur accepte d’acheter la propriété, il doit renvoyer au host la GameCommand : “YesBuy”. sinon il renvoie n’importe quoi d’autre on comprendra qu’il veut pas 


## "thinkAndAnswer” : 

### LES CROCHETS NE SONT PAS DANS LA SYNTAXE C’EST JUSTE POUR SÉPARER LES ÉLÉMENTS

Quoi qu’il arrive le joueur renvoie une réponse toujours constituée de la même manière : 

1)Liste des maisons à placer
2)Liste des maisons à vendre
3)Liste des propriétés à hypothéquer
4)Liste des propriétés à transformer en prison 


Tout ca doit être contenu dans une unique String selon le format suivant : 

### Liste des maisons à placer : 

[Nom de la couleur],[Nombre de maisons à placer dessus];[Nom de la couleur],[Nombre de maisons à placer dessus]; AUTANT DE COULEURS QUE LE JOUEUR VEUT.

#### Exemple : 

“MARRON,2;CYAN,1” ⇔ Met deux maisons sur les marrons et 1 sur les cyans




### Liste des maisons à vendre : 

[Nom de la couleur],[Nombre de maisons à vendre dessus];[Nom de la couleur],[Nombre de maisons à vendre dessus]; AUTANT DE COULEURS QUE LE JOUEUR VEUT.
 
#### Exemple : 

“MARRON,2;CYAN,1” ⇔ Vend deux des maisons qui sont sur les marrons et 1 qui est sur les cyans.



### Liste des propriétés à hypothéquer : 

[Nom de case];[Nom de case];... AUTANT DE CASES QUE LE JOUEUR VEUT HYPOTHÉQUER.

#### Exemple : 

“Avenue Mozart;Boulevard Saint-Michel” ⇔ Hypothèque l’avenue Mozart et le Boulevard Saint Michel


### Liste des propriétés à transformer en prison :

[Nom de case];[Nom de case];... AUTANT DE CASES QUE LE JOUEUR VEUT TRANSFORMER EN PRISON.

#### Exemple : 

“Avenue Mozart;Boulevard Saint-Michel” ⇔ Transforme en prison l’avenue Mozart et le Boulevard Saint Michel


UNE FOIS QU’ON A LES 4 STRING : 

On les colle avec des “_” (TIRETS DU 8 ; UNDERSCORE)

### Exemple Complet : 

“MARRON,2;CYAN,1_VIOLET,2;BLEU,1_Avenue Mozart;Boulevard Saint-Michel_Place de la Bourse;Rue la Fayette”

### ATTENTION : 

Si le joueur décide par exemple de ne pas créer de maisons ce tour-ci, au lieu de [Nom de la couleur],[Nombre de maisons à placer dessus], il faut le remplacer par un simple “N” 
Tout renvoi de collection vide doit être remplacé par un "N"

### Exemple Complet : 

“N_VIOLET,2;BLEU,1_N_N”
