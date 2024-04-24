/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Clases.Carrito;
import Clases.Orden;
import Clases.Usuario;
import ClasesDao.OrdenDao;
import Conexion.dbConexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
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
@WebServlet("/nuevaOrden")
public class NuevaOrdenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

            Date fecha = new Date();

            //verificamos que el usuario
            Usuario userVerification = (Usuario) request.getSession().getAttribute("user-logged");

            if (userVerification != null) {

                String productoId = request.getParameter("idPro");
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));

                Orden nuevaOrden = new Orden();

                nuevaOrden.setIdProducto(Integer.parseInt(productoId));
                nuevaOrden.setIdUser(userVerification.getIdUsuario());
                nuevaOrden.setCantidad(cantidad);
                nuevaOrden.setFecha(formatoFecha.format(fecha));

                OrdenDao or_dao = new OrdenDao(dbConexion.getConnection());

                boolean result = or_dao.insertarOrden(nuevaOrden);

                if (result) {

                    ArrayList<Carrito> listaCarrito = (ArrayList<Carrito>) request.getSession().getAttribute("carrito-lista");

                    if (listaCarrito != null) {

                        for (Carrito carrito : listaCarrito) {

                            if (carrito.getIdProducto() == (Integer.parseInt(productoId))) {

                                listaCarrito.remove(listaCarrito.indexOf(carrito));
                                break;
                            }

                        }

                    }

                    response.sendRedirect("orden.jsp");

                } else {

                    out.print("Orden fallida");

                }

            } else {
                response.sendRedirect("login.jsp");
            }

        } catch (ClassNotFoundException | SQLException ex) {

            ex.printStackTrace();

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
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
