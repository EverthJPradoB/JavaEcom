/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletAdmin;

import Clases.Usuario;
import ClasesDao.UsuarioDao;
import Conexion.dbConexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JEYSON
 */
@WebServlet("/ServletCCliente")
public class ServletControlCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String accion = request.getParameter("accion2");

            if (accion != null) {
                switch (accion) {
                    case "editar":

                        this.editarCliente(request, response);

                        break;
                    case "eliminar":
                        this.eliminarCliente(request, response);
                        break;

                    default:
                        out.print("Error");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServletControlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String accion = request.getParameter("accion2");

            if (accion != null) {

                switch (accion) {

                    case "insertar":

                        this.insertUser(request, response);
                        break;
                    case "modificar":

                        this.modificarUser(request, response);
                        break;

                }

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServletControlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String nombre = request.getParameter("name");
            out.print(nombre);
            String apellido = request.getParameter("apellido");
            String telefono = request.getParameter("telefono");
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            Usuario objUsuario = new Usuario();

            objUsuario.setNombre(nombre);
            objUsuario.setApellido(apellido);
            objUsuario.setTelefono(telefono);
            objUsuario.setCorreo(email);
            objUsuario.setContraseña(pass);
            int com = new UsuarioDao(dbConexion.getConnection()).insertUSer(objUsuario);

            if (com == 1) {

                response.sendRedirect(request.getContextPath() + "/admin/usuarios_Admin.jsp");

            }

        } catch (IOException ex) {

        }
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            int id = Integer.parseInt(request.getParameter("id"));

            Usuario objCliente = new UsuarioDao(dbConexion.getConnection()).obtenerUsuario(id);

            request.setAttribute("editarCliente", objCliente);

            request.getRequestDispatcher("/admin/editarCliente.jsp").forward(request, response);

        }

    }

    private void modificarUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            int id = Integer.parseInt(request.getParameter("id"));

            String nombre = request.getParameter("nombre");

            /* String apellido = request.getParameter("apellido");
            String telefono = request.getParameter("telefono");
            String correo = request.getParameter("correo");
            String contraseña = request.getParameter("contraseña");
            //int tipo = Integer.parseInt(request.getParameter("tipo"));*/
            Usuario obUsuario = new Usuario();
            obUsuario.setIdUsuario(id);
            obUsuario.setNombre(nombre);
            /* obUsuario.setApellido(apellido);
            obUsuario.setTelefono(telefono);
            obUsuario.setCorreo(correo);
            obUsuario.setContraseña(contraseña);
            //obUsuario.setTipo(tipo);*/

            boolean bandera = new UsuarioDao(dbConexion.getConnection()).actualizarUsuario(obUsuario);

            out.print(bandera);

            if (bandera) {

                response.sendRedirect(request.getContextPath() + "/admin/usuarios_Admin.jsp");

            } else {

                out.print("Error");
            }

        }

    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            int id = Integer.parseInt(request.getParameter("id"));

            boolean bandera = new UsuarioDao(dbConexion.getConnection()).eliminarUsuario(id);

            if (bandera) {

                response.sendRedirect(request.getContextPath() + "/admin/usuarios_Admin.jsp");

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
