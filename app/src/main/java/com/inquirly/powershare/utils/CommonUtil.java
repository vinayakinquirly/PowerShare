package com.inquirly.powershare.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by Vinayak on 8/31/2016.
 */
public class CommonUtil {

    public static String getPath(Context context, Uri uri) {
        try {
            if ("content".equals(uri.getScheme())) {
                String[] projection = {MediaStore.Images.Media.DATA};
                Cursor cursor = ((Activity) context).managedQuery(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            } else {
                return uri.getPath();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "";
        }
    }

}
