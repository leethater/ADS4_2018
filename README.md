# ADS4_2018

Projet d'analyse des données structurées en 2ème année universitaire.  
Le projet consiste à ous implémenter un interpréteur permettant de tracer automatiquement et précisément des formes géométriques simples, en lisant des instructions dans un fichier.

## Compilation et exécution
Pour tous les systèmes unix,
```Shell
jflex lexer.flex
javac *.java
java Main Tests/fichier_test
```

## Langage et grammaire

```Grammaire

nombre→ [0-9]+
hex→ [0-9A-F]
couleur→ #{hex}{hex}{hex}{hex}{hex}{hex}
opérateur→ "+" | "-" | "/" | "*"
identificateur→ [a-z][a-zA-Z_]*

programme→
          suite-instructions
instruction→
            Begin
            suite-instructions
            End
            | DrawCircle (expr, expr, expr, couleur)
            | FillCircle (expr, expr, expr, couleur)
            | DrawRect (expr, expr, expr, expr, couleur)
            | FillRect (expr, expr, expr, expr, couleur)
            | Const identificateur = expr
            | If expr Then instruction Else instruction Fi
            | Var identificateur = expr
            | While expr Do instruction
            | For (Var identificateur = expr, expr, expr) Do instruction
suite-instructions→
            instruction; suite-instructions
            | ε
expr→
            nombre
            |(expr opérateur expr)
            | identificateur

```

## Extensions ajoutées
* Constantes
Pour déclarer une constante, utilisez *Const __nom_constante__ = valeur*
* Conditionnelles
Il est possible de rajouter des conditionnelles dans votre fichier, la syntaxe : *If __expr_condition__ Then __instruction1__ Else __instruction2__ Fi*
* Variables
Vous pouvez également utiliser des variables avec *Var __nom_variable__ = __valeur__*
* Boucles While et For
Si vous désirez employer des boucles dans le code de votre fichier, vous pouvez utiliser *While __expr_condition__ Do __instructions__* pour la boucle while() et *For(__declaration__, __expr_condition__, __incrementation__) Do*
