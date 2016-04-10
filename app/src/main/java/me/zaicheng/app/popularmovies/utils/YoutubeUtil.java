package me.zaicheng.app.popularmovies.utils;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by vmlinz on 4/10/16.
 */
public class YoutubeUtil {
    public final static String THUMBNAIL_BASE_URL = "http://img.youtube.com/vi/";

    public static String getThumbNailUrl(String id) {
        return THUMBNAIL_BASE_URL + id + "/1.jpg";
    }

    public static Intent getYoutubePlayerIntent(String id) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + id));
    }
}
