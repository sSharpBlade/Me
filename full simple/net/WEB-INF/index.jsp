<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Productos</title>
    </head>
    <body>
        <form action="productos" method="get">
            <input type="text" name="producto" placeholder="Nombre del Producto">
            <button type="submit">Buscar</button>
        </form>
        
        <a href="productos?action=add">Formulario</a>
        
        <h1>Lista de Productos para: <%= request.getParameter("producto")%></h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Bodega</th>
                    <th>Ciudad</th>
                    <th>Ubicación</th>
                    <th>Cantidad</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<model.DtoDatos> listaDatos = (List<model.DtoDatos>) request.getAttribute("listaDatos");

                    if (listaDatos != null) {
                        for (model.DtoDatos dato : listaDatos) {
                %>
                <tr>
                    <td><%= dato.getBodega()%></td>
                    <td><%= dato.getCiudad()%></td>
                    <td><%= dato.getUbicacion()%></td>
                    <td><%= dato.getCantidad()%></td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="4">No hay datos disponibles</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
