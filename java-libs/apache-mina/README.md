### Create a simple ssh server with gradle
- Build ssh-server: `./gradlew :apache-mina:build`
- Run ssh-server: `./gradlew :apache-mina:run`
- Check listen port (2224): ` lsof -i -n -P | grep LISTEN`
### Test gradle server with test
- Run test: `./gradlew test`
