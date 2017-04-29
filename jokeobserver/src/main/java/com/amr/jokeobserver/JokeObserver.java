package com.amr.jokeobserver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeObserver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_observer);
        final Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            final TextView txtJoke = (TextView) findViewById(R.id.txt_joke);
            txtJoke.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        }
    }

    public static Intent makeIntent(Context context, String joke) {
        final Intent intent = new Intent(context, JokeObserver.class);
        intent.putExtra(Intent.EXTRA_TEXT, joke);
        return intent;
    }
}
