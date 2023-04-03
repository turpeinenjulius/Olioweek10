package fi.keimoraimo.olioweek10;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = MainActivity.this;
        UserStorage.getInstance().loadUsers(context);
    }

    public void switchToUser(View view){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
    public void switchToUserStorage(View view){
        Intent intent = new Intent(this, UserStorageActivity.class);
        startActivity(intent);
    }

}