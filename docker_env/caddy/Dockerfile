FROM alpine:3.7

# install deps
RUN apk update && \
    apk add curl && \
    rm -rf /var/lib/apt/lists/* && \
    apk add tar
    
# make some temporary files
RUN mkdir -p /srv/wordpress 

# download from server and rename it
# RUN wget "https://github.com/caddyserver/caddy/releases/download/v1.0.4/caddy_v1.0.4_linux_amd64.tar.gz" -O caddy.tar.gz


ADD caddy.tar.gz caddy.tar.gz

# untar it and move it to our designated folder
RUN gunzip caddy.tar.gz && tar -xvf caddy.tar -C /tmp/ && mv /tmp/caddy /usr/local/bin/caddy && \
    chmod +x /usr/local/bin/caddy

#rm caddy dl
#RUN rm caddy.tar.gz

EXPOSE 80 443

WORKDIR /srv


CMD caddy
