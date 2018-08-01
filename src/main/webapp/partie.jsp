<%-- 
    Document   : partie
    Created on : 13 juil. 2018, 11:44:26
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8"/>
  <title>Jeu! Magie Magie</title>
  <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans" rel="stylesheet">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Fugaz+One" rel="stylesheet">
  <link rel="stylesheet" href="css/style.css"/>
  
</head>
<body>
    <div class="mainPartie">
        <section class="sectionJoueur">
            <div class="headerPartie">
                <h1 class="titleMagiePartie blocHeader">Magie Magie</h1>
                <h3 class="blocHeader">${partie.nom}</h3>
                <div class="joueurMain blocHeader">
                    <p>Joueur qui a la main</p>
                    <p id="nameJoueur">${joueurMain.pseudo}</p>
                </div>
            </div>
            <ul>
                <c:forEach items="${listeJoueur}" var="infoJoueur">
                    <li class="joueur">
                        <i class="fas fa-user user userPartie ${infoJoueur[2]}"></i>
                        <p>${infoJoueur[1]}</p>
                        <p>${infoJoueur[3]}</p>
                    </li>
                </c:forEach>
            </ul>
        </section>
        <section class="sectionBtn">
            <div class="buttonPartie">
                <button id="btnJouer">Jouer</button>
                <div>
                    <button id="btnPasserTour">Passer tour</button>
                </div>
            </div>
        </section>
        <section class="sectionCarteIngre">
            <h2>MES CARTES</h2>
            <ul class="ulIngredients">
                <c:forEach items="${listeCarte}" var="carte">
                <c:choose>
                    <c:when test="${carte.typeIngredient == 'CHAUVE_SOURIS'}">
                        <li class="ingredients">
                            <img class="cartesIngerdients" src="img/ailes.png" alt="ailes" title="ailes chauve souris" width="349" height="267">
                            <h3>Chauve souris</h3>
                            <button id="chauveSouris" class="btnChoixIngre"><p>CHOISIR</p></button>
                        </li>
                    </c:when>
                    <c:when test="${carte.typeIngredient == 'LICORNE'}">
                        <li class="ingredients">
                            <img class="cartesIngerdients" src="img/licorne.png" alt="Licorne" title="Licorne" width="349" height="267">
                            <h3>Licorne</h3>
                            <button id="licorne" class="btnChoixIngre"><p>CHOISIR</p></button>
                        </li>
                    </c:when>
                    <c:when test="${carte.typeIngredient == 'LAPIS_LAZULI'}">
                        <li class="ingredients">
                            <img class="cartesIngerdients" src="img/lapi-lazuli.png" alt="Lapi-Lazuli" title="Lapi-Lazuli" width="349" height="267">
                            <h3>Lapi-Lazuli</h3>
                            <button id="lapiLazuli" class="btnChoixIngre"><p>CHOISIR</p></button>
                        </li>
                    </c:when>
                    <c:when test="${carte.typeIngredient == 'CRAPAUD'}">
                        <li class="ingredients">
                            <img class="cartesIngerdients" src="img/frog.png" alt="Bave-crapaud" title="Bave crapaud" width="349" height="267">
                            <h3>Bave crapaud</h3>
                            <button id="crapaud" class="btnChoixIngre"><p>CHOISIR</p></button>
                        </li>
                    </c:when>
                    <c:when test="${carte.typeIngredient == 'MANDRAGORE'}">
                    <li class="ingredients">
                        <img class="cartesIngerdients" src="img/madragore.png" alt="Mandragore" title="Mandragore" width="349" height="267">
                        <h3>Mandragore</h3>
                        <button id="mandragore" class="btnChoixIngre"><p>CHOISIR</p></button>
                    </li>
                    </c:when>
                </c:choose>
                </c:forEach>
            </ul> 
        </section>          
    </div>
    <script src="js/jquery-3.3.1.js" type="text/javascript"></script>
</body>
</html>
