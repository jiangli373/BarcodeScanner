package com.example.barcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ShowResultActivity extends Activity implements OnClickListener{

	private TextView tv;
	private Button bt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        Intent intent = getIntent();
        String conent = intent.getStringExtra("content");
        tv = (TextView)findViewById(R.id.content);
        tv.setText(conent);
        bt = (Button)findViewById(R.id.back);
        bt.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		this.finish();
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	} 
	
}
