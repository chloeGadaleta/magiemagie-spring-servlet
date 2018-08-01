<%-- 
    Document   : rejoindrePartie
    Created on : 13 juil. 2018, 11:41:11
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8"/>
  <title>Rejoindre la partie</title>
  <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans" rel="stylesheet">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Fugaz+One" rel="stylesheet">
  <link rel="stylesheet" href="css/style.css"/>
  <link rel="stylesheet" href="css/rejoindre-partie-style.css"/>
</head>
<body>
    <header>
        <div>
            <i class="fas fa-user user"></i>
            <h2 class="nameUser">Chloe Gadaleta</h2>
            <a href="magie.html"><h1><i class="fas fa-magic magicL"></i> Magie Magie <i class="fas fa-magic magicR"></i></h1></a>
            <h2 class="titleListPartie">VOTRE PARTIE</h2>
        </div>
    </header>
    <div class="main">
        <a href="<c:url value='/demarrer-partie'/>?idPartie=${partie.id}"><button class="btnCreeP">Démarrer la partie <i class="far fa-arrow-alt-circle-right fleche"></i></button></a>
        <ul class="listePartie">
            <c:forEach items="${listeIdPseudo}" var="tabInfoJoueur">
                <li class="joueur joueurRejoindreP">
                    <i class="fas fa-user user ${tabInfoJoueur[2]}"></i>
                    <h3>${tabInfoJoueur[1]}</h3>
                </li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
