<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Titre</th>
        <th>Auteur</th>
        <th>Genre</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${not empty requestScope.livres}">
            <c:forEach var="livre" items="${requestScope.livres}">
                <tr>
                    <td>${livre.id}</td>
                    <td>${livre.titre}</td>
                    <td>${livre.auteur}</td>
                    <td>${livre.genre}</td>
                    <td>
                        <a href="livres/edit?id=${livre.id}">Modifier</a>
                        <a href="livres/delete?id=${livre.id}">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="6">Aucun livre trouvé</td>
            </tr>
        </c:otherwise>
    </c:choose>
    </tbody>

</table>
${requestScope.livres[0].titre}

</body>
</html>
