package org.push.fcm.service.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Service
public class FCMintializer {

    private static final String LOG_TITLE = "[FCM-INITIALIZE]";

    @Value("${app.firebase.config-path}")
    private String FIREBASE_CONFIG_PATH;


    @PostConstruct
    public void initialize() {
        try {
            ClassPathResource fileInputStream = new ClassPathResource(FIREBASE_CONFIG_PATH);

            FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(fileInputStream.getInputStream()))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {

                FirebaseApp.initializeApp(firebaseOptions);
                log.info("Firebase application has been initialized");
            }

        } catch (IOException e) {
            log.error(LOG_TITLE + "{}", e.getMessage());
        }
    }
}
