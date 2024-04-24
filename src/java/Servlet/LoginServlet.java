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
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String user = request.getParameter("txtUser");
            String pass = request.getParameter("txtPass");

            UsuarioDao userDAO = new UsuarioDao(dbConexion.getConnection());

            //validar si es que el usuario si existe , se guada en un objeto
            Usuario objOUsuario = userDAO.validarLogin(user, pass);

            // si el objeto es diferente de nulo, si existe
            if (objOUsuario != null) {
                request.getSession().setAttribute("user-logged", objOUsuario);

                //request.getRequestDispatcher("index.jsp").forward(request, response);

                response.sendRedirect("index.jsp");
            } else {
                
                request.setAttribute("Errores", "Error de autentificacion");
                
                //response.sendRedirect("login.jsp");
                request.getRequestDispatcher("login.jsp").forward(request, response);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
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
