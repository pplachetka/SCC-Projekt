#!/bin/bash

set -e

windows_path=$(cat config/windows_path)


cp -r /mnt/$windows_path/ ./webapps

echo "kopiert von /mnt/$windows_path/ nach $(pwd)/webapps"
ls webapps
