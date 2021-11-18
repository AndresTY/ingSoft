package com.deltasystem.quietness.toolbar_items;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.deltasystem.quietness.R;
import com.deltasystem.quietness.activity_menu.Menu;
import com.deltasystem.quietness.sing_in_up.Encuesta;
import com.deltasystem.quietness.sing_in_up.Login;
import com.deltasystem.quietness.sing_in_up.SignUp;
import com.deltasystem.quietness.sing_in_up.TermOfService;
import com.deltasystem.quietness.update.sendInfo;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class Logout extends AppCompatActivity {

    private SharedPreferences settings;
    private GoogleSignInClient mGoogleSignInClient;
    Bundle ble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        openInstructions();

    }

    public void openInstructions() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Logout.this);

        final AlertDialog dialog = builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                continuar();
                dialog.cancel();
            }
        }).setPositiveButton("SI", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                devolver();
                cargar_sleep_register();
                dialog.cancel();
            }
        }).create();

        TextView myMsg = new TextView(Logout.this);
        myMsg.setText("¿Desea cerrar sesión?");
        myMsg.setTextSize(15);
        myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
        dialog.setView(myMsg);

        dialog.setTitle("Confirmar cierre de sesión");

        dialog.setOnShowListener( new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                negativeButton.setTextColor(0xFFFF0000);
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setTextColor(0xFF0000FF);
            }
        });
        dialog.show();

    }

    private void devolver(){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            mGoogleSignInClient.signOut();

        }
        settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = settings.edit();
        ble = this.getIntent().getExtras();
        Intent intent = new Intent(Logout.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        edit.clear();
        edit.commit();
        startActivity(intent);
    }

    private void continuar(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(Logout.this, Menu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    public void cargar_sleep_register(){
        String data = informacion();
        System.out.println(">>>INFORMACION");
        sendInfo si = new sendInfo();
        si.add_sueno(ble.getString("user"), ble.getString("passwd"), data);
        System.out.println(">>>CARGAR");
        System.out.println(data);
        borrar_contenido();

    }

    private String informacion(){
        String archivos [] = fileList();
        String dataCompleta = "";

        if (archivoExiste(archivos, "horas_dormidas.txt")){
            try{

                InputStreamReader archivo = new InputStreamReader(openFileInput("horas_dormidas.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                while(linea != null){
                    dataCompleta += linea + "\n";
                    linea = br.readLine();
                }

                br.close();
                archivo.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return dataCompleta;
    }

    private boolean archivoExiste(String archivos [], String nombre_archivo){
        for(int i = 0; i < archivos.length; i++)
            if(archivos[i].equalsIgnoreCase(nombre_archivo))
                return true;
        return false;
    }

    public void borrar_contenido(){
        String archivos [] = fileList();

        if (archivoExiste(archivos, "horas_dormidas.txt")){
            try{

                OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("horas_dormidas.txt", Activity.MODE_PRIVATE));
                archivo.write("");
                archivo.flush();
                archivo.close();

            }catch (IOException e){

            }
        }
    }
}