package com.yys.returnapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by YYS on 2018-01-11.
 */

public class DialogActivity extends AppCompatActivity {

    private TextView mTitleView;
    private TextView mMessageView;
    private Button mOK;

    private String mTitle;
    private String mMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        mTitleView = (TextView)findViewById(R.id.title);
        mMessageView = (TextView)findViewById(R.id.message);
        mOK = (Button)findViewById(R.id.ok);

        mTitle = getIntent().getStringExtra("title");
        mMessage = getIntent().getStringExtra("message");

        mTitleView.setText(mTitle);
        mMessageView.setText(mMessage);
        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
