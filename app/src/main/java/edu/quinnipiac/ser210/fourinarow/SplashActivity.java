/**
 * SplashActivity - Accepts username and loads second screen of game activity
 * @author Chris Rocco
 * @date 2/20/2022
 */


package edu.quinnipiac.ser210.fourinarow;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SplashActivity extends AppCompatActivity {

    private static final int REQUEST_GAME = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void onClick(View view) {

        EditText messageView = (EditText)findViewById(R.id.username);
        String messageText = messageView.getText().toString();
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("usernameKey",messageText);
        startActivity(intent);




    }

}