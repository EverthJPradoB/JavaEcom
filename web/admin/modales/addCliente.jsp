
<div class="modal fade" id="agregarCliente">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary text-white">
                <h5 class="modal-title">Agregar Producto</h5> 
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <form action="${pageContext.request.contextPath}/ServletCCliente?accion2=insertar"
                  method="POST" class="">

                <div class="modal-body">

                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" name="name">

                    </div>
                    <div class="form-group">
                        <label for="apellido">Apellido</label>
                        <input type="text" class="form-control" name="apellido">

                    </div>

                    <div class="form-group">
                        <label for="telefono">Telefono</label>
                        <input type="text" class="form-control" name="telefono">

                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" name="email">

                    </div>
                    <div class="form-group">
                        <label for="pass">Contraseña</label>
                        <input type="text" class="form-control" name="pass">
                    </div>

                    <div class="form-group">
                        <label for="tipo">Tipo</label>
                        <input type="text" class="form-control" name="tipo" value="0">

                    </div>



                </div>



                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>    
            </form>
        </div>
    </div>
</div>
