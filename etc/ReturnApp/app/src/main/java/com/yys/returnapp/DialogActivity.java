package com.yys.returnapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by YYS on 2018-01-11.
 */

public class DialogActivity extends AppCompatActivity {

//    private Toolbar mToolbar;
    private TextView mMessageView;
    private Button mOK;

    private String mTitle;
    private String mMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

//        mToolbar = (Toolbar)findViewById(R.id.my_toolbar);
//        setSupportActionBar(mToolbar);

        mMessageView = (TextView)findViewById(R.id.message);
        mOK = (Button)findViewById(R.id.ok);

        mTitle = getIntent().getStringExtra("title");
        mMessage = getIntent().getStringExtra("message");

//        getSupportActionBar().setTitle(mTitle);
        mMessageView.setText(mMessage);
        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
