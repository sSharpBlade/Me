/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import controller.General;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DtoDatos;

/**
 *
 * @author yepez
 */
@WebServlet(name = "ProductoServlet", urlPatterns = {"/productos"})
public class ProductoServlet extends HttpServlet {

    private General g = new General();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            request.getRequestDispatcher("/WEB-INF/formulario.jsp").forward(request, response);
        }
        String producto = request.getParameter("producto");
        List<DtoDatos> listaDatos = g.getDataProducto(producto);
        request.setAttribute("listaDatos", listaDatos);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int producto = Integer.parseInt(request.getParameter("producto"));
        int bodega = Integer.parseInt(request.getParameter("bodega"));
        String ubicacion = request.getParameter("ubicacion");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        
        g.guardarProducto(producto, bodega, ubicacion, cantidad);        
        response.sendRedirect("productos");
    }

}
