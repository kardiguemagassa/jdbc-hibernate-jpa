<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="styles.css">
    <title>JSP - Hello World</title>
</head>
<body>
    <div class="row">
        <div class="column">
            Liste femmes:<br>
            <table>
                <c:forEach var = "joueur" items="${listeFemmes}">
                    <tr>
                        <td>${joueur.prenom}</TD><TD>${joueur.nom}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="column">
            Liste hommes:<br>
            <table>
                <c:forEach var = "joueur" items="${listeHommes}">
                    <tr>
                        <td>${joueur.prenom}</TD><TD>${joueur.nom}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>