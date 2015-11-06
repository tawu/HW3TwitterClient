package com.codepath.apps.HW3TwitterClient.models;

/**
 * Created by tawu on 11/3/15.
 */

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * text, id, created_at, user -> name
 [
 {
 "text": "...",
 "contributors": null,
 "id": 240558470661799936,
 "created_at": "Tue Aug 28 21:16:23 +0000 2012",
 "retweet_count": 0,
 "in_reply_to_status_id_str": null,
 "geo": null,
 "retweeted": false,
 "in_reply_to_user_id": null,
 "place": null,
 "source": "<a href="//realitytechnicians.com\"" rel="\"nofollow\"">OAuth Dancer Reborn</a>",
 "user":
 {
 "name": "OAuth Dancer",
 "profile_sidebar_fill_color": "DDEEF6",
 "profile_background_tile": true,
 "profile_sidebar_border_color": "C0DEED",
 "profile_image_url": "http://a0.twimg.com/profile_images/730275945/oauth-dancer_normal.jpg",
 "created_at": "Wed Mar 03 19:37:35 +0000 2010",
 "location": "San Francisco, CA",
 "follow_request_sent": false,
 "id_str": "119476949",
 "is_translator": false,
 "profile_link_color": "0084B4",
 "entities":
 {
 "url":
 {
 "urls": [
 {
 "expanded_url": null,
 "url": "http://bit.ly/oauth-dancer",
 "indices": [
 0,
 26
 ],
 "display_url": null
 }
 ]
 },
 "description": null
 },
 "default_profile": false,
 "url": "http://bit.ly/oauth-dancer",
 "contributors_enabled": false,
 ...
 },
 {...}
 ...
 ]


 */

// parse the json + store the data,
// encapsulate state logic or display logic
public class Tweet
{

    //list out the attributes
    private String body;
    private long uid; //uuid for the tweet
    private String createat;
    private int likesNum;

    public int getLikesNum()
    {
        return likesNum;
    }

    private User user;


    public String getBody()
    {
        return body;
    }

    public long getUid()
    {
        return uid;
    }

    public String getCreateat()
    {
        return createat;
    }

    public User getUser()
    {
        return user;
    }

    //Deserilize the JSON to java object
    //Tweet.fromJson => <Tweet>
    public static Tweet fromJSON(JSONObject jsonObject)
    {
        Tweet tweet = new Tweet();

        try
        {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createat = jsonObject.getString("created_at");
            tweet.likesNum = jsonObject.getInt("favorite_count");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        return tweet;
    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray)
    {
        ArrayList<Tweet> tweets = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++)
        {
            try
            {
                JSONObject tweetJSON = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJSON);
                if(tweet != null)
                    tweets.add(tweet);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                continue;
            }
        }

        return tweets;
    }


}
