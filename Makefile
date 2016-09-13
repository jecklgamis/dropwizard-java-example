default:
	cat ./Makefile
dist: jar docker-image
	@echo All done
jar:
	mvn clean package
docker-image:
	docker build -t dropwizard-java-example .
docker-run:
	docker run -p 8080:8080 dropwizard-java-example
docker-run-bash:
	docker run -i -t dropwizard-java-example /bin/bash
