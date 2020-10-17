package com.example.ca1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    //Memored Numbers
    String memo1;
    String memo2;
    String memo3;

    //Request_Code
    private static final int REQUEST_CODE = 1;

    //Buttons LongPress
    Button memory1;
    Button memory2;
    Button memory3;
    ImageButton del;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Delete LongClick to delete all text
        /*--------------------------------------------------------------------*/
        del = (ImageButton) findViewById(R.id.delete);
        del.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                TextView tv = findViewById(R.id.display);
                String displayNow = tv.getText().toString();
                if(!displayNow.isEmpty()) {
                    tv.setText("");
                }
                return true;
            }
        });
        /*--------------------------------------------------------------------*/

        //Sets LongClicks on memories to start new Activity
        /*--------------------------------------------------------------------*/

        memory1 = (Button) findViewById(R.id.mem1);
        memory1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity2.class);
                i.putExtra("memo","1");
                startActivityForResult(i,REQUEST_CODE);
                return true;
            }
        });

        memory2 = (Button) findViewById(R.id.mem2);
        memory2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity2.class);
                i.putExtra("memo","2");
                startActivityForResult(i,REQUEST_CODE);
                return true;
            }
        });

        memory3 = (Button) findViewById(R.id.mem3);
        memory3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity2.class);
                i.putExtra("memo","3");
                startActivityForResult(i,REQUEST_CODE);
                return true;
            }
        });
        /*--------------------------------------------------------------------*/
    }

    /* OnClick Dial Keys */
    /*--------------------------------------------------------------------*/
    public void dialKeys(View v){
        TextView tv = findViewById(R.id.display);
        if (tv.getText().toString().length() < 13) {
            Button b = (Button) v;
            String dialKey = b.getText().toString();
            tv.append(dialKey);
        }
    }
    /*--------------------------------------------------------------------*/

    /* OnClick Delete Key */
    /*--------------------------------------------------------------------*/
    public void delete(View v){
        TextView tv = findViewById(R.id.display);
        String displayNow = tv.getText().toString();
        if(!displayNow.isEmpty()) {
            tv.setText(displayNow.substring(0, displayNow.length() - 1));
        }
    }
    /*--------------------------------------------------------------------*/

    /* OnClick Dial */
    /*--------------------------------------------------------------------*/
    public void dial(View v){
        TextView tv = findViewById(R.id.display);
        String displayNow = tv.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);

        /*
            Use Intent.ACTION_CALL for automatic call
            !Needs permission!
            <uses-permission android:name="android.permission.CALL_PHONE" />
        */

        intent.setData(Uri.parse("tel:"+displayNow));
        startActivity(intent);
    }
    /*--------------------------------------------------------------------*/

    /* OnClick Memories */
    /*--------------------------------------------------------------------*/
    public void memory1(View v){
        TextView tv = findViewById(R.id.display);
        tv.setText(memo1);
    }

    public void memory2(View v){
        TextView tv = findViewById(R.id.display);
        tv.setText(memo2);
    }

    public void memory3(View v){
        TextView tv = findViewById(R.id.display);
        tv.setText(memo3);
    }

    /*--------------------------------------------------------------------*/

    /* Result of new Activity */
    /*--------------------------------------------------------------------*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE  && resultCode  == RESULT_OK) {
                String name = data.getStringExtra("name");
                String phone = data.getStringExtra("phone");
                String memo = data.getStringExtra("memo");


                switch(memo){
                    case("1"):
                        memo1=phone;

                        memory1 = (Button) findViewById(R.id.mem1);
                        memory1.setText(name);
                        break;
                    case("2"):
                        memo2=phone;

                        memory2 = (Button) findViewById(R.id.mem2);
                        memory2.setText(name);
                        break;
                    case("3"):
                        memo3=phone;

                        memory3 = (Button) findViewById(R.id.mem3);
                        memory3.setText(name);
                        break;

                }

            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }

}