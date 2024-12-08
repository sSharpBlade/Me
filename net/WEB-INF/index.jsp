<%@page import="model.DtoInfo"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Productos</title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>Productos</th>
                    <th>Bodegas</th>
                    <th>Cantidad</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<DtoInfo> listaDatos = (List<DtoInfo>) request.getAttribute("listado");
                    String productoActual = ""; // Producto actual en el bucle
                    int contadorFilas = 0;      // Para contar las filas del producto actual
                    int index = 0;             // Índice para iterar los datos

                    if (listaDatos != null) {
                        while (index < listaDatos.size()) {
                            DtoInfo dato = listaDatos.get(index);
                            productoActual = dato.getProducto();
                            contadorFilas = 0;

                            // Contar cuántas filas corresponden al producto actual
                            for (DtoInfo temp : listaDatos) {
                                if (temp.getProducto().equals(productoActual)) {
                                    contadorFilas++;
                                }
                            }
                %>
                <tr>
                    <td rowspan="<%= contadorFilas %>"><%= dato.getProducto() %></td>
                    <td><%= dato.getBodega() %></td>
                    <td rowspan="<%= contadorFilas %>"><%= dato.getCantidad() %></td>
                </tr>
                <%
                            // Iterar sobre las filas restantes del mismo producto
                            for (int i = 1; i < contadorFilas; i++) {
                                index++;
                                dato = listaDatos.get(index);
                %>
                <tr>
                    <td><%= dato.getBodega() %></td>
                </tr>
                <%
                            }
                            index++;
                        }
                    } else {
                %>
                <tr>
                    <td colspan="3">No hay datos disponibles</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
