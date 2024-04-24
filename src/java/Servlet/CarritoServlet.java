/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Clases.Carrito;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            ArrayList<Carrito> carritoLista = new ArrayList<>();

            int idPro = Integer.parseInt(request.getParameter("id"));

            Carrito cm = new Carrito();
            cm.setIdProducto(idPro);
            cm.setCantidad(1);

            HttpSession session = request.getSession();
            ArrayList<Carrito> carrito_lista = (ArrayList<Carrito>) session.getAttribute("carrito-lista");

            if (carrito_lista == null) {
                carritoLista.add(cm);
                session.setAttribute("carrito-lista", carritoLista);
                response.sendRedirect("index.jsp");
            } else {

                carritoLista = carrito_lista;
                boolean existe = false;

                for (Carrito carrito : carrito_lista) {
                    if (carrito.getIdProducto() == idPro) {
                        existe = true;
                        out.print("Ya existe en el carrito");

                    }

                }
                if (!existe) {

                    carritoLista.add(cm);
                    response.sendRedirect("index.jsp");
                }

            }

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
