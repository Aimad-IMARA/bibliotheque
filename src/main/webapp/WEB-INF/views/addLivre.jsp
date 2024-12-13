<%--
  Created by IntelliJ IDEA.
  User: Aimad
  Date: 13/12/2024
  Time: 5:32 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un livre</title>
</head>
<body>
    <h1>Ajouter un livre</h1>
    <form action="addLivre" method="post">
        <label for="titre">Titre :</label>
        <input type="text" id="titre" name="title" required><br><br>

        <label for="auteur">Auteur :</label>
        <input type="text" id="auteur" name="author" required><br><br>

        <label for="genre">Genre :</label>
        <input type="text" id="genre" name="isbn" required><br><br>

        <button type="submit">Ajouter</button>
    </form>
</body>
</html>
