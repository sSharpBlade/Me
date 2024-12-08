<?php

require_once "control.php";

$op = $_SERVER['REQUEST_METHOD'];

switch ($op) {
    case 'GET':
        control::obtenerInfo();
        break;
    default:
        break;
}
