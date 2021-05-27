package com.health.needleatm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.health.needleatm.utils.EmailSenderAsync;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

public class AssistanceActivity extends AppCompatActivity {

    private static final String TAG = "AssistanceActivity";

    MaterialButton genOTP;
    TextView verifyOtpMssg;

    OtpView otpView;

    TextView otp_sent_mssg;

    TextInputLayout phNum;

    String number = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistance);

        phNum = findViewById(R.id.assis_phoneNum);

        genOTP = findViewById(R.id.assis_submitNum);
        verifyOtpMssg = findViewById(R.id.assis_verifyOtpMssg);

        otpView = findViewById(R.id.otp_view);
        otp_sent_mssg = findViewById(R.id.assis_otpsentmssg);

        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                verifyOtpMssg.setVisibility(View.VISIBLE);
                if (otp.equals("5247")) {
                    verifyOtpMssg.setText("OTP Verified Successfully !!");
                    verifyOtpMssg.setTextColor(getResources().getColor(R.color.green));

                    sendEmail();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(AssistanceActivity.this, ExitActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }, 1500);
                } else {
                    verifyOtpMssg.setText("Invalid OTP !!");
                    verifyOtpMssg.setTextColor(getResources().getColor(R.color.red));
                }
            }
        });

        genOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = phNum.getEditText().getText().toString().trim();

                if (number.length() != 10) {
                    phNum.setErrorEnabled(true);
                    phNum.setError("Enter 10 digit number");
                    return;
                }
                phNum.setErrorEnabled(false);
                phNum.setError(null);

                String str = "Please enter OTP sent to +91 " + number.substring(0, 2) + "XXXXXXXX";
                otp_sent_mssg.setText(str);

                otpView.setVisibility(View.VISIBLE);
                otp_sent_mssg.setVisibility(View.VISIBLE);
            }
        });

    }

    // https://stackoverflow.com/questions/25649631/sending-mail-by-android-app-using-javamail-api-without-user-interaction
    protected void sendEmail() {
        String sender_email = getString(R.string.email_id);
        String sender_pass = getString(R.string.pass);

        String email_being_sent_to = "chiragajain291@gmail.com";  //mbbs160147@kem.edu      //chiragajain291@gmail.com
        String email_body = "The following email is auto-generated, providing contact information of our Patient.\nContact Information: +91 "+number;

        new EmailSenderAsync().execute(email_being_sent_to, email_body, sender_email, sender_pass);

        Toast.makeText(AssistanceActivity.this, "Mail sent Successfully", Toast.LENGTH_SHORT).show();
    }
}

