# fellowship

| Main Pages         |  Function                                                                                                               |
|--------------------|-------------------------------------------------------------------------------------------------------------------------|
| `/signup`          | Allows users to create an account                                                                                       |
| `/login`           | Allows users with an account to login, will have a query parameter added to the address in the event of a failed loggin |
| `/in/`             | Requires login, the main feed for the site, will post all users posts in the order that they were made                  |
| `/in/profile/{id}` | Requires login, reroutes to a users unique profile page, page has all of that users posts listed                        |
| `/in/profile`      | Requires login, automatically reroutes to the the current users profile page                                            |


#### Future Features
- Every test in the whole world
- Posts and Users are automatically timestamped without the need for user input
- The ability to follow and be followed by other users. This will @ first be purely a feature to show support, future patches will create a feed that shows users the posts only of the users theyve followed

#### Known Bugs
- Logging in with an old account sometimes reroutes to a css page

##### Resources
[Error Landing Page](https://www.logicbig.com/tutorials/spring-framework/spring-boot/custom-thymeleaf-error-page.html)

I copied this error landing page wholesale

[Color Scheme](/https://coolors.co/4b7f52-dcfffd-7dd181-929487-ffffff)

I picked my main color and this site made a color theme for me

