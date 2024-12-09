<?php

require_once "controlador.php";

$op = $_SERVER["REQUEST_METHOD"];

switch ($op) {
    case 'GET':
        if (isset($_GET['producto'])) {
            controlador::obtenerDatos($_GET['producto']);
            break;
        }

        if (isset($_GET['tabla'])) {
            controlador::dataTabla($_GET['tabla']);
            break;
        }

    case 'POST':
        controlador::agregarProducto();
        break;

    default:
        # code...
        break;
}
