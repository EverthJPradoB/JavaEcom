
<div class="modal fade" id="agregarProducto">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary text-white">
                <h5 class="modal-title">Agregar Producto</h5> 
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <form action="${pageContext.request.contextPath}/ControladorProductos?accion=insertar"
                  method="POST" class="">

                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="categoria">Categoria</label>
                        <input type="text" class="form-control" name="categoria" required>
                    </div>
                    
                     <div class="form-group">
                        <label for="precio">Precio</label>
                        <input type="number" class="form-control" name="precio" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="imagen">Imagen</label>
                        <input type="text" class="form-control" name="imagen" required>
                    </div>
                  
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>    
            </form>
        </div>
    </div>
</div>
