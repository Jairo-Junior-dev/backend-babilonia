package com.finance.babilonia.service;

import com.finance.babilonia.config.handle.exceptions.LoginException;
import com.finance.babilonia.controller.request.UserRequest;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final Keycloak keycloak;
    private final String realm;

    public UserService(Keycloak keycloak, @Value("${keycloak.realm}") String realm) {
        this.keycloak = keycloak;
        this.realm = realm;
    }

    public void addUser(UserRequest userRequest) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userRequest.getUsername());
        userRepresentation.setEmail(userRequest.getEmail());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);

        Response response = keycloak.realm(realm).users().create(userRepresentation);

        if (String.valueOf(response.getStatus()).charAt(0) == '4' || String.valueOf(response.getStatus()).charAt(0) == '5') {
            throw new LoginException(response.getStatus(),"Erro ao criar a conta");
        }
        //400
        //500
        String userId = response.getLocation().
                getPath().
                replaceAll(".*/([^/]+)$", "$1");

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userRequest.getPassword());

        keycloak.realm(realm)
                .users()
                .get(userId)
                .resetPassword(passwordCred);
    }

    public void changePassword(String email) {
        List<UserRepresentation> userRepresentations = keycloak.realm(realm).users().searchByEmail(email, true);
        keycloak.realm(realm).users().get(userRepresentations.getFirst().getId()).executeActionsEmail(Collections.singletonList("UPDATE_PASSWORD"));
    }
}
