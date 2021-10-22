package com.deltasystem.quietness.sing_in_up;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.deltasystem.quietness.R;
import com.deltasystem.quietness.activity_menu.Menu;
import com.deltasystem.quietness.toolbar_items.settings;
import com.deltasystem.quietness.update.sendInfo;

public class Encuesta extends AppCompatActivity {

    private RadioButton q11,q12,q13,q14;
    private RadioButton q21,q22,q23,q24;
    private RadioButton q31,q32,q33,q34,q35;
    private RadioButton q41,q42,q43,q44;
    private RadioGroup q1,q2,q3,q4;
    private Button _load=null;
    private Bundle bdle=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);

        initialization_radio_button();
        initialization_radio_group();

        _load = (Button) findViewById(R.id.LoadData);
        _load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLoad(view);
            }
        });
    }

    //extracts survey data
    public String Load(){
        int Q1aID = q1.getCheckedRadioButtonId();
        int Q2aID = q2.getCheckedRadioButtonId();
        int Q3aID = q3.getCheckedRadioButtonId();
        int Q4aID = q4.getCheckedRadioButtonId();

        RadioButton r1 = (RadioButton) q1.getChildAt(q1.indexOfChild(q1.findViewById(Q1aID)));
        RadioButton r2 = (RadioButton) q2.getChildAt(q2.indexOfChild(q2.findViewById(Q2aID)));
        RadioButton r3 = (RadioButton) q3.getChildAt(q3.indexOfChild(q3.findViewById(Q3aID)));
        RadioButton r4 = (RadioButton) q4.getChildAt(q4.indexOfChild(q4.findViewById(Q4aID)));

        String a1 = r1.getText().toString();
        String a2 = r2.getText().toString();
        String a3 = r3.getText().toString();
        String a4 = r4.getText().toString();

        return String.format("%s;%s;%s;%s;",a1,a2,a3,a4);
    }

    //upload quiz to database
    private void Load_quiz(String a){
        bdle = this.getIntent().getExtras();
        String user = bdle.getString("user");
        String passwd = bdle.getString("passwd");
        sendInfo SdIn = new sendInfo();
        SdIn.add_quiz(user,passwd,a);
    }

    //button action
    public void onClickLoad(View view){
        String quiz =Load();
        Intent intent = null;
        bdle = this.getIntent().getExtras();
        //extracts user information
        String user = bdle.getString("user");
        String passwd = bdle.getString("passwd");
        int return_view = bdle.getInt("view");
        if(return_view==0) {
            intent = new Intent(Encuesta.this, Menu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Load_quiz(quiz);
        }else if(return_view==1){
            intent = new Intent(Encuesta.this, settings.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Load_quiz(quiz);
        }
        //pass the data to another view
        intent.putExtra("user",bdle.getString("user"));
        intent.putExtra("passwd",bdle.getString("passwd"));
        startActivity(intent);
    }

    private void initialization_radio_group(){
        q1 = (RadioGroup) findViewById(R.id.Q1RG);
        q2 = (RadioGroup) findViewById(R.id.Q2RG);
        q3 = (RadioGroup) findViewById(R.id.Q3RG);
        q4 = (RadioGroup) findViewById(R.id.Q4RG);
    }

    private void initialization_radio_button(){
        q11 = (RadioButton) findViewById(R.id.Q11);
        q12 = (RadioButton) findViewById(R.id.Q12);
        q13 = (RadioButton) findViewById(R.id.Q13);
        q14 = (RadioButton) findViewById(R.id.Q14);
        q21 = (RadioButton) findViewById(R.id.Q21);
        q22 = (RadioButton) findViewById(R.id.Q22);
        q23 = (RadioButton) findViewById(R.id.Q23);
        q24 = (RadioButton) findViewById(R.id.Q24);
        q31 = (RadioButton) findViewById(R.id.Q31);
        q32 = (RadioButton) findViewById(R.id.Q32);
        q33 = (RadioButton) findViewById(R.id.Q33);
        q34 = (RadioButton) findViewById(R.id.Q34);
        q35 = (RadioButton) findViewById(R.id.Q35);
        q41 = (RadioButton) findViewById(R.id.Q41);
        q42 = (RadioButton) findViewById(R.id.Q42);
        q43 = (RadioButton) findViewById(R.id.Q43);
        q44 = (RadioButton) findViewById(R.id.Q44);

    }
}