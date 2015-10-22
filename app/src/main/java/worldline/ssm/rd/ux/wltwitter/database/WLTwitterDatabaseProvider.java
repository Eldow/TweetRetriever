package worldline.ssm.rd.ux.wltwitter.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import worldline.ssm.rd.ux.wltwitter.utils.Constants;

/**
 * Created by franc on 16/10/2015.
 */
public class WLTwitterDatabaseProvider extends ContentProvider {
    private static final int TWEET_CORRECT_URI_CODE = 42;
    private WLTwitterDatabaseHelper mDBHelper;
    private UriMatcher mUriMatcher;
    @Override
    public boolean onCreate() {
        mDBHelper = new WLTwitterDatabaseHelper(getContext());
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(WLTwitterDatabaseContract.CONTENT_PROVIDER_TWEETS_AUTHORITY,WLTwitterDatabaseContract.TABLE_TWEETS,TWEET_CORRECT_URI_CODE);
        return true;
    }

    @Override
    public String getType(Uri uri) {
        if(mUriMatcher.match(uri)==TWEET_CORRECT_URI_CODE){
            return WLTwitterDatabaseContract.TWEETS_CONTENT_TYPE;
        }
        throw new IllegalArgumentException("Unknown URI " + uri);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.i(Constants.General.LOG_TAG, "INSERT");
        long id = 0;
        if(values != null){
        id = mDBHelper.getWritableDatabase().insert(WLTwitterDatabaseContract.TABLE_TWEETS,"",values);}
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.v(Constants.General.LOG_TAG, "QUERY");
        return mDBHelper.getReadableDatabase().query(WLTwitterDatabaseContract.TABLE_TWEETS, projection, selection, selectionArgs, sortOrder,null,null);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.e(Constants.General.LOG_TAG, "DELETE");
        return mDBHelper.getWritableDatabase().delete(WLTwitterDatabaseContract.TABLE_TWEETS, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d(Constants.General.LOG_TAG, "UPDATE");
        if(values != null) {
            return mDBHelper.getWritableDatabase().update(WLTwitterDatabaseContract.TABLE_TWEETS, values, selection, selectionArgs);
        }
        else return 0;
    }
}
