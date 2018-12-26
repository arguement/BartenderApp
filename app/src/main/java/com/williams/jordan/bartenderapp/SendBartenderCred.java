package com.williams.jordan.bartenderapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


//this class sends username and password to a php file on a server
public class SendBartenderCred extends AsyncTask<String,Void,String> {
    private Context mContext;
    private Context ctx;
    Boolean done = false;
    private AlertDialog mAlertDialog;
    private String username;

    public  SendBartenderCred(Context context,Context ctx){
        this.mContext= context;
        this.ctx = context.getApplicationContext();
    }
    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];
        this.username = voids[1];
        String databaseURL = /*"http://10.0.2.2/practise/1/sql.php"*/"http://192.168.1.2/practise/1/sql.php";
        if (type.equals("login")){
            try {
                String username = voids[1];
                String password = voids[2];
                URL url = new URL(databaseURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line ="";
                while((line = bufferedReader.readLine())!=null){
                    result = result + line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        mAlertDialog = new AlertDialog.Builder(mContext).create();
        mAlertDialog.setTitle("login status");
    }

    @Override
    protected void onPostExecute(String aVoid) {// after task is finished it sends a request showing successful login or not


       mAlertDialog.setMessage(aVoid);
        mAlertDialog.show();



       if(aVoid.equals("login successful")){
            Toast.makeText(ctx,"something went wrong",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ctx, MainActionActivity.class);
           intent.putExtra("username", this.username);
           ctx.startActivity(intent);

        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
