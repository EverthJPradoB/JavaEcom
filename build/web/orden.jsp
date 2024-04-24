<%-- 
    Document   : orden.jsp
    Created on : 30/11/2021, 04:55:27 PM
    Author     : JEYSON
--%>

<%@page import="Conexion.dbConexion"%>
<%@page import="Clases.Orden"%>
<%@page import="java.util.List"%>
<%@page import="ClasesDao.OrdenDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Clases.Carrito"%>
<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    //VERIFICAR USUARIO
    Usuario userVerification = (Usuario) request.getSession().getAttribute("user-logged");
    List<Orden> ordenes = null;
    if (userVerification != null) {

        request.setAttribute("user", userVerification);

        ordenes = new OrdenDao(dbConexion.getConnection()).obtenerListaOrden(userVerification.getIdUsuario());

    } else {
        
        response.sendRedirect("login.jsp");
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


    <div style="width: 1110px;margin: 20px auto;">

        <h2 class="mb-3" style="padding-bottom: 13px">TOTAL DE ORDENES</h2>
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Fecha</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Categoria</th>
                    <th scope="col">Cantidad</th>
                    <th scope="col">Precio</th>
                    <th scope="col"></th>

                </tr>
            </thead>
            <tbody>


                <%                        for (Orden o : ordenes) {
                        if (o != null) {

                %> 
                <tr>
                    <th> <%= o.getFecha()%>  </th>
                    <th> <%= o.getNombre()%>  </th>

                    <th> <%= o.getCategoria()%>  </th>
                    <th> <%= o.getCantidad()%>  </th>
                    <th> <%= o.getPrecio()%>  </th>
                    <th> <a class="bnt btn-danger" href="eliminarOrden?id=<%=o.getIdOrden()%>" style="padding: 5px; border-radius: 5px">Borrar de Orden</a>  </th>
                </tr>
                <%                            }
                    }

                %>


            </tbody>
        </table>


    </div>






    <%@include file="/includes/footer.jsp"%>

</body>
