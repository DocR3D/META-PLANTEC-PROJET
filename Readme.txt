# Projet de Meta : Mini spec 
 Fait par Lucas Bernin, Kaivin Gidel et Maxime Yonnet 
 
 Dans ce projet se trouve le repertoire SimpleExpression qui contient : 
 - Un repertoire src contenant tout le code
 - N fichier XML avec le resultat de leur import/Export se terminant par -out.xml
 - "ProjetMetaUML.png" qui correspond au diagramme Uml du MetaModel
 - "QUELQUECHOSE.dtd" qui correspond au format de la dtd
 
 Dans ce projet nous avons réalisés : 
 - L'importation de fichiers XML selon la DTD à l'aide des librairies DOM, la classe se trouve dans src/XMLIO/XMLAnalyser.java
 - L'exportation de fichiers XML selon la DTD à l'aide des librairies DOM, la classe se trouve dans src/XMLIO/XMLSerializer.java
 - Réalisation d'un pretty printer afin d'afficher dans la console le résultat de l'importation selon l'exemple fournit dans le sujet
 - Un outil de renommage permettant de modifier le nom d'un élement. Il prend en parametre l'ancien nom et le nouveau nom
 Ces quatres parties ont été faites en utilisant un visiteur different à chacun 
 
 Le code se trouvant dans le repertoire src est séparé en trois repertoires (package) :
 - BackEnd qui contient le PrettyPrinter, l'outil de renommage et leurs classes de test
 - MetaModel qui contient l'ensemble des classes nécéssaires la création des objets
 - XMLIO qui contient l'analyser de fichier XML, le serializer et pour chacun, une classe de test
