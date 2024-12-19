<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des emprunts</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center mb-4">Liste des emprunts</h1>
    <table class="table table-bordered table-hover">
        <thead class="table-dark text-center">
        <tr>
            <th>ID</th>
            <th>Email</th>
            <th>Numéro de téléphone</th>
            <th>Date d'emprunt</th>
            <th>Date de retour</th>
            <th>Livre</th>
            <th>Statut</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emprunt" items="${requestScope.emprunts}">
            <tr>
                <td>${emprunt.id}</td>
                <td>${emprunt.email}</td>
                <td>${emprunt.numeroTel}</td>
                <td>${emprunt.dateEmprunt}</td>
                <td>${emprunt.dateRetour}</td>
                <td>${emprunt.livre.titre}</td>
                <td class="text-center">
                    <c:choose>
                        <c:when test="${emprunt.status == 'ACTIF'}">
                            <span class="badge bg-success">Actif</span>
                        </c:when>
                        <c:when test="${emprunt.status == 'RETOUR'}">
                            <span class="badge bg-secondary">Retour</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge bg-warning">Inconnu</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="text-center">
                    <c:if test="${emprunt.status == 'ACTIF'}">
                        <a href="${pageContext.request.contextPath}/admin/emprunts/retourner?id=${emprunt.id}"
                           class="btn btn-warning btn-sm">
                            <i class="bi bi-arrow-return-left"></i> Retourner
                        </a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
