package com.deltasystem.quietness.toolbar_items;

import static com.deltasystem.quietness.R.layout.activity_fallos;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.deltasystem.quietness.R;
import com.deltasystem.quietness.activity_menu.Menu;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class BugReport extends AppCompatActivity{

    private Button btn_profile;
    private ImageButton btnMusic,btnSleep,btnCalendar,btnStory,btn_tips;
    private Bundle ble=null;


    EditText _txtEmail, _txtAsunto, _txtDetalles;
    Button btnSend, back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_fallos);
        _txtAsunto = findViewById(R.id.boton_asunto);
        _txtDetalles = findViewById(R.id.boton_detalles);
        btnSend = findViewById(R.id.LoadData);
        back=findViewById(R.id.back_ntb);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver_menu();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = "quietnessapp@gmail.com";
                final String password = "proyectoingenieria";
                String messageToSend = _txtDetalles.getText().toString();
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username,password);
                            }
                        });
                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("quitenessapp@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
                    message.setSubject("Error Quiteness: " + _txtAsunto.getText().toString());
                    message.setText(messageToSend);
                    Transport.send(message);
                    Toast.makeText(getApplicationContext(), "email enviado correctamente", Toast.LENGTH_LONG).show();
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
    public void volver_menu(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }
}