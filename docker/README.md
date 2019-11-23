Infrastruktur für die Speiseplan-SCC app.

# Vorbereitung

Herunterladen von Docker und Docker-compose (OS spezifisch)

# Herunterladen

in einem Terminal evtl. neuen Ordner erstellen mit

`$ mkdir dev`

dann hinein navigieren

`$ cd dev`

herunterladen des Repos:

`$ git clone https://gitlab.com/nm_hung93/speiseplan-scc-projekt.git`

# Starten der services

In das repo navigieren

`$ cd speiseplan-scc-projekt`

Im Repo dann mit docker-compose die Container hochfahren


`$ docker-compose -f docker-compose.yml up -d`
> -d gibt an, dass es im Hintergrund laufen soll
>
> -f gibt an das genaue file an

Unter https://localhost kann nun auf Tomcat zugegriffen werden. Es wird am Anfang ncoh eine Warnmeldung erscheinen, welches besagt, dass dir URL ein ungültiges Zertifikat vorweist. Das liegt daran, dass dieses Zertifikat selbst erzeugt wurde und nicht von einer Top Level domain verifiziert wurde. Wenn ein domain Name vorliegt, kann dann umgeschalten werden und der webserver holt sich ein ordentliches Zertifikat.

# Weitere Commandos

Ausgabe der Liste der laufenden container

`$ docker ps`

