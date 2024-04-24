<%-- 
    Document   : index.jsp
    Created on : 30/11/2021, 04:45:29 PM
    Author     : JEYSON
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="Clases.Carrito"%>
<%@page import="java.util.List"%>
<%@page import="ClasesDao.ProductoDao"%>
<%@page import="Clases.Producto"%>
<%@page import="Conexion.dbConexion"%>
<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    //VERIFICAR USUARIO
    Usuario userVerification = (Usuario) request.getSession().getAttribute("user-logged");

    if (userVerification != null) {

        request.setAttribute("user", userVerification);
    }

    /*LISTAR PRODUCTOS*/
    ProductoDao pd = new ProductoDao(dbConexion.getConnection());

    List<Producto> ArrayProductoDao = pd.obtenerProductos();

    /*LISTAR CARRITO*/
    ArrayList<Carrito> carrito_lista = (ArrayList<Carrito>) session.getAttribute("carrito-lista");

    if (carrito_lista != null) {

        request.setAttribute("carrito_lista", carrito_lista);
    }

%>


<!DOCTYPE html>
<html>
    <head>
        <%@include file="/includes/head.jsp"%>
        <title>E-Commerce</title>

    </head>
    <body>
        <%@include file="/includes/nav.jsp"%>

        <div class="container">
            <div class="row">

                <%                            if (!ArrayProductoDao.isEmpty()) {
                        for (Producto p : ArrayProductoDao) {
                %>
                <div class="col-md-3 my-3">
                    <div class="card w-100">
                        <img class="card-img-top"  height="200px"  src="imagenes/<%=p.getImagen()%>"
                             alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title"><%=p.getNombre()%></h5>
                            <h6 class="price">Precio $<%=p.getPrecio()%></h6>
                            <h6 class="category">Categoria <%=p.getCategoria()%></h6>
                            <div class="mt-3 ">
                                <% //<a class="btn btn-primary" href="">Comprar </a> %>
                                <a class="btn btn-success  float-start"  style="margin-left: 140px" href="CarritoServlet?id=<%=p.getIdProducto()%>">Añadir </a> 


                            </div>
                        </div>
                    </div>
                </div>
                <%
                        }
                    } else {
                        out.println("No hay productos");
                    }
                %>



            </div>
        </div>



        <%@include file="/includes/footer.jsp"%>


    </body>
</html>
