<?php
// Ruta al archivo CSV
$csvPath = __DIR__ . "/estadisticas/estadisticas.csv";

// Verificar si el archivo existe
if (!file_exists($csvPath)) {
    die("<div class='error-message'>El archivo CSV no fue encontrado.</div>");
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Estadísticas</title>
    <!-- Enlace al CSS -->
    <link rel="stylesheet" href="estadisticas/estadisticas.css">
</head>
<body>
    <div class="container">
        <h1>Estadísticas Generales</h1>
        <table class="table-styled">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Valores</th>
                </tr>
            </thead>
            <tbody>
                <?php
                // Abrir el archivo CSV
                $file = fopen($csvPath, "r");

                while (($data = fgetcsv($file, 1000, ",")) !== FALSE) {
                    // Ignorar filas vacías
                    if (empty(array_filter($data))) {
                        continue;
                    }
                    echo "<tr>";
                    foreach ($data as $cell) {
                        echo "<td>" . htmlspecialchars($cell) . "</td>";
                    }
                    echo "</tr>";
                }
                fclose($file);
                ?>
            </tbody>
        </table>
    </div>
</body>
</html>