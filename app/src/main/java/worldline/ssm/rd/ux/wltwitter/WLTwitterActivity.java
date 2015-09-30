package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class WLTwitterActivity extends Activity {
    public static final String WLT_PREFS = "WLTFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String username;
        if(getIntent().getExtras() != null) { //Sign in
            username = getIntent().getExtras().getString("username");
        } else { //Quick start
            username = WLTwitterApplication.getContext().getSharedPreferences(WLT_PREFS, Context.MODE_PRIVATE).getString("username",null);
        }
        getActionBar().setSubtitle(username);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wltwitter_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        // On Logout
        if (id == R.id.actionLogout) {
            //clear username & password
            WLTwitterApplication.getContext().getSharedPreferences(WLT_PREFS, Context.MODE_PRIVATE).edit().remove("username").commit();
            WLTwitterApplication.getContext().getSharedPreferences(WLT_PREFS, Context.MODE_PRIVATE).edit().remove("password").commit();
            //start login activity
            Intent intent = new Intent(this, WLTwitterLoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
