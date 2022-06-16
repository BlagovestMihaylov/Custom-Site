package com.example.site.mail;

import net.sargue.mailgun.Configuration;
import net.sargue.mailgun.Mail;

public class MailService {

    private static final String DOMAIN_NAME = "sandbox7c2441249c904f9c9bea8afee8bf3a0b.mailgun.org";
    private static final String API_KEY = "pubkey-121f39fe553032e4e0694a8b011f6274";
    private static final String API_URL = "https://api.mailgun.net/v3/";

    static Configuration configuration = new Configuration()
            .domain(DOMAIN_NAME)
            .apiUrl(API_URL)
            .apiKey(API_KEY)
            .from("Blago test", "blagovestmihaylov@abv.bg");

    public static void main(String[] args) {
        Mail.using(configuration)
                .to("stjqqzjeqdulscsnkk@kvhrs.com")
                .subject("This is the subject")
                .text("Hello world!")
                .build()
                .send();
    }
}