package com.datemate.common.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    private static final String FIREBASE_CONFIG_PATH = "firebase/datemate-1a343-firebase-adminsdk-6hmou-23fad80288.json";

    @Bean
    public void FirebaseInit() throws IOException {
        ClassPathResource serviceAccount = new ClassPathResource(FIREBASE_CONFIG_PATH);

        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream())).build();

        FirebaseApp.initializeApp(options);

    }
}
