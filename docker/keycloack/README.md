# Command 
docker-compose -f compose.yml up

# Local setup

create admin account on new created container
`docker exec <CONTAINER> /opt/jboss/keycloak/bin/add-user-keycloak.sh -u <USERNAME> -p <PASSWORD>`

restart container
`docker restart <CONTAINER>`


documentation
https://hub.docker.com/r/jboss/keycloak/