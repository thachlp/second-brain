Apache-MINA-SSHD provides an easy way to create an ssh server and client. 
### Create a simple ssh server
- Build ssh-server: `./gradlew :apache-mina:build`
- Run ssh-server: `./gradlew :apache-mina:run`
- Check listen port (2224): ` lsof -i -n -P | grep LISTEN`
### Test ssh server
- Run test: `./gradlew test`

### Similar libs:
- [jsch](http://www.jcraft.com/jsch/)
- [sshj](https://github.com/hierynomus/sshj)
