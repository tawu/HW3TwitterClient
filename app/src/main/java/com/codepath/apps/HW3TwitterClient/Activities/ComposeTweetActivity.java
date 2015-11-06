package com.codepath.apps.HW3TwitterClient.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.HW3TwitterClient.R;

public class ComposeTweetActivity extends ActionBarActivity
{

    private ImageView ivMyImage;
    private TextView tvMyName;
    private TextView tvMyScreenName;
    private EditText etBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_tweet);

        //fetch views
        ivMyImage = (ImageView)findViewById(R.id.ivMyImage);
        tvMyName = (TextView)findViewById(R.id.tvMyName);
        tvMyScreenName = (TextView)findViewById(R.id.tvMyScreenName);
        etBody = (EditText)findViewById(R.id.etBody);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compose_tweet, menu);
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