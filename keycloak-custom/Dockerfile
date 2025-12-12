# keycloak-custom/Dockerfile
FROM quay.io/keycloak/keycloak:latest

# Copia il file di configurazione del realm nel percorso di importazione
COPY appCovo-realm-export.json /opt/keycloak/data/import/
