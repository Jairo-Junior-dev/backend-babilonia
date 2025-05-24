package com.finance.babilonia.service;

import com.finance.babilonia.controller.request.UserRequest;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final Keycloak keycloak;

    public void addUser(UserRequest userRequest) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userRequest.getUsername());
        userRepresentation.setEmail(userRequest.getEmail());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        Response response = keycloak.realm("master").users().create(userRepresentation);
        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userRequest.getPassword());

        keycloak.realm("master")
                .users()
                .get(userId)
                .resetPassword(passwordCred);
    }
}
