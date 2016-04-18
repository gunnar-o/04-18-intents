package edu.uw.intentdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "**Main**";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View launchButton = findViewById(R.id.btnLaunch);
        launchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Launch button pressed");

                // Explicit intent: We know exactly who the receiver is
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("edu.uw.intentdemo.message", "Hello from ActivityMain");
                startActivity(intent);

            }
        });

    }

    public void callNumber(View v) {
        Log.v(TAG, "Call button pressed");

        // Implicit intent
        Intent implicitIntent = new Intent(Intent.ACTION_DIAL);
        // Finds an activity that can dial a number
        implicitIntent.setData(Uri.parse("tel:206-685-1622"));

        if (implicitIntent.resolveActivity(getPackageManager()) != null) // Make sure there is an activity that can accept it
            startActivity(implicitIntent);


    }

    private static int REQUEST_PICTURE_CODE = 1;

    public void takePicture(View v) {
        Log.v(TAG, "Camera button pressed");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null)
         startActivityForResult(intent, REQUEST_PICTURE_CODE);   // For when we want a result back from the activity

    }

    @Override                       // REQUEST code above
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Callback that occurs when we get a result back from an activity
        if (requestCode == REQUEST_PICTURE_CODE && resultCode == RESULT_OK) {
            // I got a picture
            Bundle extras = data.getExtras();   // Get picture from the bundle
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ImageView imageView = (ImageView) findViewById(R.id.imgThumbnail);
            imageView.setImageBitmap(imageBitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void sendMessage(View v) {
        Log.v(TAG, "Message button pressed");


    }

}
