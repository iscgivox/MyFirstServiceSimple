package net.ivanvega.myfirstservicesimple;

import android.content.ContentProvider;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar pgr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pgr = (ProgressBar) findViewById(R.id.pgr);
        pgr.setProgress(50);
    }

    public void btnIniciar_click(View v) {
        Intent iS = new Intent(this, MyServiceSimple.class);
        startService(iS);

    }

    public void btnParar_click(View v) {
        stopService(
                new Intent(this, MyServiceSimple.class)
        );
    }

    public void btnIS_click(View v) {
        startService(
                new Intent(this, MyIntentService.class)
        );
    }

    public void btnAT_click(View v) {
        mAsyncTask.execute(0, 1, 2, 3, 4, 5);
    }

    AsyncTask<Integer, Integer, String> mAsyncTask = new AsyncTask<Integer, Integer, String>() {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgr.setProgress(0);
            Toast.makeText(MainActivity.this, "SE INICIO TAREA ASINCRONA", Toast.LENGTH_SHORT)
                    .show();


        }

        @Override
        protected String doInBackground(Integer... integers) {

            for (int p = integers[0]; p < 100; p++) {
                publishProgress(p);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "TAREA ASYNCRONA FINAIZADA";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pgr.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT)
                    .show();
        }

    };

    public void btnCPC_click(View v) {

        Cursor c =  getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME }
                ,
                null,null,null
        );

        if(c.moveToFirst()){
            do{
                int id = c.getInt(0);
                String name = c.getString(1);

                Log.d("CONTACTOS", "ID: " + String.valueOf(id) + " NAME: " + name );
            }while(c.moveToNext());
        }
    }

}