default:
	cat ./Makefile
dist: 
	mvn clean package
image:
	 docker build -t dropwizard-java-example .
run:
	 docker run -p 8080:8080 -p 8081:8081 -p 8443:8443 dropwizard-java-example
run-bash:
	 docker run -i -t dropwizard-java-example /bin/bash
keystore:
	 ./generate-keystore.sh
