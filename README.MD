# How to open servlets in this mess

Projekt in Ordner Speiseplan

mit IntelliJ öffnen

wenn Fehlermeldungen kommen möglicheweise den lib Ordner noch als library einbinden
(ums sauber zu machen sollte später alles in der pom.xml sein)

# How to deploy servlets

Einstellen unter
File > Project Structure > + > Web Application Archive > speiseplan.war 

Output directory unter [Ordnerpfad]/SCC-Projekt-Server/docker/webapps

Nun kann unter Build > Artifacts die speiseplan.war ausgewählt werden

Dies wird kompiliert und in den angegebenem Verzeichnis hinterlegt, Tomcat merkt die Änderung und deployt es nach kurzer Zeit

(wenn `docker-compose ... up` statt `docker-compose ... up -d` genutzt wird, dann kann man dieses deploy mitverfolgen und evtl Fehler erkennen)
