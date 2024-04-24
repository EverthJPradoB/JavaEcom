<%@page import="Clases.Usuario"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">E-Commerce</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">

                ${ (user.getTipo()  == 1)?  '<li class="nav-item"><a class="nav-link" href="admin/index.jsp">Admin</a></li>' : '' }

               

                <li class="nav-item"><a class="nav-link" href="index.jsp">Inicio</a></li> 
                <li class="nav-item"><a class="nav-link" href="carrito.jsp">Carrito <span class="badge badge-danger">${carrito_lista.size()}</span> </a></li>
                    <%
                        if (userVerification != null) {
                    %>
                <li class="nav-item"><a class="nav-link" href="orden.jsp">Ordenes</a></li>
                <li class="nav-item"><a class="nav-link" href="cerrar-session">Cerrar Session</a></li>

                <%
                } else {
                %>
                <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                    <%
                        }
                    %>
            </ul>
        </div>
    </div>
</nav>