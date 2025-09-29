package com.example.backend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @PostConstruct
    public void init() {
        try {
            InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json");
            if (serviceAccount == null) {
                throw new RuntimeException("serviceAccountKey.json not found in classpath");
            }
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://task-c22fb.firebaseio.com")
                .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }

    @Bean
    public FirebaseApp firebaseApp() {
        return FirebaseApp.getInstance();
    }
}
