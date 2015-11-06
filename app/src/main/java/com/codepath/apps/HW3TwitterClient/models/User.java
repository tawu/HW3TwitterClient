package com.codepath.apps.HW3TwitterClient.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tawu on 11/3/15.
 */

/*
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
            "screen_name": "oauth_dancer",
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
        }
 */

public class User
{
    //list attribute
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    //deserilize user json to User
    public static User fromJSON(JSONObject jsonObject)
    {
        User u = new User();

        try
        {
            u.name = jsonObject.getString("name");
            u.uid = jsonObject.getLong("id");
            u.screenName = jsonObject.getString("screen_name");
            u.profileImageUrl = jsonObject.getString("profile_image_url");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return u;
    }


}
