# TokenManager
API built with the intention to manage access tokens.

## Getting Started
Open the terminal. Put the commands below to download and start the project:
* $> git clone https://github.com/sergiovlvitorino/tokenmanager
* $> cd tokenmanager
* $> mvn package
* $> java -jar target/tokenmanager-1.0.0.jar

### Prerequisites
*JDK 1.8

*Maven 3

### Method's description
* (GET) - {url}/api/create

* (GET) - {url}/api/check/{token}

* (GET) - {url}/api/find/{token}

* (PUT) - {url}/api/refresh/{token}

* (DELETE) - {url}/api/destroy/{token}

### Setting inactive period(milliseconds)
Update the property $tokenmanager.interval located into src/resources/application.properties 

### Running tests
Open the terminal. Put the commands below to test:
* $> cd tokenmanager
* $> mvn clean test

## Authors

* **Sergio Vitorino** - (https://github.com/sergiovlvitorino)

See also the list of [contributors](https://github.com/sergiovlvitorino/tokenmanager/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
