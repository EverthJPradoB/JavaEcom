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

/**
 *
 * @author JEYSON
 */
@WebServlet("/removerCarrito")
public class RemoverCarrito extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);

        try (PrintWriter out = response.getWriter()) {

            String idCarrito = request.getParameter("id");

            if (idCarrito != null) {

                ArrayList<Carrito> carrito_lista = (ArrayList<Carrito>) request.getSession().getAttribute("carrito-lista");

                if (carrito_lista != null) {

                    for (Carrito carrito : carrito_lista) {
                        if (carrito.getIdProducto() == Integer.parseInt(idCarrito)) {

                            carrito_lista.remove(carrito_lista.indexOf(carrito));
                            break;
                        }
                    }

                }
                response.sendRedirect("carrito.jsp");

            } else {
                response.sendRedirect("carrito.jsp");
            }

        }

    }


}
