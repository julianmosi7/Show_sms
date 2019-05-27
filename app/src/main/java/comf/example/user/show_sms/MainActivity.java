package comf.example.user.show_sms;

import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private CursorLoader cursorLoader;
    private SimpleCursorAdapter simpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.myList);
        intialize();
    }

    private void intialize(){
        Uri uri = Uri.parse("content://sms/inbox");
        cursorLoader = (CursorLoader) getContentResolver().query(
                uri, null, null, null, null);
    }

    private void request(){
        String[] mProjection = {Telephony.Sms.ADDRESS, Telephony.Sms.BODY, Telephony.Sms.DATE_SENT};
        //String selectionClause = null;
        //String[] selectionArgs = {""};

        int[] listItems = {R.id.address, R.id.body, R.id.date_sent};

        simpleCursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.listviewlayout,
                (Cursor) cursorLoader,
                mProjection,
                listItems,
                0);
        mListView.setAdapter(simpleCursorAdapter);
    }
}
