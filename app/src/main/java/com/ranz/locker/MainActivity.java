package com.ranz.locker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etPin = findViewById(R.id.etPin);
        Button btnActivate = findViewById(R.id.btnActivate);

        btnActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin = etPin.getText().toString();
                if (pin.length() == 4) {
                    getSharedPreferences("lock", MODE_PRIVATE).edit().putString("pin", pin).apply();
                    startService(new Intent(MainActivity.this, LockService.class));
                    Intent i = new Intent(MainActivity.this, LockActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "PIN 4 digit!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
