/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

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
@WebServlet("/registrarUsuario")
public class RegistrarUsuarioServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs public class
     * RegistrarUsuarioServlet extends HttpServlet {
     *
     * /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

                response.sendRedirect("login.jsp");

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegistrarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
