Infrastruktur für die Speiseplan-SCC app.

# Vorbereitung

Herunterladen von Docker und Docker-compose (OS spezifisch)

# Herunterladen

in einem Terminal evtl. neuen Ordner erstellen mit

`$ mkdir dev`

dann hinein navigieren

`$ cd dev`

herunterladen des Repos:

`$ git clone https://github.com/pplachetka/SCC-Projekt-Server`

# Starten der services

In das repo navigieren

`$ cd SCC-Projekt-Server`

Im Repo dann mit docker-compose die Container hochfahren


`$ docker-compose -f docker-compose.yml.caddy up -d`
> -d gibt an, dass es im Hintergrund laufen soll
>
> -f gibt an das genaue file an

man kann auch das -d weglassen, dann darf das Terminal nicht geschlossen werden, sonst werden alle Docker Prozesse geschlossen!
Das Terminal läuft dann weite rund zeigt Log Einträge an
Bspw kann man dann bei einem deploy sehen, dass Tomcat diesen deploy auch gemerkt und realisiert hat

Unter https://localhost kann nun auf Tomcat zugegriffen werden. Es wird am Anfang ncoh eine Warnmeldung erscheinen, welches besagt, dass dir URL ein ungültiges Zertifikat vorweist. Das liegt daran, dass dieses Zertifikat selbst erzeugt wurde und nicht von einer Top Level domain verifiziert wurde. Wenn ein domain Name vorliegt, kann dann umgeschalten werden und der webserver holt sich ein ordentliches Zertifikat.

Folgende Services wurden angelegt:
- reverse proxy webserver für automatisches https: hunnguye/caddy-alpine-x86_64:v1.0.4
- Application server: tomcat:9.0.29-jdk8-openjdk
- datenbank : mysql:8


# Weitere Commandos

Ausgabe der Liste der laufenden container

`$ docker ps`

