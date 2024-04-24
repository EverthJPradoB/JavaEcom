<%-- 
    Document   : registrarse.jsp
    Created on : 30/11/2021, 04:55:52 PM
    Author     : JEYSON
--%>

<%@page import="Clases.Usuario"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    Usuario userVerification = (Usuario) request.getSession().getAttribute("user-logged");

    if (userVerification != null) {

        request.setAttribute("user", userVerification);
    }


%>
<!DOCTYPE html>
<html>
    <%@include file="/includes/head.jsp"%>
    <title>E-Commerce</title>

</head>
<body>
    <%@include file="/includes/nav.jsp"%>

    <div class="container">


        <div class="mt-4"> 
            <div class="mb-3">

                <h2>Registrarse</h2>

            </div>

            <div>

                <form method="POST" action="registrarUsuario">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputEmail4">Nombre</label>
                            <input type="text" class="form-control" name="name" id="inputEmail4" placeholder="NOMBRE">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputPassword4">Apellidos</label>
                            <input type="text" name="apellido" class="form-control" id="inputPassword4" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputAddress">Telefono</label>
                        <input type="text" class="form-control" name="telefono" id="inputAddress" placeholder="1111-4444">
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputCity">Email</label>
                            <input type="email" class="form-control" id="" name="email" placeholder="Email">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="pass">Contraseña</label>
                            <input type="password" class="form-control" id="" name="pass" placeholder="*******">
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label" for="gridCheck">
                                Check me out
                            </label>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Sign in</button>
                </form>
            </div>

        </div>
    </div>


    <%@include file="/includes/footer.jsp"%>

</body>
</html>
