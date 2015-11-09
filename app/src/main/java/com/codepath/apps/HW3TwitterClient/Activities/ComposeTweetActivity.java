package com.codepath.apps.HW3TwitterClient.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.HW3TwitterClient.R;
import com.codepath.apps.HW3TwitterClient.REST.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class ComposeTweetActivity extends ActionBarActivity
{

    private ImageView ivMyImage;
    private TextView tvMyName;
    private TextView tvMyScreenName;
    private EditText etBody;

    private String myImageUrl = "http://abs.twimg.com/sticky/default_profile_images/default_profile_4_normal.png";
    private String myName = "tawu";
    private String myScreenName = "72Jzm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_tweet);

        //display action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_tweet);
        //getSupportActionBar().setTitle(R.string.home);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //fetch views
        ivMyImage = (ImageView)findViewById(R.id.ivMyImage);
        tvMyName = (TextView)findViewById(R.id.tvMyName);
        tvMyScreenName = (TextView)findViewById(R.id.tvMyScreenName);
        etBody = (EditText)findViewById(R.id.etBody);

        Picasso.with(getBaseContext()).load(myImageUrl).into(ivMyImage);
        tvMyName.setText(myName);
        tvMyScreenName.setText(myScreenName);
    }



    public void cancelCompose(View v)
    {
        finish();
    }

    public void onSendTweetAction(View v)
    {
        String text = etBody.getText().toString();

        if (text == null)
        {
            Toast.makeText(getBaseContext(), "Blank tweet", Toast.LENGTH_SHORT).show();
            return;
        }

        //Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
        //finish();


        TwitterClient client = TwitterApplication.getRestClient();

        client.postTweet(text, null, new JsonHttpResponseHandler()
        {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                Toast.makeText(getBaseContext(), "Tweet posted!", Toast.LENGTH_SHORT).show();
                Intent data = new Intent();
                data.putExtra("response", response.toString());
                setResult(RESULT_OK, data);
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)
            {
                throwable.printStackTrace();
                Log.d("DEBUG", errorResponse.toString());
            }
        });

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
