<?php

require_once "conect.php";

class controlador
{

    static function obtenerDatos($nombreProducto)
    {
        $conn = connect::conectar();

        $sql = "SELECT b.nombre AS bodega, b.ciudad, bp.ubicacion, bp.cantidad FROM BodegaProductos bp INNER JOIN productos p ON bp.producto = p.id INNER JOIN bodegas b ON bp.bodega = b.id WHERE p.nombre LIKE '%$nombreProducto%'";

        $ex = $conn->prepare($sql);
        $ex->execute();

        $result = $ex->fetchAll(PDO::FETCH_ASSOC);

        print_r(json_encode($result));
    }

    static function agregarProducto()
    {
        $conn = connect::conectar();

        $_Data = json_decode(file_get_contents("php://input"), true);

        $producto = $_Data['producto'];
        $bodega = $_Data['bodega'];
        $ubicacion = $_Data['ubicacion'];
        $cantidad = $_Data['cantidad'];

        $sql = "INSERT INTO bodegaproductos (ubicacion, cantidad, producto, bodega) VALUES ('$ubicacion', $cantidad, $producto, $bodega)";

        $ex = $conn->prepare($sql);
        $result = $ex->execute();

        print_r(json_encode($result));
    }

    static function dataTabla($nombre)
    {
        $conn = connect::conectar();

        $sql = "SELECT * FROM $nombre";

        $ex = $conn->prepare($sql);
        $ex->execute();

        $result = $ex->fetchAll(PDO::FETCH_ASSOC);

        print_r(json_encode($result));
    }
}
