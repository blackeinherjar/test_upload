package com.bignerdranch.android.reminder;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReminderList extends AppCompatActivity {

    DbHelper dbhelper;
    ArrayAdapter<String> mAdapter;
    ListView first_duty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);


        Bundle data_get = getIntent().getExtras();
        String data_name = data_get.getString("name");
        String data_date = data_get.getString("date");

        TextView name_txt = (TextView) findViewById(R.id.display_name);
        TextView date_txt = (TextView) findViewById(R.id.display_date);

        name_txt.setText(data_name);
        date_txt.setText(data_date);



        dbhelper = new DbHelper(this);

        first_duty = (ListView)findViewById(R.id.first_duty);

        loadDutyList();
    }

    private void loadDutyList() {
        ArrayList<String> dutyList = dbhelper.getDutyList();
        if(mAdapter == null)
        {
            mAdapter = new ArrayAdapter<String>(this,R.layout.activity_duty_row,R.id.duty_title,dutyList);
            first_duty.setAdapter(mAdapter);
        }
        else
        {
            mAdapter.clear();
            mAdapter.addAll(dutyList);
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_bar,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.add_new_duty:
                final EditText dutyEditText = new EditText(this);
                AlertDialog popupbox = new AlertDialog.Builder(this)
                        .setTitle("Add New Yoga Routine")
                        .setMessage("Please enter your next yoga routine")
                        .setView(dutyEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface popupbox, int i) {
                                String duty = String.valueOf(dutyEditText.getText());
                                dbhelper.add_duty(duty);
                                loadDutyList();

                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                popupbox.show();;
                return true;
        }

        return super.onOptionsItemSelected(item);
    }




    public void deleteDuty(View view)
    {
        View parent = (View)view.getParent();
        TextView dutyTextView = (TextView)parent.findViewById(R.id.duty_title);
        String duty = String.valueOf(dutyTextView.getText());
        dbhelper.delete_duty(duty);
        loadDutyList();
    }

}




