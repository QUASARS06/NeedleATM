package com.health.needleatm.utils;

import android.os.AsyncTask;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

public class EmailSenderAsync extends AsyncTask<String, Void, Boolean> {

    private HtmlEmail email;

    @Override
    protected Boolean doInBackground(String... params) {
        String textMsg;
        try {
            String userEmail = params[0];
            String message = params[1];
            String sender_email = params[2];
            String sender_pass = params[3];

            email = new HtmlEmail();

            email.setAuthenticator(new DefaultAuthenticator(sender_email, sender_pass));

            email.setSmtpPort(587);

            email.setHostName("smtp.gmail.com");

            email.setDebug(true);


            email.addTo(userEmail, "");

            email.setFrom("spit420420@gmail.com", "Needle ATM");

            email.setSubject("Contact Information of Patient");


            email.getMailSession().getProperties().put("mail.smtps.auth", "true");

            email.getMailSession().getProperties().put("mail.debug", "true");

            email.getMailSession().getProperties().put("mail.smtps.port", "587");

            email.getMailSession().getProperties().put("mail.smtps.socketFactory.port", "587");

            email.getMailSession().getProperties().put("mail.smtps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            email.getMailSession().getProperties().put("mail.smtps.socketFactory.fallback", "false");

            email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");

            email.setTextMsg(message);

            email.send();


            return true;

        } catch (Exception e) {

            return false;
        }

    }
}
