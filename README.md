# fellowship

Application User

| Parameter            | type   | about                                                    |
|----------------------|--------|----------------------------------------------------------| 
| `userName`           | String | Unique among all acounts                                 |
| `password`           | String | Bcrypted with a Salt                                     | 
| `firstName`          | String |                                                          |
| `statementOfPurpose` | String |                                                          |

Account Controller

| Route       |  Functionality                                                   |
|-------------|------------------------------------------------------------------|
| `/login`    | All traffic is redirected here until a authorization is made     |
| `/signup`   | Allows users to create a new account                             |
