package comf.example.user.show_sms;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private SimpleCursorAdapter simpleCursorAdapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.myList);

        //permission request
        if(checkSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_SMS}, 0);
        }

        intialize();
        request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(requestCode==0){
            if(grantResults.length>0 && grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "SMS Read Permission denied", Toast.LENGTH_LONG);
            }
        }
    }

    private void intialize(){
        Uri uri = Uri.parse("content://sms/inbox");
         cursor = getContentResolver().query(
                uri, null, null, null, null);
    }

    private void request(){
        String[] mProjection = {Telephony.Sms.ADDRESS, Telephony.Sms.BODY, Telephony.Sms.DATE_SENT};
        String selectionClause = null;
        String[] selectionArgs = {""};

        int[] listItems = {R.id.address, R.id.body, R.id.date_sent};

        simpleCursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.listviewlayout,
                cursor,
                mProjection,
                listItems,
                0);
        mListView.setAdapter(simpleCursorAdapter);
    }
}
