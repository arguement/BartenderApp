package com.williams.jordan.bartenderapp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    public void bartenderSignUp(View view){
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        EditText confirmPass = findViewById(R.id.confirmPassword);

        String Textusername = username.getText().toString();
        String Textpassword = password.getText().toString();
        String TextconfirmPass = confirmPass.getText().toString();

        if (isCorrectPassword(Textpassword) && isCorrectUsername(Textusername) && isCorrectPassword(TextconfirmPass)){
            if(Textpassword.equals(TextconfirmPass)){
                Toast.makeText(Login.this,"proceed",Toast.LENGTH_LONG).show();

                if (isConnected()) {

                    SendBartenderCred sendBartenderCred = new SendBartenderCred(this, getApplicationContext());
                    sendBartenderCred.execute("login", Textusername, TextconfirmPass);
                    //Toast.makeText(Login.this,"run",Toast.LENGTH_LONG).show();

                }
                else{
                    createPopup("you are not connected to the internet");
                }
            }
            else{
                createPopup("the two passwords bid not match");
            }
        }
        else{
            createPopup("username or password incorrect");
        }

    }

    //test if the username is correct. that it has atleast 6 letters and contains charcaters of the alphabet
    public boolean isCorrectUsername(String string){
        String expression = "^(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        CharSequence inputStr = string;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()){
            return true;
        }
        return false;
    }


    //test if the password is correct. that it has atleast 8 letters and contains charcaters of the alphabet
    public boolean isCorrectPassword(String string){
        String expression = "^(?=.*[0-9])(?=.*[a-z]).{8,}$";
        CharSequence inputStr = string;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()){
            return true;
        }
        return false;
    }

    //creates pop messages
    public void createPopup(String message){
        AlertDialog incorrectPassUser = new AlertDialog.Builder(this).create();
        incorrectPassUser.setMessage(message);
        incorrectPassUser.show();
    }

    // checks for net connectivity
    public boolean isConnected()
    {
        String command = "ping -c 1 google.com";
        try {
            return (Runtime.getRuntime().exec (command).waitFor() == 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
