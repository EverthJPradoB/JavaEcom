<%-- 
    Document   : index.jsp
    Created on : 02/12/2021, 11:38:07 PM
    Author     : JEYSON
--%>

<%@page import="ClasesDao.ProductoDao"%>
<%@page import="java.util.List"%>
<%@page import="Conexion.dbConexion"%>
<%@page import="Clases.Producto"%>
<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>

<%

    Usuario userVerification = (Usuario) request.getSession().getAttribute("user-logged");

    if (userVerification != null && userVerification.getTipo() == 1) {

        request.setAttribute("user", userVerification);

    } else {

        response.sendRedirect("../index.jsp");
    }

    List<Producto> listaProductos = new ProductoDao(dbConexion.getConnection()).obtenerProductos();

    request.setAttribute("listaProductos", listaProductos);
    request.setAttribute("totalProductos", listaProductos.size());

%>


<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/includes/head.jsp"%>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css">
        <script src="https://kit.fontawesome.com/e2c3a7ed5d.js" crossorigin="anonymous"></script>
        <title>Admistrador - Productos</title>
    </head>

    <body>

        <%@include file="../includes/sidebar_1.jsp"%>

        <div class="container">

            <!--  HEADER -->
            <header id="header" class="py-2  bg-info text-white">

                <div class="container">

                    <div>

                        <h1 id="" > <i class="fab fa-product-hunt"></i>PRODUCTOS  </h1>

                    </div>

                </div>

            </header> 

            <section id="section" class="py-4 mb-4 bg-light">

                <div class="container">

                    <div>
                        <a href="" class="btn btn-primary" 
                           data-toggle="modal" data-target ="#agregarProducto"

                           > <i class="fas fa-plus"></i> Agregar Producto</a>

                    </div>
                </div>

            </section>

            <% //LISTADO DE PRODUCTO %> 
            <section id="sectionProductos">

                <div class="container">

                    <div class="card">
                        <h3 class="card-header d-flex justify-content-between" ><span>Listado de Productos</span>  <span> Total de Productos: <i class="fas fa-shopping-cart"></i> ${totalProductos}</span> </h3>

                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Categoria</th>
                                    <th scope="col">Precio</th>
                                    <th scope="col">imagen</th>
                                    <th scope="col"></th>

                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="producto" items="${listaProductos}">
                                    <tr>
                                        <td> ${producto.idProducto} </td>
                                        <td> ${producto.nombre} </td>
                                        <td> ${producto.categoria} </td>
                                        <td> ${producto.precio} </td>
                                        <td>  <img src="${pageContext.request.contextPath}/imagenes/${producto.imagen}" width="100" height="100"> </td>
                                        <td><a class="btn btn-warning"  href="${pageContext.request.contextPath}/ControladorProductos?accion=editar&id=${producto.idProducto}"><i class="far fa-edit"></i> Editar</a></td>
                                    </tr>

                                </c:forEach>

                            </tbody>
                        </table>

                    </div>


                </div>

            </section>
            <% //-----AGREGAR PRODUCTO- %> 
            <jsp:include page="../admin/modales/agregarProducto.jsp"/>


        </div>

        <%@include file="/includes/sidebar_2.jsp"%>

        <script src="<%=request.getContextPath()%>/js/scripts.js"></script>

        <%@include file="/includes/footer.jsp"%>


    </body>
</html>