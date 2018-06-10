package unicap.es.easystreets;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.auth0.android.jwt.JWT;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                if(token == null || token.isEmpty()){
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    JWT jwt = new JWT(token);
                    if(jwt.isExpired(0)){
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(SplashActivity.this, MapsActivity.class);
                        startActivity(intent);
                    }
                }
                finish();
            }
        }, 2000);
    }
}
