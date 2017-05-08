package com.corvi.translationdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    GoogleTranslate translator;
    EditText translateedittext;
    TextView translatabletext,translatabletext1,translatabletext2,translatabletext3,translatabletext4,translatabletext5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        translateedittext = (EditText) findViewById(R.id.translateedittext);
        Button translatebutton = (Button) findViewById(R.id.translatebutton);
        translatebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new EnglishToTagalog().execute();

            }
        });
    }


    private class EnglishToTagalog extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        protected void onError(Exception ex) {

        }
        @Override
        protected Void doInBackground(Void... params) {

            try {
                translator = new GoogleTranslate("AIzaSyANJWfD2y-1lrsyzDiA8EKnAR78uMgeyQI");

                Thread.sleep(1000);


            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;

        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPreExecute() {
            //start the progress dialog
            progress = ProgressDialog.show(MainActivity.this, null, "Translating...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Void result) {
            progress.dismiss();

            super.onPostExecute(result);
            translated();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }


    public void translated(){

        String translatetotagalog = translateedittext.getText().toString();//get the value of text
        String text = translator.translte(translatetotagalog, "en", "fr");
        String text1 = translator.translte(translatetotagalog, "en", "hi");
        String text2 = translator.translte(translatetotagalog, "en", "de");
        String text3 = translator.translte(translatetotagalog, "en", "in");
        String text4 = translator.translte(translatetotagalog, "en", "it");
        String text5 = translator.translte(translatetotagalog, "en", "ja");
        translatabletext = (TextView) findViewById(R.id.translatabletext);
        translatabletext1 = (TextView) findViewById(R.id.translatabletext1);
        translatabletext2 = (TextView) findViewById(R.id.translatabletext2);
        translatabletext3 = (TextView) findViewById(R.id.translatabletext3);
        translatabletext4 = (TextView) findViewById(R.id.translatabletext4);
        translatabletext5 = (TextView) findViewById(R.id.translatabletext5);
        translatabletext.setText(text);
        translatabletext1.setText(text1);
        translatabletext2.setText(text2);
        translatabletext3.setText(text3);
        translatabletext4.setText(text4);
        translatabletext5.setText(text5);

        Log.e("Mainactivity","translate : " +text);

    }

}
