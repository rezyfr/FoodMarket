﻿# Food Market
## BDD Specs
### User Story #1 Authentication
#### Register
```
As a unregistered user
I want to register as an user on the app
So I can login and access the FoodMarket features
```
##### Register Scenarios
```
Given the user has connectivity
When the user filled registration form and click the submit button
And the submitted data is valid
Then the application should show the Dashboard page

Given the user has connectivity
When the user filled registration form and click the submit button
And the submitted data is invalid
Then the application should show an error on the wrong field

Given the user doesn't have connectivity
When the user filled registration form and click the submit button
Then the application should show an error message
```
#### Login
```
As a registered user
I want to login with my registered email
So I can access the FoodMarket features
```
##### Login Scenarios
```
Given the user has connectivity
When the user filled login fields with valid credentials and click the login button
Then the application should show the Dashboard page

Given the user has connectivity
When the user filled login fields with wrong credentials and click the login button
Then the application should show an error message
```
### User Story #2 
#### Explore Food
```
As an authenticated user
I want to explore the available food on the application
So I can pick a food I want and order it
```
##### Explore Scenarios
```
Given user has connectivity
When the user is on the Dashboard page
Then the application should show food list based on several type

Given user doesn't have connectivity
When the user is on the Dashboard page
Then the application should show error view on the dashboard page
```
