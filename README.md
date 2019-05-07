# fellowship

Application User

| Parameter            | type   | about                                                    |
|----------------------|--------|----------------------------------------------------------| 
| `userName`           | String | Unique among all acounts                                 |
| `password`           | String | Bcrypted with a Salt                                     | 
| `firstName`          | String |                                                          |
| `lastIntial`         | String | I want to forbid users from commiting their last name    |
| `statementOfPurpose` | String |                                                          |

Account Controller

| Route       |  Functionality                                                   |
|-------------|------------------------------------------------------------------|
| `/login`    | All traffic is redirected here until a authorization is made     |
| `/signup`   | Allows users to create a new account                             |
