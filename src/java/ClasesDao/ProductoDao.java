/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesDao;

import Clases.Carrito;
import Clases.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JEYSON
 */
public class ProductoDao {

    private static final String SELECT_FROM_PRODUCTOS = "SELECT * FROM producto";

    private String query;

    private Connection con;

    private PreparedStatement stmt;

    private ResultSet rs;

    public ProductoDao(Connection con) {

        this.con = con;

    }

    public List<Producto> obtenerProductos() {

        List<Producto> arrayProductos = new ArrayList<>();

        try {

            stmt = this.con.prepareStatement(SELECT_FROM_PRODUCTOS);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Producto objProducto = new Producto();

                objProducto.setIdProducto(rs.getInt("id_producto"));
                objProducto.setNombre(rs.getString("nombre"));

                objProducto.setCategoria(rs.getString("categoria"));

                objProducto.setPrecio(rs.getDouble("precio"));

                objProducto.setImagen(rs.getString("imagen"));

                arrayProductos.add(objProducto);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayProductos;
    }

    public List<Carrito> obtenerCarritoProductos(ArrayList<Carrito> carritoLista) {

        List<Carrito> productos = new ArrayList<Carrito>();

        try {

            if (carritoLista.size() > 0) {

                for (Carrito item : carritoLista) {

                    query = "select * from producto where id_producto=?";
                    stmt = this.con.prepareStatement(query);

                    stmt.setInt(1, item.getIdProducto());
                    System.out.println(item.getIdProducto());

                    rs = stmt.executeQuery();

                    while (rs.next()) {

                        Carrito fila = new Carrito();

                        fila.setIdProducto(rs.getInt("id_producto"));
                        fila.setNombre(rs.getString("nombre"));
                        fila.setCategoria(rs.getString("categoria"));
                        fila.setPrecio(rs.getDouble("precio") * item.getCantidad());
                        fila.setCantidad(item.getCantidad());

                        productos.add(fila);

                    }

                }

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return productos;

    }

    public double obtenerPrecioTotalCarrito(ArrayList<Carrito> carritoLista) {

        double total = 0;

        try {

            if (carritoLista.size() > 0) {

                for (Carrito item : carritoLista) {

                    query = "SELECT precio FROM producto Where id_producto = ?";

                    stmt = this.con.prepareStatement(query);

                    stmt.setInt(1, item.getIdProducto());

                    rs = stmt.executeQuery();

                    while (rs.next()) {

                        total += rs.getDouble("precio") * item.getCantidad();

                    }

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;

    }

    public Producto obtenerProducto(int id) {

        Producto oProducto = null;
        try {
            query = "select * from producto where id_producto=? ";

            stmt = this.con.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                oProducto = new Producto();
                oProducto.setIdProducto(rs.getInt("id_producto"));
                oProducto.setNombre(rs.getString("nombre"));
                oProducto.setCategoria(rs.getString("categoria"));
                oProducto.setPrecio(rs.getDouble("precio"));
                oProducto.setImagen(rs.getString("imagen"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return oProducto;

    }

    /*ADMIN*/
    public boolean insertarProductos(Producto objProducto) {

        boolean estado = false;

        try {

            stmt = this.con.prepareStatement("INSERT INTO `producto`(`nombre`, `categoria`, `precio`, `imagen`) "
                    + "VALUES ( ? , ? , ? ,? ) ");

            stmt.setString(1, objProducto.getNombre());
            stmt.setString(2, objProducto.getCategoria());
            stmt.setDouble(3, objProducto.getPrecio());
            stmt.setString(4, objProducto.getImagen());

            stmt.executeUpdate();

            estado = true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return estado;

    }

    public boolean actualizarProductos(Producto objProducto) {

        boolean estado = false;

        try {

            stmt = this.con.prepareStatement("UPDATE `producto` SET `nombre`=?,"
                    + "`categoria`= ?,`precio`=?,"
                    + "`imagen`=? WHERE id_producto = ?");

            stmt.setString(1, objProducto.getNombre());
            stmt.setString(2, objProducto.getCategoria());
            stmt.setDouble(3, objProducto.getPrecio());
            stmt.setString(4, objProducto.getImagen());

            stmt.setInt(5, objProducto.getIdProducto());

            stmt.executeUpdate();

            estado = true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return estado;

    }

    public boolean eliminarProducto(int id) {
        boolean estado = false;

        try {

            stmt = this.con.prepareStatement("DELETE FROM `producto` WHERE id_producto = ?");
            
            stmt.setInt(1, id);

            stmt.executeUpdate();

            estado = true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return estado;

    }

}
