<?php

define("host", "localhost");
define("user", "root");
define("pass", "");
define('db', 'pruebasoa');

class Connect
{

    static function conectar()
    {
        $conn = new PDO('mysql:host=' . host . ';dbname=' . db, user, pass);
        return $conn;
    }
}
