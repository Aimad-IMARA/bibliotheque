<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un emprunt</title>
</head>
<body>
<h1>Ajouter un emprunt</h1>
<form method="POST" action="emprunts">
    <label for="livreId">Livre:</label>
    <select id="livreId" name="livreId" required>
        <c:forEach var="livre" items="${requestScope.livres}">
            <option value="${livre.id}">${livre.titre}</option>
        </c:forEach>
    </select><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>

    <label for="numeroTel">Numéro de téléphone:</label>
    <input type="tel" id="numeroTel" name="numeroTel" required><br><br>

    <label for="dateRetour">Date de retour:</label>
    <input type="date" id="dateRetour" name="dateRetour" required><br><br>

    <button type="submit">Ajouter</button>
</form>
</body>
</html>
