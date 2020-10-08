# Zoom API Integration using Springboot and Webclient

Primary motivation to have this project available to everyone is the unavailability of any sample project which shows how to integrate Zoom APIs. This project provides a sample application which demonstrates how to integrate Zoom APIs using Spring boot and WebClient.

# Spring libraries used
Spring Boot: 2.3.4.RELEASE
Webflux and Project Reactor

# Gradle changes

Following are the gradle changes to add Webclient.

```bash
compile 'org.springframework.boot:spring-boot-starter-webflux'
compile 'org.projectreactor:reactor-spring:1.0.1.RELEASE'
```

# For Testing
1. Create an oAuth app in Zoom and get the client_id, client_secret. In this oAuth app user will have to provide the redirect_url to which Zoom will redirect after a successful oAuth token generation.
2. Modify the client_id, client_secret, redirect_url obtained from the oauth app in the application-dev.properties 
3. Generate an oAuth code by using the following URL and pasting it in the chrome browser
https://zoom.us/oauth/authorize?response_type=code&client_id=<client_id>&redirect_uri=<redirect_url_in_oauth_app>
    1. For testing purpose, set the redirect_url, whitelist_url as https://zoom.us
    2. Response: https://zoom.us/?code=JLiHmpvrnk_Bb5fxRpURdeR9f_Xi2HPIA
    3. The code along with ?code= is the oAuth code
4. Use the oAuth code for testing


