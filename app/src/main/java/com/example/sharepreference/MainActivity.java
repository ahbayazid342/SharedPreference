package com.example.sharepreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btnApply, btnSave;
    Switch switchBtn;
    EditText etText;

    public static final String PREF = "shared";
    public static final String TEXT = "text";
    public static final String SWITCH = "switch";

    private String text;
    private Boolean switchOnOFF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tvText);
        btnApply = findViewById(R.id.applyBtn);
        btnSave = findViewById(R.id.saveBtn);
        switchBtn = findViewById(R.id.switchBtn);
        etText = findViewById(R.id.etText);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(etText.getText().toString().trim());
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchBtn.isChecked()){
                    saveData();
                }
            }
        });

        loadData();
        updateView();
    }

    private void saveData() {
        SharedPreferences preferences = getSharedPreferences(PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TEXT, tv.getText().toString());
        editor.putBoolean(SWITCH, switchBtn.isChecked());

        editor.apply();
        Toast.makeText(MainActivity.this, "data save", Toast.LENGTH_LONG).show();
    }

    private void loadData(){
        SharedPreferences preferences = getSharedPreferences(PREF,  MODE_PRIVATE);
        text = preferences.getString(TEXT, "No Data");
        switchOnOFF = preferences.getBoolean(SWITCH, false);
    }
    private void updateView(){
        tv.setText(text);
        switchBtn.setChecked(switchOnOFF);
    }
}