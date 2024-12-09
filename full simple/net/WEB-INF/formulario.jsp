<!DOCTYPE html>
<html>
<head>
    <title>Guardar Producto</title>
</head>
<body>
    <h1>Guardar Producto</h1>
    <form action="productos" method="POST">
        <label for="producto">Producto ID:</label>
        <input type="number" name="producto" required><br>

        <label for="bodega">Bodega ID:</label>
        <input type="number" name="bodega" required><br>

        <label for="ubicacion">Ubicación:</label>
        <input type="text" name="ubicacion" required><br>

        <label for="cantidad">Cantidad:</label>
        <input type="number" name="cantidad" required><br>

        <button type="submit">Guardar</button>
    </form>
</body>
</html>
