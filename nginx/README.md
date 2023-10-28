### Introduction
#### Install
MacOS: `brew install nginx`

#### Manage Nginx

- Start Nginx: `brew services start nginx`
- Check Nginx status: `brew services list`
- Check Nginx version: `nginx -v`
- Check Nginx configuration: `nginx -t`
- Reload Nginx configuration: `brew services reload nginx`
- Stop Nginx: `brew services stop nginx`
- On MacOS Ventura 13.6, config file is located in `/opt/homebrew/etc/nginx/nginx.conf`

#### Serving static content
After starting Nginx, you can access the server at http://localhost:8080/. By default, on MacOS files are served from the `/opt/homebrew/var/www` directory.

We can change the directory from which files are served with the following configuration:
```text
location / {
    root   /data/www;
    index  index.html;
}
```
- When a client makes a request to http://localhost:8080/, Nginx will serve the index.html file located at `/data/www/index.html`.
- If we add another file to `/data/www`, the client can access file via http://localhost:8080/filename.html

We can also specify a different directory for serving images:
```text
location /images/ {
    root   /data/www;
    index  index.html;
}
```
- When a client makes a request to http://localhost:8080/images/image.jpg, Nginx will serve the image.jpg file located in the `/data/www/images` directory.

#### Reverse proxy
Is a server that sits front of backend servers and forwards client requests to those backend servers .

Nginx can be used as a reverse proxy.
```text
server {
    listen 8080;
    server_name example.com;

    location /app/ {
        proxy_pass http://localhost:8081;
    }

		location /admin/ {
        proxy_pass http://localhost:8082;
    }
}
```
- I have two services admin and app running on port 8081 and 8082. I can use Nginx as a proxy to serve two services.
