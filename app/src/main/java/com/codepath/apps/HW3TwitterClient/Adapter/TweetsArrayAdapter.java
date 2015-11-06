package com.codepath.apps.HW3TwitterClient.Adapter;


import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.HW3TwitterClient.R;
import com.codepath.apps.HW3TwitterClient.models.Tweet;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by tawu on 11/3/15.
 */
//take the tweet objects and turning them into views displayed in the list
public class TweetsArrayAdapter extends ArrayAdapter<Tweet>
{
    public TweetsArrayAdapter(Context context, List<Tweet> tweets)
    {
        //super(context, android.R.layout.simple_list_item_1, tweets); //middle param is the template that will be used to display view
        super(context, 0, tweets); //now we can use below getView(...) to show the customer logic
    }

    //override and setup custom template
    // viewholder pattern

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1. get the tweet
        Tweet tweet = getItem(position);

        // 2. find or inflate the template
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
        }

        // 3. find the subviews to fill with data  in the template
        ImageView ivUserImage = (ImageView)convertView.findViewById(R.id.ivUserImage);
        TextView tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);
        TextView tvTweetBody = (TextView)convertView.findViewById(R.id.tvTweetBody);
        TextView tvUserScreenName = (TextView)convertView.findViewById(R.id.tvUserScreenName);
        TextView tvTweetLike = (TextView)convertView.findViewById(R.id.tvTweetLikeNum);
        TextView tvTimeStamp = (TextView)convertView.findViewById(R.id.tvTimeStamp);

        // 4. populate data inoto the subviews
        tvUserName.setText(tweet.getUser().getName());
        tvTweetBody.setText(tweet.getBody());
        ivUserImage.setImageResource(android.R.color.transparent); // clear out the old image for a recycled view
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivUserImage);

        tvUserScreenName.setText("@" + tweet.getUser().getScreenName());
        tvTweetLike.setText(String.valueOf(tweet.getLikesNum()));
        tvTimeStamp.setText(getRelativeTimeAgo(tweet.getCreateat()));

        //5. return the view to be inserted into the list
        return convertView;
    }

    //
    public String getRelativeTimeAgo(String rawJsonDate)
    {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try
        {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        }
        catch (java.text.ParseException e)
        {
            e.printStackTrace();
        }

        return relativeDate;
    }
}
