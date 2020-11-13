package pt.ua.nextweather.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import pt.ua.nextweather.R;

public class Activity_B extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        Intent intent = getIntent();

        String city = intent.getStringExtra("City");
        Fragment_B fragment = Fragment_B.newInstance(city);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, fragment)
                .addToBackStack(null)
                .commit();

    }
}