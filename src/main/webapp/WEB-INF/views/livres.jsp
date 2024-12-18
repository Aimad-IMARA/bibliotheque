<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bibliothèque - Liste des Livres</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center mb-4">Gestion des Livres</h1>
    <div class="d-flex justify-content-between align-items-center mb-4">
        <a href="${pageContext.request.contextPath}/admin/livres/addLivre" class="btn btn-success">
            <i class="bi bi-plus-circle"></i> Ajouter un Nouveau Livre
        </a>
    </div>
    <table class="table table-bordered table-hover">
        <thead class="table-dark text-center">
        <tr>
            <th>Titre</th>
            <th>Auteur</th>
            <th>Genre</th>
            <th>Disponible</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty requestScope.livres}">
                <c:forEach var="livre" items="${requestScope.livres}">
                    <tr>
                        <td>${livre.titre}</td>
                        <td>${livre.auteur}</td>
                        <td>${livre.genre}</td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${livre.disponible}">
                                    <span class="badge bg-success">Disponible</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-danger">Non Disponible</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center">
                            <a href="${pageContext.request.contextPath}/admin/livres/edit?id=${livre.id}" class="btn btn-primary btn-sm">
                                <i class="bi bi-pencil"></i> Modifier
                            </a>
                            <a href="${pageContext.request.contextPath}/admin/livres/delete?id=${livre.id}" class="btn btn-danger btn-sm">
                                <i class="bi bi-trash"></i> Supprimer
                            </a>
                            <c:if test="${livre.disponible}">
                                <a href="${pageContext.request.contextPath}/admin/livres/emprunter?id=${livre.id}" class="btn btn-warning btn-sm">
                                    <i class="bi bi-arrow-right-circle"></i> Emprunter
                                </a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="5" class="text-center text-muted">Aucun livre trouvé</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
