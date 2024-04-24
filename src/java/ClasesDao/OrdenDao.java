/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesDao;

import Clases.Orden;
import Clases.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author JEYSON
 */
public class OrdenDao {

    private String query;

    private Connection con;

    private PreparedStatement stmt;

    private ResultSet rs;

    public OrdenDao(Connection con) {

        this.con = con;

    }

    public boolean insertarOrden(Orden objetoModel) {

        boolean result = false;

        try {

            query = "INSERT INTO `ordenes`( `id_producto`, `id_usuario`, `cantidad`, `orden_fecha`) "
                    + "VALUES ( ? , ? , ? ,?)";

            stmt = this.con.prepareStatement(query);
            stmt.setInt(1, objetoModel.getIdProducto());
            stmt.setInt(2, objetoModel.getIdUser());
            stmt.setInt(3, objetoModel.getCantidad());
            stmt.setString(4, objetoModel.getFecha());
            stmt.executeUpdate();
            result = true;

        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }

        return result;
    }

    public List<Orden> obtenerListaOrden(int id) {

        List<Orden> arrayOrden = new ArrayList<Orden>();

        try {

            stmt = this.con.prepareStatement("SELECT * FROM ordenes where id_usuario = ?");

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {

                Orden objOrden = new Orden();

                ProductoDao objDao = new ProductoDao(this.con);

                int productoId = rs.getInt("id_producto");

                objDao.obtenerProducto(productoId);
                //Retorna el producto buscado

                Producto prod = objDao.obtenerProducto(productoId);

                objOrden.setIdOrden(rs.getInt("id_orden"));
                objOrden.setIdProducto(productoId);
                objOrden.setNombre(prod.getNombre());
                objOrden.setCategoria(prod.getCategoria());
                objOrden.setPrecio(prod.getPrecio());

                objOrden.setCantidad(rs.getInt("cantidad"));
                objOrden.setFecha(rs.getString("orden_fecha"));
                arrayOrden.add(objOrden);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return arrayOrden;
    }

    public void EliminarOrden(int id) {

        try {

            stmt = this.con.prepareStatement("DELETE FROM `ordenes` WHERE id_orden = ?");
            
            stmt.setInt(1, id);
            
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

}
