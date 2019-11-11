package com.example.intentshare;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.service.autofill.UserData;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

import com.example.intentshare.model.Users;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {
    EditText edittxtUsernm,edittxtpass;
    String UsrName="a";
    String UserPass="a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittxtUsernm=findViewById(R.id.userNameTxt);
        edittxtpass=findViewById(R.id.passwordTxt);


    }


    public void go(View view) {
       // Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        String getuser=edittxtUsernm.getText().toString();
        String getpass=edittxtpass.getText().toString();
        //Toast.makeText(this, getpass, Toast.LENGTH_SHORT).show();
        if(getuser!="" && getpass!=""){
            if ((getuser.equalsIgnoreCase(UsrName)) && (getpass.equalsIgnoreCase(UserPass)))
                lunchActivityToDo();
            else
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
        }

    }
    private  void lunchActivityToDo()
    {
        Intent intent =new Intent(MainActivity.this,ToDoActivity.class);
        startActivity(intent);
    }

    public void editData(View view) {
        Intent intent =new Intent(MainActivity.this,SecondActivity.class);
        Users userData = new Users(UsrName,UserPass);
        intent.putExtra("userData",userData);
        startActivityForResult(intent,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK)
        {   
            if(data!=null&&data.hasExtra("npas")) {
                UserPass = data.getExtras().getString("npas");
                Toast.makeText(this, "UPDATED", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode==0&&resultCode==RESULT_CANCELED)
        {
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        }
    }
}
