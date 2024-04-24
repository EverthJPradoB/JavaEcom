
<div class="d-flex" id="wrapper">
    <!-- Sidebar-->
    <div class="border-end bg-white" id="sidebar-wrapper">
        <div class="sidebar-heading border-bottom bg-light"> ${user.getNombre()}</div>
        <div class="list-group list-group-flush">
            <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%=request.getContextPath()%>/admin/usuarios_Admin.jsp">Usuarios</a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%=request.getContextPath()%>/admin/productos_Admin.jsp">Productos</a>

        </div>
    </div>
    <!-- Page content wrapper-->
    <div id="page-content-wrapper">
        <!-- Top navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <div class="container-fluid">
                <button class="btn btn-primary " id="sidebarToggle">   <span class="navbar-toggler-icon"></span></button>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mt-2 mt-lg-0">

                        <li class="nav-item"><a class="nav-link"href="<%=request.getContextPath()%>/cerrar-session">Cerrar Session</a></li>

                    </ul>
                </div>
            </div>
        </nav>
        <!-- Page content-->
        <div class="container-fluid">

