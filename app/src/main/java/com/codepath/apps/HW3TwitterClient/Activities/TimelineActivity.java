package com.codepath.apps.HW3TwitterClient.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.HW3TwitterClient.R;
import com.codepath.apps.HW3TwitterClient.Adapter.TweetsArrayAdapter;
import com.codepath.apps.HW3TwitterClient.REST.TwitterClient;
import com.codepath.apps.HW3TwitterClient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;


public class TimelineActivity extends ActionBarActivity {

    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //display action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_tweet);
        getSupportActionBar().setTitle(R.string.home);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //find listview
        lvTweets = (ListView)findViewById(R.id.lvTweets);

        //create arraylist (data source)
        tweets = new ArrayList<>();
        //construct the adapter from data source
        aTweets = new TweetsArrayAdapter(this, tweets);
        //connect adapter to listview
        lvTweets.setAdapter(aTweets);

        client = TwitterApplication.getRestClient();
        populateTimeLine();
    }

    // send an API request to get the timeline json
    // fill the listview by createing the tweet objects from the json
    private void populateTimeLine()
    {

        client.getHomeTimeLine(new JsonHttpResponseHandler()
        {
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response)
            {
                //Log.d("DEBUG", response.toString());

                //get JSON
                //deserilize JSON
                //create models and add to adapter
                //load the model data into listview
                aTweets.clear();
                aTweets.addAll(Tweet.fromJSONArray(response));

            }

            // FAILURE

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse)
            {
                Log.d("DEBUG", errorResponse.toString());
            }
        });
    }

    public void onComposeAction(MenuItem mi)
    {
        // handle click here
        Intent intent = new Intent(TimelineActivity.this, ComposeActivity.class);
        Toast.makeText(TimelineActivity.this, "Compose",Toast.LENGTH_SHORT).show();
        //startActivityForResult(intent, RESULT_OK);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
/*        if (id == R.id.action_compose) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }
}
