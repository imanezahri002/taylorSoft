package org.taylorsoft.taylorsoft;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaylorSoftApplication {

    public static void main(String[] args) {
    Dotenv dotenv = Dotenv.configure()
            .ignoreIfMalformed()  // ignore si le .env est mal formé
            .ignoreIfMissing()    // ignore si le .env est manquant
            .load();

        System.setProperty("AWS_ACCESS_KEY_ID", dotenv.get("AWS_ACCESS_KEY_ID"));
        System.setProperty("AWS_SECRET_KEY", dotenv.get("AWS_SECRET_KEY"));
        System.setProperty("AWS_REGION", dotenv.get("AWS_REGION"));
        System.setProperty("AWS_S3_BUCKET", dotenv.get("AWS_S3_BUCKET"));

        SpringApplication.run(TaylorSoftApplication.class, args);
}
}
