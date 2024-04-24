/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletAdmin;

import Clases.Producto;
import ClasesDao.ProductoDao;
import Conexion.dbConexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JEYSON
 */
@WebServlet("/ControladorProductos")
public class ServletControladorProductos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String accion = request.getParameter("accion");

            if (accion != null) {

                switch (accion) {
                    case "editar":

                        this.editarProducto(request, response);

                        break;
                    case "eliminar":

                        this.eliminarProducto(request, response);

                        break;

                    default:
                        out.print("Error");
                }

            } else {

                out.print("Default");
            }

        } catch (ClassNotFoundException | SQLException ex) {

            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String accion = request.getParameter("accion");
            if (accion != null) {
                switch (accion) {
                    case "insertar":

                        this.InsertarProducto(request, response);

                        break;
                    case "modificar":

                        this.ModificarProducto(request, response);

                        break;

                    default:
                        out.print("Error");
                }
            } else {
                out.print("Default");
            }
        } catch (ClassNotFoundException | SQLException ex) {

            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    private void InsertarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String nombre = request.getParameter("nombre");
            String categoria = request.getParameter("categoria");
            String precio = request.getParameter("precio");
            String imagen = request.getParameter("imagen");

            double precio_ = 0;

            if (precio != null && !"".equals(precio)) {
                precio_ = Double.parseDouble(precio);
            }

            Producto producto = new Producto();

            producto.setNombre(nombre);
            producto.setCategoria(categoria);
            producto.setPrecio(precio_);
            producto.setImagen(imagen);

            boolean objDao = new ProductoDao(dbConexion.getConnection()).insertarProductos(producto);

            if (objDao) {
                response.sendRedirect(request.getContextPath() + "/admin/productos_Admin.jsp");
            }

        }

    }

    public void ModificarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String categoria = request.getParameter("categoria");
            String precio = request.getParameter("precio");
            String imagen = request.getParameter("imagen");

            double precio_ = 0;

            if (precio != null && !"".equals(precio)) {
                precio_ = Double.parseDouble(precio);
            }

            Producto objProducto = new Producto();
            objProducto.setIdProducto(id);
            objProducto.setNombre(nombre);
            objProducto.setCategoria(categoria);
            objProducto.setPrecio(precio_);
            objProducto.setImagen(imagen);

            boolean bandera = new ProductoDao(dbConexion.getConnection()).actualizarProductos(objProducto);

            if (bandera) {

                response.sendRedirect(request.getContextPath() + "/admin/productos_Admin.jsp");

            }

        }

    }

    private void editarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        int id = Integer.parseInt(request.getParameter("id"));

        Producto objProducto = new ProductoDao(dbConexion.getConnection()).obtenerProducto(id);

        request.setAttribute("editarProducto", objProducto);

        request.getRequestDispatcher("/admin/editarProducto.jsp").forward(request, response);

    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String id = request.getParameter("id");

            if (id != null) {
                
                int ind = Integer.parseInt(id);
                
                boolean bandera = new ProductoDao(dbConexion.getConnection()).eliminarProducto(ind);
                
                out.print(bandera);

                if (bandera) {

                    response.sendRedirect(request.getContextPath() + "/admin/productos_Admin.jsp");

                }

            }


        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
