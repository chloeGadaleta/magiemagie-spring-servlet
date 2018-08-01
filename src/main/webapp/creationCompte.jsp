<%-- 
    Document   : creationCompte
    Created on : 13 juil. 2018, 15:28:20
    Author     : Administrateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Fugaz+One" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css"/>
    <title>Connectez-vous</title>
    </head>
    <body>
        <section class="rejoindrePartie">
            <h1 class="titleRejoindrePartie">Connectez-vous</h1>
            <form class="formRejoindrePartie" method="POST">
                <label class="lab labPseudo">pseudo</label>
                <input class="champ champCli champPseudo" type="text" name="pseudo"/>
                <label class="lab labAvatar">Choissisez votre avatar</label>
                
                <div class="blocAvatar"> 
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="radio" id="exampleRadios1" value="blueIcon" checked>
                        <label class="form-check-label" for="radio"><i class="fas fa-user blueIcon"></i></label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="radio" id="exampleRadios2" value="pinkIcon" checked>
                        <label class="form-check-label" for="radio"><i class="fas fa-user pinkIcon"></i></label>
                    </div>
                    <div class="form-check disabled">
                        <input class="form-check-input" type="radio" name="radio" id="exampleRadios3" value="greenIcon" checked>
                        <label class="form-check-label" for="radio"><i class="fas fa-user greenIcon"></i></label>
                    </div>
                    <div class="form-check disabled">
                        <input class="form-check-input" type="radio" name="radio" id="exampleRadios3" value="yellowIcon" checked>
                        <label class="form-check-label" for="radio"><i class="fas fa-user yellowIcon"></i></label>
                    </div>
                    <div class="form-check disabled">
                        <input class="form-check-input" type="radio" name="radio" id="exampleRadios3" value="purpleIcon" checked>
                        <label class="form-check-label" for="radio"><i class="fas fa-user purpleIcon"></i></label>
                    </div>
                </div>
                <input class="btnSubmit btnSubREjoindrePartie" type="submit"/>
            </form>
        </section>
    </body>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</html>
