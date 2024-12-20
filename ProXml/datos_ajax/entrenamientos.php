<?php
// Mostrar errores de PHP
error_reporting(E_ALL);
ini_set('display_errors', 1);

// Rutas a los archivos XML y XSL
$xmlPath = __DIR__ . "/datos/entrenamientos.xml";
$xslPath = __DIR__ . "/datos/entrenamientos.xsl";

// Lo utilizaremos para verificar cambios en el XML
if (isset($_GET['checkUpdate'])) {
    if (file_exists($xmlPath)) {
        echo filemtime($xmlPath); // Devuelve la última fecha de modificación
    } else {
        echo "0"; // Si el archivo no existe, devuelve 0
    }
    exit;
}

// Verificar si existen los archivos
$htmlOutput = "";
if (file_exists($xmlPath) && file_exists($xslPath)) {
    // Procesar XML y XSLT
    try {
        $xml = new DOMDocument();
        $xml->load($xmlPath);

        $xsl = new DOMDocument();
        $xsl->load($xslPath);

        $processor = new XSLTProcessor();
        $processor->importStylesheet($xsl);

        // Guardaremos los datos de la transformacion xsl-xslt
        $htmlOutput = $processor->transformToXml($xml);
    } catch (Exception $e) {
        $htmlOutput = "<div style='color: red; text-align: center;'>Error al procesar el XML/XSL: " . $e->getMessage() . "</div>";
    }
} else {
    $htmlOutput = "<div style='color: red; text-align: center;'>Error: No se encontraron los archivos XML o XSL.</div>";
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <div id="contenido">
        <?php echo $htmlOutput; ?>
    </div>
    <script>
        let ultimaModificacion = 0;

        // Función para comprobar actualizaciones en el archivo XML
        function checkForUpdates() {
            fetch('entrenamientos.php?checkUpdate=true')
                .then(response => response.text())
                .then(timestamp => {
                    if (ultimaModificacion === 0) {
                        ultimaModificacion = timestamp; // Inicializa la variable
                    } else if (ultimaModificacion != timestamp) {
                        // Si se detecta un cambio, recargar el contenido
                        console.log("Se detectó un cambio en el XML. Recargando...");
                        location.reload();
                    }
                })
                .catch(err => console.error("Error al verificar cambios:", err));
        }

        // Comprobar actualizaciones cada 5 segundos
        setInterval(checkForUpdates, 5000);
    </script>
</body>
</html>