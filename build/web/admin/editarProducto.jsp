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

            <form action="${pageContext.request.contextPath}/ControladorProductos?accion=modificar&id=${editarProducto.idProducto}"
                  method="POST" class="">

                <!--Botones de Navegacion -->
                <section class="container my-3">

                    <div class="row">
                        <div class="col-sm d-flex justify-content-between">
                            <a href="<%=request.getContextPath()%>/admin/productos_Admin.jsp" class="btn btn-secondary">
                                <i class="fas fa-arrow-left"></i> Regresar al inicio
                            </a>
                            <a href="${pageContext.request.contextPath}/ControladorProductos?accion=eliminar&id=${editarProducto.idProducto}" class="btn btn-danger">
                                <i class="fas fa-arrow-left"></i> Eiminar
                            </a>
                        </div>

                        <div class="col-sm">

                        </div>


                    </div>


                </section>



                <section id="detalle_cliente " class="w-50">
                    <div class="container">
                        <div class="card">
                            <div class="card-header">
                                <h4>Editar Cliente</h4>
                            </div>
                            <div class="card-body">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="nombre">Nombre</label>
                                        <input type="text" class="form-control" value="${editarProducto.nombre}" name="nombre" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="categoria">Categoria</label>
                                        <input type="text" class="form-control" value="${editarProducto.categoria}" name="categoria" required>
                                    </div>

                                    <div class="form-group">
                                        <label for="precio">Precio</label>
                                        <input type="number" class="form-control" value="${editarProducto.precio}" name="precio" required>
                                    </div>

                                    <div class="form-group">
                                        <label for="imagen">Imagen</label>
                                        <input type="text" class="form-control" value="${editarProducto.imagen}" name="imagen" required>
                                    </div>

                                </div>

                                <div>

                                    <button type="submit"   class="btn btn-warning float-end">Enviar Formulario</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

            </form>


        </div>

        <%@include file="/includes/sidebar_2.jsp"%>

        <script src="<%=request.getContextPath()%>/js/scripts.js"></script>

        <%@include file="/includes/footer.jsp"%>


    </body>
</html>