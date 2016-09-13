#!/usr/bin/env bash
set -ex
java -jar target/dropwizard-java-example.jar server src/main/resources/config.yml
