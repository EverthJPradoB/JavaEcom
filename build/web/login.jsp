<%-- 
    Document   : login.jsp
    Created on : 30/11/2021, 04:55:01 PM
    Author     : JEYSON
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Clases.Carrito"%>
<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    //VERIFICAR USUARIO
    Usuario userVerification = (Usuario) request.getSession().getAttribute("user-logged");

    if (userVerification != null) {
        response.sendRedirect("index.jsp");

    }

    //LISTAR CARRITO
    ArrayList<Carrito> carrito_lista = (ArrayList<Carrito>) session.getAttribute("carrito-lista");

    if (carrito_lista != null) {

        request.setAttribute("carrito_lista", carrito_lista);
    }

%>



<!DOCTYPE html>
<html>
    <%@include file="/includes/head.jsp"%>
    <title>E-Commerce</title>

</head>
<body>
    <%@include file="/includes/nav.jsp"%>


    <div style="width: 500px;height: 200px; margin: 0 auto;margin-top: 100px" >

        <form  action="LoginServlet" method="POST" class="form-control" style="display: flex;justify-content: center;align-items: center;padding-top: 30px">
            <input type="hidden" value="${Errores}" class="errores" > 
            <div style="width: 100%;height: 100%;">
                <div class="form-group row">
                    <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-10">
                        <input type="text" name="txtUser"  class="form-control" id="inputPassword" placeholder="Ingrese su Correo">                </div>
                </div>
                <div class="form-group row">
                    <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password"  name="txtPass" class="form-control" id="inputPassword" placeholder=".....">
                    </div>
                </div>
                <di>
                    <button type="submit" class="btn btn-primary">Sign in</button>
                </di>
            </div>
        </form>
        <a href="registrarse.jsp">Crear una cuenta</a>


    </div>




    <%@include file="/includes/footer.jsp"%>

    <script>
        let error = document.querySelector(".errores").value
        window.addEventListener('DOMContentLoaded', (event) => {
            if (error != '') {
                alert(error);

            }
        });
        ;



    </script>

</body>
</html>
