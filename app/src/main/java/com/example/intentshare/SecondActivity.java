package com.example.intentshare;

import android.content.Intent;
import android.service.autofill.UserData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.intentshare.model.Users;

public class SecondActivity extends AppCompatActivity {
    EditText nametxt, passtxt, npass;
    String name, pass, newpass;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        nametxt = findViewById(R.id.oldUserN);
        passtxt = findViewById(R.id.OldUserP);
        npass = findViewById(R.id.newPass);
        i = getIntent();
        if (i != null && i.hasExtra("userData")) {
            Users users = i.getExtras().getParcelable("userData");
            name = users.getUserName();
            pass = users.getPassword();

        }


    }


    public void confirm(View view) {
        String n=nametxt.getText().toString();
        String p=passtxt.getText().toString();
        newpass = npass.getText().toString();
        if (n!="" && p != "") {
            if ((n.equalsIgnoreCase(name)) && (p.equalsIgnoreCase(pass))) {
                i.putExtra("npas", newpass);
                setResult(RESULT_OK,i);
                finish();
            }
            else {
                setResult(RESULT_CANCELED);
                finish();
            }
        }
    }
}