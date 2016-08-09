package com.ksyun.live.demo.demo.vrdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ksy.recordlib.demo.demo.vrdemo.R;

public class MainActivity extends AppCompatActivity {
    private TextView txt_url;
    private EditText edit_url;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_url = (TextView)findViewById(R.id.txt_url);
        edit_url = (EditText)findViewById(R.id.edit_url);
        btn_start = (Button)findViewById(R.id.btn_start);

        txt_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_url.setText(txt_url.getText().toString());
            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = edit_url.getText().toString();
                if (!TextUtils.isEmpty(url)){
                    Intent intent  = new Intent(MainActivity.this,TestVideoActivity.class);
                    intent.putExtra("path",url);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "empty url!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
