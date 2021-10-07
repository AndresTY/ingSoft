package com.deltasystem.quietness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Calendar extends AppCompatActivity {

    private EditText title,location,desc,startDate,endDate;
    private Button saveEvent;
    private Bundle ble = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initializationAll();
        ble = this.getIntent().getExtras();
        saveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty()
                        && !desc.getText().toString().isEmpty() && !startDate.getText().toString().isEmpty() && !endDate.getText().toString().isEmpty()){
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE,title.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION,location.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION,desc.getText().toString());
                    intent.putExtra(CalendarContract.Events.DTSTART,startDate.getText().toString());
                    intent.putExtra(CalendarContract.Events.DTEND,endDate.getText().toString());
                    //intent.putExtra(CalendarContract.Events.ALL_DAY,"true");
                    intent.putExtra(Intent.EXTRA_EMAIL,ble.getString("user").toString());
                    startActivity(intent);
                    /*
                    if (intent.resolveActivity(getPackageManager())!= null){
                        startActivity(intent);
                    }else{
                        Toast.makeText(Calendar.this,"There is no app that can support this action", Toast.LENGTH_SHORT).show();
                    }*/
                }else{
                    Toast.makeText(Calendar.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializationAll(){
        title = findViewById(R.id.TitleTxt);
        location = findViewById(R.id.LocationTxt);
        desc = findViewById(R.id.descriptionTxt);
        startDate = findViewById(R.id.StartDateTxt);
        endDate = findViewById(R.id.EndDateTxt);
        saveEvent = findViewById(R.id.save_Btn);
    }

}