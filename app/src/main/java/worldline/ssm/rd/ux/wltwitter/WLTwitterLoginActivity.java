package worldline.ssm.rd.ux.wltwitter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import worldline.ssm.rd.ux.wltwitter.utils.Constants;


public class WLTwitterLoginActivity extends Activity implements View.OnClickListener {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Init intent
        intent = new Intent(this, WLTwitterActivity.class);
        //getSharedPreferences(WLT_PREFS,  Context.MODE_PRIVATE).edit().clear().commit();
        //If prefs are already stored, start second activity
        if(WLTwitterApplication.getContext().getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE).contains("username")){
            startActivity(intent);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wltwitter_login);
        //Add a listener to sign in button
        findViewById(R.id.buttonSignIn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //Init prefs & intent
        SharedPreferences prefs = WLTwitterApplication.getContext().getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        //Get what we need from editTexts
        EditText username = (EditText) findViewById(R.id.editTextLogin);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        String strUsername = username.getText().toString();
        String strPassword = username.getText().toString();
        //Check for empty fields
        if (TextUtils.isEmpty(username.getText()) && TextUtils.isEmpty(password.getText())) {
            Toast.makeText(this, R.string.error_empty_fields, Toast.LENGTH_LONG).show();
        }else if (TextUtils.isEmpty(username.getText())){
            Toast.makeText(this, R.string.error_no_login, Toast.LENGTH_LONG).show();
        }else if (TextUtils.isEmpty(password.getText())){
            Toast.makeText(this, R.string.error_no_password, Toast.LENGTH_LONG).show();
        }
        else {
        //Store in prefs for future connections
            prefs.edit().putString("username",strUsername).commit();
            prefs.edit().putString("password", strPassword).commit();
            //Create new Bundle to display username
            Bundle extras = new Bundle();
            extras.putString("username", strUsername);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wltwitter_login, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
