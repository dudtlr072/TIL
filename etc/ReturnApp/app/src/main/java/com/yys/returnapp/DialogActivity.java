package com.yys.returnapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by YYS on 2018-01-11.
 */

public class DialogActivity extends Activity {

    private TextView mMessageView;
    private Button mOK;

    private String mTitle;
    private String mMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        mMessageView = (TextView)findViewById(R.id.message);
        mOK = (Button)findViewById(R.id.ok);

        mTitle = getIntent().getStringExtra("title");
        mMessage = getIntent().getStringExtra("message");


    }
}
