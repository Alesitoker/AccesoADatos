<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <xsl:apply-templates/>
        </html>
    </xsl:template>
    <xsl:template match="ListContact">
        <head>
            <title>Contact list</title>
        </head>
        <body>
            <h1>Contact list</h1>
            <table border="1">
                <tr>
                    <th>Name</th>
                    <th>Address</th>
                    <th>PhoneNumber</th>
                    <th>ZipCode</th>
                    <th>Birthdate</th>
                    <th>Debt</th>
                    <th>DebtAmount</th>
                </tr>
                <xsl:apply-templates select="Contact"/>
            </table>
        </body>
    </xsl:template>
    <xsl:template match="Contact">
        <tr>
            <xsl:apply-templates/>
        </tr>
    </xsl:template>
    <xsl:template match="name|address|phoneNumber|zipCode|birthdate|debt|debtAmount">
        <td>
            <xsl:apply-templates/>
        </td>
    </xsl:template>
</xsl:stylesheet>