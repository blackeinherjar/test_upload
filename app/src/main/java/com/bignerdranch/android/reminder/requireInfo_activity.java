package com.bignerdranch.android.reminder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;


public class requireInfo_activity extends AppCompatActivity {

    private static final String TAG = "requireinfo_activity";
    private TextView reminder_date_display;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.require_information);





        reminder_date_display = (TextView)findViewById(R.id.reminder_date);
        reminder_date_display.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Calendar my_calender = Calendar.getInstance();
                int day = my_calender.get(Calendar.DAY_OF_MONTH);
                int month = my_calender.get(Calendar.MONTH);
                int year = my_calender.get(Calendar.YEAR);
                DatePickerDialog date_dialog = new DatePickerDialog(
                        requireInfo_activity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mOnDateSetListener,year,month,day);

                date_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                date_dialog.show();
            }
        });
        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG,"onDateSet:mm/dd/yyyy:" + month + "/" + day + "/" + year);
                String picked_date = month + "/" + day + "/" + year;
                reminder_date_display.setText(picked_date);
            }
        };
    }

      public void onClick_confirm(View view)
      {
          //boolean valid = true;
             TextView input_date = (TextView)findViewById(R.id.reminder_date);
             String date = input_date.getText().toString();
             EditText input_name = (EditText)findViewById(R.id.my_name);
             String name = input_name.getText().toString();
          if(date.trim().equals(""))
          {
              TextView error_txt = (TextView) findViewById(R.id.error_msg);
              error_txt.setText("please fill in the date and the name");
          }
          else if(name.trim().equals(""))
          {
              TextView error_txt = (TextView) findViewById(R.id.error_msg);
              error_txt.setText("please fill in the date and the name");
              //valid = false;
          }else
          {
              Intent intent = new Intent(this, ReminderList.class);
              intent.putExtra("name",name);
              intent.putExtra("date",date);
              startActivity(intent);
          }










    }



















}
