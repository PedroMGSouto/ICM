package com.example.ca1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Logger;

public class MainActivity2 extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        intent = getIntent();
        TextView me = (TextView) findViewById(R.id.memEdit);
        me.append(" "+intent.getStringExtra("memo"));
    }


    public void submit(View v){
        EditText et = (EditText) findViewById(R.id.nome);
        EditText et2 = (EditText) findViewById(R.id.tel);

        if(et.getText().toString().length() > 5 || et2.getText().toString().length() > 13){
            Toast toast = Toast.makeText(getApplicationContext(),"Name: max 5 chars\nPhone: (optional code) 9 digits",Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        intent.putExtra("name", et.getText().toString());
        intent.putExtra("phone", et2.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED, intent);
        finish();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}