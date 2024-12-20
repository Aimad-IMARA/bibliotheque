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
            <i class="bi bi-plus-circle"></i>
        </a>
        <form method="get" action="${pageContext.request.contextPath}/admin/livres" class="w-75">
            <div class="input-group">
                <input type="text" class="form-control" name="search" placeholder="Rechercher un livre par nom" value="${param.search}">
                <button class="btn btn-light border" type="submit">
                    <i class="bi bi-search"></i>
                </button>
            </div>
        </form>
        <a href="${pageContext.request.contextPath}/admin/emprunts" class="btn btn-dark">
            <i class="bi bi-journal-arrow-up"></i> <span>Les emprunts</span>
        </a>
    </div>
    <table class="table table-bordered table-hover">
        <thead class="table-dark text-center">
        <tr>
            <th>Titre</th>
            <th>Auteur</th>
            <th>Genre</th>
            <th>Statut</th>
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
                                <i class="bi bi-pencil"></i>
                            </a>
                            <a href="${pageContext.request.contextPath}/admin/livres/delete?id=${livre.id}" class="btn btn-danger btn-sm">
                                <i class="bi bi-trash"></i>
                            </a>
                            <c:if test="${livre.disponible}">
                                <!-- Button to Open the Modal -->
                                <button
                                        class="btn btn-warning btn-sm"
                                        data-bs-toggle="modal"
                                        data-bs-target="#empruntModal"
                                        data-livre-id="${livre.id}"
                                        data-livre-titre="${livre.titre}">
                                    <i class="bi bi bi-journal-arrow-up"></i>
                                </button>
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

<div class="modal fade" id="empruntModal" tabindex="-1" aria-labelledby="empruntModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/admin/emprunts/addEmprunt" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="empruntModalLabel">Ajouter un Emprunt</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="livreId" id="livreId">
                    <div class="mb-3">
                        <label class="form-label">Livre</label>
                        <label class="form-control" id="livre-name"></label>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="mb-3">
                        <label for="numeroTel" class="form-label">Numéro de Téléphone</label>
                        <input type="text" class="form-control" id="numeroTel" name="numeroTel" required>
                    </div>
                    <div class="mb-3">
                        <label for="dateRetour" class="form-label">Date de Retour</label>
                        <input type="date" class="form-control" id="dateRetour" name="dateRetour" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const empruntModal = document.getElementById('empruntModal');
    empruntModal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        const livreId = button.getAttribute('data-livre-id');
        document.getElementById('livre-name').textContent = button.getAttribute('data-livre-titre')
        const livreIdInput = empruntModal.querySelector('#livreId');

        livreIdInput.value = livreId;
    });
</script>
</body>
</html>
