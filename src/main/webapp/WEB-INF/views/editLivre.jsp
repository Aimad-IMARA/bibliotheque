<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier un Livre</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-header text-center">
                    <h2>Modifier le Livre : ${requestScope.livre.titre}</h2>
                </div>
                <div class="card-body">
                    <form action="editLivre" method="post">
                        <input type="hidden" name="_method" value="PUT" />
                        <input type="hidden" name="id" value="${requestScope.livre.id}">

                        <div class="mb-3">
                            <label for="titre" class="form-label">Titre</label>
                            <input type="text" value="${requestScope.livre.titre}" id="titre" name="titre" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="auteur" class="form-label">Auteur</label>
                            <input type="text" value="${requestScope.livre.auteur}" id="auteur" name="auteur" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="genre" class="form-label">Genre</label>
                            <input type="text" value="${requestScope.livre.genre}" id="genre" name="genre" class="form-control" required>
                        </div>

                        <button type="submit" class="btn btn-warning w-100">Modifier</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
