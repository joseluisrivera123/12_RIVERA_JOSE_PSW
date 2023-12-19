package com.api.rest.SpringBootKeycloak.service.impl;

import com.api.rest.SpringBootKeycloak.dto.ResponseMessage;
import com.api.rest.SpringBootKeycloak.dto.UserDTO;
import com.api.rest.SpringBootKeycloak.service.IKeycloakService;
import com.api.rest.SpringBootKeycloak.util.KeycloakProvider;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class KeycloakServiceImpl implements IKeycloakService {

    /**
     * Metodo para listar todos los usuarios de Keycloak
     * @return List<UserRepresentation>
     */
    public List<UserRepresentation> findAllUsers(){
        return KeycloakProvider.getRealmResource()
                .users()
                .list();
    }


    /**
     * Metodo para buscar un usuario por su username
     * @return List<UserRepresentation>
     */
    public List<UserRepresentation> searchUserByUsername(String username) {
        return KeycloakProvider.getRealmResource()
                .users()
                .searchByUsername(username, true);
    }


    /**
     * Metodo para crear un usuario en keycloak
     * @return String
     */
    public ResponseMessage createUser(@NonNull UserDTO userDTO) {

        int status = 0;
        UsersResource usersResource = KeycloakProvider.getUserResource();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(userDTO.getFirstName());
        userRepresentation.setLastName(userDTO.getLastName());
        userRepresentation.setEmail(userDTO.getEmail());
        userRepresentation.setUsername(userDTO.getUsername());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);

        Response response = usersResource.create(userRepresentation);

        status = response.getStatus();

        if (status == 201) {
            String path = response.getLocation().getPath();
            String userId = path.substring(path.lastIndexOf("/") + 1);
            CredentialRepresentation passwordCredential = new CredentialRepresentation();
            passwordCredential.setTemporary(false);
            passwordCredential.setType(CredentialRepresentation.PASSWORD);
            passwordCredential.setValue(userDTO.getPassword());
            usersResource.get(userId).resetPassword(passwordCredential);

            RealmResource realmResource = KeycloakProvider.getRealmResource();
            RoleRepresentation roleRepresentation = realmResource.roles().get("user").toRepresentation();
            realmResource.users().get(userId).roles().realmLevel().add(Arrays.asList(roleRepresentation));
            return new ResponseMessage("Usuario creado con éxito");
        } else if (status == 409) {
            return new ResponseMessage("Ese usuario ya existe");
        } else {
            return new ResponseMessage("Error creando el usuario");
        }
    }


    /**
     * Metodo para borrar un usuario en keycloak
     * @return void
     */
    public void deleteUser(String userId){
        KeycloakProvider.getUserResource()
                .get(userId)
                .remove();
    }


    /**
     * Metodo para actualizar un usuario en keycloak
     * @return void
     */
    public void updateUser(String userId, @NonNull UserDTO userDTO){

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(OAuth2Constants.PASSWORD);
        credentialRepresentation.setValue(userDTO.getPassword());

        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setEnabled(true);
        user.setEmailVerified(true);
        user.setCredentials(Collections.singletonList(credentialRepresentation));

        UserResource usersResource = KeycloakProvider.getUserResource().get(userId);
        usersResource.update(user);
    }
}
