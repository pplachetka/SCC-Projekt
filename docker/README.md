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

Nun kann über localhost:80 nachgeprüft werden, ob der Service auf port 80 bereitsteht.
Jetty müsste einen 404 Error ausgeben, weil wir noch keine Services deployt haben, aber der Server steht bereits

# Weitere Commandos

Ausgabe der Liste der laufenden container

`$ docker ps`

