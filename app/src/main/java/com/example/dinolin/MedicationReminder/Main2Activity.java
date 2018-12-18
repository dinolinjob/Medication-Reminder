package com.example.dinolin.MedicationReminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    Button buttonAdd;
    ListView listView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;

    int SNO;
    String name,patient_id,diseases,medication;

    Databasehelper mydb;
    EditText Name, Patient_id, disease, Medication;
    Button save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Patient Details");
        setContentView(R.layout.activity_main2);
        mydb = new Databasehelper(this);

        Name = (EditText) findViewById(R.id.Name);
        Patient_id = (EditText) findViewById(R.id.Patient_id);
        disease = (EditText) findViewById(R.id.disease);
        Medication = (EditText) findViewById(R.id.Medication);
        save_button = (Button) findViewById(R.id.save_button);
        AddData();

        buttonAdd = (Button) findViewById(R.id.button2);
        listView = (ListView) findViewById(R.id.listView);
        listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        buttonAdd.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                listItems.add(Medication.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                Toast.makeText(Main2Activity.this, "Please do a Long Press", Toast.LENGTH_LONG)
                        .show();
            }
        });
        registerForContextMenu(listView);








    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_list:
                listItems.remove(info.position);
                adapter.notifyDataSetChanged();
                return super.onContextItemSelected(item);
            case R.id.set_alarm:
                startActivity(new Intent(this, AlarmActivity.class));


        }
        return super.onContextItemSelected(item);
    }


    public void AddData() {
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = mydb.insertdata(Name.getText().toString(), Patient_id.getText().toString(), disease.getText().toString(), Medication.getText().toString());
                if (isInserted = true) {
                    Toast.makeText(Main2Activity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(Main2Activity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
            }
        });
    }
}
