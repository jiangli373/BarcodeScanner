package com.example.barcodescanner;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private Button scanner,exit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanner = (Button)findViewById(R.id.scanner);
        exit = (Button)findViewById(R.id.exit);
        scanner.setOnClickListener(this);
        exit.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.exit){
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}else if(v.getId()==R.id.scanner){
			 Intent intent = new Intent("com.google.zxing.client.android.SCAN");
             intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
             startActivityForResult(intent, 0);
		}		
		
	}
	public void onActivityResult(int requestCode,int resultCode,Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
            	Intent ii = new Intent();
            	ii.putExtra("content", intent.getStringExtra("SCAN_RESULT"));
            	ii.setClass(this,ShowResultActivity.class);
			    startActivity(ii);
            } else if (resultCode == RESULT_CANCELED) {
            	Intent ii = new Intent();
            	ii.setClass(this,ShowResultActivity.class);
			   startActivity(ii);
            }
        }
    }

}
