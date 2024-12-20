<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes" />
    <xsl:template match="/">
        <html lang="en">
            <head>
                <meta charset="UTF-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                <style>
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        margin: 0;
                        padding: 20px;
                        background-color: #f4f7f6;
                        color: #333;
                    }
                    h1 {
                        text-align: center;
                        color: #2c3e50;
                        margin-bottom: 20px;
                        font-size: 2.5em;
                        text-transform: uppercase;
                        letter-spacing: 1.5px;
                    }
                    table {
                        width: 80%;
                        margin: 0 auto;
                        border-collapse: collapse;
                        background-color: #fff;
                        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
                        border-radius: 8px;
                        overflow: hidden;
                    }
                    th {
                        background-color: #34495e;
                        color: #ecf0f1;
                        padding: 15px 10px;
                        font-size: 1.1em;
                        text-transform: uppercase;
                        letter-spacing: 1px;
                    }
                    td {
                        padding: 12px 10px;
                        text-align: center;
                        color: #2c3e50;
                        font-size: 1em;
                    }
                    tr:nth-child(even) {
                        background-color: #f2f2f2;
                    }
                    tr:hover {
                        background-color: #e1f5fe;
                        transition: background-color 0.3s ease;
                    }
                    .footer {
                        margin-top: 20px;
                        text-align: center;
                        font-size: 0.9em;
                        color: #7f8c8d;
                    }
                </style>
            </head>
            <body>
                <h1>Entrenamientos Disponibles</h1>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Duración (min)</th>
                        <th>Nivel</th>
                    </tr>
                    <xsl:for-each select="entrenamientos/entrenamiento">
                        <tr>
                            <td><xsl:value-of select="@id" /></td>
                            <td><xsl:value-of select="nombre" /></td>
                            <td><xsl:value-of select="duracion" /></td>
                            <td><xsl:value-of select="nivel" /></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>