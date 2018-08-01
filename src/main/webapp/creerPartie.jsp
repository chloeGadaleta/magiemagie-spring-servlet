<%-- 
    Document   : creerPartie
    Created on : 13 juil. 2018, 11:39:15
    Author     : Administrateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8"/>
  <title>Magie</title>
  <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans" rel="stylesheet">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Fugaz+One" rel="stylesheet">
  <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
    <header>
        <div>
            <i class="fas fa-user user"></i>
            <h2 class="nameUser">Chloe Gadaleta</h2>
            <a href="magie.html"><h1><i class="fas fa-magic magicL"></i>Magie Magie<i class="fas fa-magic magicR"></i></h1></a>
            <h2 class="titleListPartie">CREER UNE PARTIE</h2>
        </div>
    </header>
    <div class="main mainForm">
        <form method="POST" class="titreCreerpartie">
            <label for="name" class="labelForm">Nommez votre partie</label>
            <input type="text" id="name" name="nom" class="caseForm">
            <input type="submit" value="Créer" class="btnCreerForm">
        </form>
    </div>
</body>
</html>
