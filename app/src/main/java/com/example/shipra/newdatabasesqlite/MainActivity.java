package com.example.shipra.newdatabasesqlite;

import android.app.AlertDialog;
import android.database.Cursor;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shipra.newdatabasesqlite.DatabaseHelperd;

import static com.example.shipra.newdatabasesqlite.R.*;


public class MainActivity extends AppCompatActivity{
    DatabaseHelperd myDb;
    EditText editName, editSurname, editMarks, editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;

    Button btnviewUpdate;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);


        editName = (EditText) findViewById(id.editText_name);
        editSurname = (EditText) findViewById(id.editText_surname);
        editMarks = (EditText) findViewById(id.editText_Marks);
        editTextId = (EditText) findViewById(id.editText_id);

        btnAddData = (Button) findViewById(id.button_add);
        btnviewAll = (Button) findViewById(id.button_viewAll);
        btnDelete = (Button) findViewById(id.button_delete);
        btnviewUpdate = (Button) findViewById(id.button_update);


        DeleteData();
        AddData();
        viewAll();
        UpdateData();


    }


    public void DeleteData() {

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(editTextId.getText().toString());

                if (deletedRows > 0)
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
            }

        });
    }


    public void UpdateData() {
        btnviewUpdate.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                                                         editName.getText().toString(),
                                                         editSurname.getText().toString(), editMarks.getText().toString());
                                                 if (isUpdate == true)
                                                     Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_LONG).show();
                                                 else
                                                     Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                                             }
                                         }
        );
    }


    public void AddData() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             boolean isInserted = myDb.insertData(editTextId.getText().toString(),

                                                     editSurname.getText().toString(),
                                                     editMarks.getText().toString());
                                             if (isInserted == true)
                                                 Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                             else
                                                 Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                                         }
                                     }
        );
    }


    public void viewAll() {

        btnviewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    // show message
                    showMessage("Error", "Nothing found");
                    return;

                }


                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id :" + res.getString(0) + "\n");
                    buffer.append("Name :" + res.getString(1) + "\n");
                    buffer.append("Surname :" + res.getString(2) + "\n");
                    buffer.append("Marks :" + res.getString(3) + "\n\n");


                    // Show all data
                    showMessage("Data", buffer.toString());
                }
            }


        });


    }


    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    protected void onClick(View v) {
        switch (v.getId()) {
            case id.button_add:

                boolean isInserted = myDb.insertData(editTextId.getText().toString(),
                        editSurname.getText().toString(),
                        editMarks.getText().toString());
                if (isInserted == true)
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();


        }

    }
}
