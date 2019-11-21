#!/bin/bash

echo "Bitte den Pfad einfuegen in dem du deine Servlets speichern moechtest. Dazu Pfad kopieren und mit rechtsclick auf diese Subshell Flaeche einfuegen"

read -r "path"

echo "${path,}" | sed s/://g | sed "s/\\\/\//g"  > config/windows_path

echo "Danke, dein Windowspfad ist "$path" Der dazugehörige Unix Pfad ist "$(cat config/windows_path)" " 
