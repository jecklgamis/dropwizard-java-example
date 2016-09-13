#!/bin/bash

APP_HOME=/usr/local/app
set -e

if [ "$1" = 'app' ]; then
    cd /usr/local/app &&  exec java -jar "${APP_HOME}/bin/dropwizard-java-example.jar" server "${APP_HOME}/config/config.yml"
else
    exec "$@"
fi

