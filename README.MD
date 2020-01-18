# Changelog v0.2

alle Features implementiert:

setMenuItemSchedule
getMenuItemSchedule
getMenuItemScheduleCustomerOrder
setMenuItemScheduleCustomerOrder
getMenuItemList
setMenuItem
login

url parameter jetzt camelCase
alle notwendigen Endpunkte die Autorisierung benötigen werden mit Hilfe des Tokens geprüft
Response status 401 = unauthorized access jetzt auch beim login endpunkt (d.h braucht eig nicht die json mit isAdmin = -1 lesen

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
