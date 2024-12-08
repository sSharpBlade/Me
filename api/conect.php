<?php

class connect
{
    static function conectar()
    {
        define("host", "localhost");
        define("database", "pruebasoa");
        define("user", "root");
        define("pass", "");

        try {
            $con = new PDO("mysql:host=" . host . ";dbname=" . database, user, pass);
            return $con;
        } catch (PDOException $e) {
            echo "Error al conectar: " . $e->getMessage();
        }
    }
}
