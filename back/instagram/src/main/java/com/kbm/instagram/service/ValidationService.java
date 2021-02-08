package com.kbm.instagram.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.kbm.instagram.dto.MemberDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class ValidationService {

    private static final HttpTransport transport = new NetHttpTransport();
    private static final JsonFactory jsonFactory = new GsonFactory();
    private static final String CLIENT_ID = "520178822813-fgb8q6be29qoin9l9hcrc182kpfqukvi.apps.googleusercontent.com";

    public MemberDto validationByIdToken(String idTokenString) throws GeneralSecurityException, IOException, IllegalArgumentException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(CLIENT_ID))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        MemberDto memberDto = null;

        // (Receive idTokenString by HTTPS POST)
        GoogleIdToken idToken = verifier.verify(idTokenString);

        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String picture = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            System.out.println(email + "," + name + "," + picture + "," + locale + "," + familyName + "," + givenName);

            // Use or store profile information
            memberDto = MemberDto.builder()
                    .email(email)
                    .name(name)
                    .picture(picture).build();
        } else {
            // 유효성 체크 실패
        }
        return memberDto;
    }

}
