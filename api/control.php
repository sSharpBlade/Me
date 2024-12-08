<?php

require_once "connect.php";

class Control
{

    static function obtenerInfo()
    {
        $conn = connect::conectar();
        $sql = "SELECT p.nombre AS producto, b.nombre AS bodega, (SELECT SUM(bp2.cantidad) FROM bodegaproductos bp2 WHERE bp2.producto = bp.producto) AS total FROM bodegaproductos bp JOIN productos p ON bp.producto = p.id JOIN bodegas b ON bp.bodega = b.id GROUP BY b.nombre, p.nombre ORDER BY p.nombre";

        $ex = $conn->prepare($sql);
        $result = $ex->execute();
        $result = $ex->fetchAll(PDO::FETCH_ASSOC);

        print_r(json_encode($result));
    }
}
