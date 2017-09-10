# TokenManager

An API created to manage token.

## Getting Started

Open the terminal. Put the commands below to download and start the project:
$> git clone https://github.com/sergiovlvitorino/tokenmanager
$> cd tokenmanager
$> mvn spring-boot:run

### Prerequisites

JDK 1.8
Maven 3

### Method's description
{url}/api/create
Http Method: get
Returns: json

{url}/api/check/{token}
Http Method: get
Returns: HttpStatus

{url}/api/refresh
Http Method: put
Param: {token}
Returns: HttpStatus

{url}/api/destroy
Http Method: delete
Param: {token}
Returns: HttpStatus

{url}/api/countActiveAccounts
Http Method: get
Returns: text

## Authors

* **Sergio Vitorino** - (https://github.com/sergiovlvitorino)

See also the list of [contributors](https://github.com/sergiovlvitorino/tokenmanager/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
