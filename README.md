```
   _____                       _
  / ____|                     | |          /\
 | (___   __ _ _ __ ___  _ __ | | ___     /  \   _ __  _ __
  \___ \ / _` | '_ ` _ \| '_ \| |/ _ \   / /\ \ | '_ \| '_ \
  ____) | (_| | | | | | | |_) | |  __/  / ____ \| |_) | |_) |
 |_____/ \__,_|_| |_| |_| .__/|_|\___| /_/    \_\ .__/| .__/
                        | |                     | |   | |
                        |_|                     |_|   |_|
```

# Sample App

How to start the helPet application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/helPet.app-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`



How to start frontend
---

Just run `npm start` inside frontend application directory which is `path-to-your-application/src/main/resources/assets/helPet`