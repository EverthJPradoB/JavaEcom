<%-- 
    Document   : carrito.jsp
    Created on : 30/11/2021, 10:07:39 PM
    Author     : JEYSON
--%>

<%@page import="Conexion.dbConexion"%>
<%@page import="ClasesDao.ProductoDao"%>
<%@page import="java.util.List"%>
<%@page import="Clases.Carrito"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //USUARIO
    Usuario userVerification = (Usuario) request.getSession().getAttribute("user-logged");

    if (userVerification != null) {

        request.setAttribute("user", userVerification);
    }

    /*CARRITO*/
    ArrayList<Carrito> carrito_lista = (ArrayList<Carrito>) session.getAttribute("carrito-lista");

    List<Carrito> carritoPro = null;
    double total = 0;

    if (carrito_lista != null) {
        //Conexion
        ProductoDao pd = new ProductoDao(dbConexion.getConnection());

        //carrito
        carritoPro = pd.obtenerCarritoProductos(carrito_lista);

        request.setAttribute("carrito_lista", carrito_lista);

        //total
        total = pd.obtenerPrecioTotalCarrito(carrito_lista);
        request.setAttribute("total", total);

    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@include file="/includes/head.jsp"%>
        <title>E-Commerce</title>
    </head>
    <body>
        <%@include file="/includes/nav.jsp"%>

        <div style="width: 1110px;margin: 20px auto;">
            <%  //OPERADOR TERNARIO  %>
            <h2 style="margin-bottom: 30px">MONTO TOTAL $${ (total>0)?total :0 } </h2> 

            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">nombre</th>
                        <th scope="col">Categoria</th>
                        <th scope="col">Precio</th>
                        <th scope="col">Cantidad</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>

                    <%                    
                        if (carrito_lista != null) {
                            for (Carrito carrito : carritoPro) {%>

                    <tr>

                        <td> <%=carrito.getNombre()%> </td>
                        <td> <%=carrito.getCategoria()%> </td>
                        <td> <%=carrito.getPrecio()%> </td>
                        <td> <%=carrito.getCantidad()%> </td>


                        <td>
                            <form action="nuevaOrden" method="POST">

                                <input type="hidden" value="<%=carrito.getIdProducto()%>" name="idPro">
                                   <input type="hidden" value="<%=carrito.getCantidad()%>" name="cantidad">
                                <button type="submit" class="btn btn-primary btn-sm">Comprar</button>

                            </form>
                        </td>

                        <td><a href="removerCarrito?id=<%=carrito.getIdProducto()%>" class="btn btn-sm btn-danger">Remove</a></td>


                    </tr>

                    <% }
                        }%>
                </tbody>
            </table>

        </div>   

        <%@include file="/includes/footer.jsp"%>
    </body>
</html>
