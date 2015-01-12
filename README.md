
Bedrosians IMS UI TEST
=============

Bedrosians IMS UI TEST written in Java


## Dev Environment

* Mac OS X v10.7.5 or greater

* VirtualBox with Ubuntu 11.10 x64 Server

* Git


## Setup

* Install and setup VirtualBox with Ubuntu 11.10 x64 Server

https://github.com/beachsidecoders/beachside-dev-central/blob/master/virtualbox/vbx-osx-ubuntu.md

* Install and setup Git on Ubuntu with ssh key access 

http://vcs.bedrosians.com/bedrosians/bedrosians-dev-central/blob/master/vcs/git.md

* Install maven, jdk 6, and curl

```sh
sudo apt-get install -y maven2 openjdk-6-jdk curl

# Choose java-6 jre if it's not already selected
sudo update-alternatives --config java
sudo update-alternatives --config javac
sudo update-alternatives --config javaws
```

* Project Files

```sh
# clone repo
git clone ssh://git@vcs.bedrosians.com:2222/bedrosians/bedims-uitest.git

cd bedims-uitest
```

* Installation - Build, Install, and Run. Installation directory is /opt/bedims-uitest

```sh
cd bedims-uitest
setup/installbedims-uitest.sh
```

* Upgrade

```sh
cd bedims-uitest
git pull
setup/installbedims-uitest.sh
```

* Start/Stop/Statuc

```sh
# Start
sudo start bedims-uitest

# Stop
sudo stop bedims-uitest

# Status
sudo status bedims-uitest
```

## Development

* Building

```sh
mvn clean package
```

* Running/Stopping

```sh
mvn jetty:run
# To stop, type Ctrl+c on the Jetty shell console
```

## Verify

See test/README.md

## Acknowledgements
BedLogic makes use of the following third-party open source libraries:

* Jetty (http://www.eclipse.org/jetty) - HTTP server
* Jersey (https://jersey.java.net) - JAX-RS REST implementation
* Spring Framework (http://projects.spring.io/spring-framework) - Application features: Dependency Injection, IOC, Data Access via ODBC, and spring-test module for integration testing
* JUnit (http://junit.org) - Testing framework
* Mockito (http://code.google.com/p/mockito/) - Mocking framework
* SLF4J (http://www.slf4j.org) and Logback (http://logback.qos.ch) - Logging api via SLF4J, and implementation via Logback.
=======
Bed IMS UI TEST
=============

Bedrosians IMS UI TEST


>>>>>>> refs/remotes/origin/develop
