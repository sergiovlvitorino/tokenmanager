# tokenmanager
This project is an API created with the intention to manage token.


Method's Description:


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
