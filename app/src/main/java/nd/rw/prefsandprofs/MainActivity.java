package nd.rw.prefsandprofs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public EditText et_value;
    public SharedPreferences prefs;
    public String storedValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et_value = (EditText) findViewById(R.id.edit_text);

        prefs = getPreferences(Context.MODE_PRIVATE);

        storedValue = prefs.getString(getString(R.string.stored_value_name), "No value present");
        Log.d(TAG, "onCreate: retained value: " + storedValue);
        et_value.setText(storedValue);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void saveValue(View view) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(getString(R.string.stored_value_name), et_value.getText().toString());
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called with");
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(getString(R.string.stored_value_name), et_value.getText().toString());
        Log.d(TAG, "onStop(): value put: " + et_value.getText().toString());
        editor.apply();
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: onDestroy() called");
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString(getString(R.string.stored_value_name), et_value.getText().toString());
//        Log.d(TAG, "onDestroy: value put: " + et_value.getText().toString());
//        editor.apply();
        super.onDestroy();
    }
}
