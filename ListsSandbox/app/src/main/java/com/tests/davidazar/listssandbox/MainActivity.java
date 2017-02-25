package com.tests.davidazar.listssandbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mRecyclerButton;
    private Button mRealmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerButton = (Button) findViewById(R.id.recycler_activity_button);
        mRealmButton = (Button) findViewById(R.id.realm_activity_button);

        mRecyclerButton.setOnClickListener(this);
        mRealmButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        Intent intent = null;

        switch (view.getId()) {

            case R.id.recycler_activity_button:
                intent = new Intent(this, RecyclerActivity.class);
                break;

            case R.id.realm_activity_button:
                intent = new Intent(this, RealmListActivity.class);
                break;
        }

        startActivity(intent);
    }
}
