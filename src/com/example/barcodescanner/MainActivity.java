package com.example.barcodescanner;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.db.DataService;
import com.example.model.BookInfo;

public class MainActivity extends Activity implements OnClickListener{

	private Button scanner,exit;
	private final String DATABASE_FILENAME = "marc.db";
	 DataService ds = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (!new File(
//				"/data/data/com.witmob.babyshow/files/marc.db")
//				.exists()) {
			importDatatbase();
//		}
        scanner = (Button)findViewById(R.id.scanner);
        exit = (Button)findViewById(R.id.exit);
        scanner.setOnClickListener(this);
        exit.setOnClickListener(this);
    }
    
    

	private void importDatatbase() {
		try {
			
			File file = new File(DATABASE_FILENAME);
			if (!file.exists()) {
				InputStream is = getResources().openRawResource(R.raw.marc);
				FileOutputStream fileOutputStream = openFileOutput(
						DATABASE_FILENAME, 0);
				byte[] buffer = new byte[8192];
				int count = 0;

				while ((count = is.read(buffer)) > 0) {
					fileOutputStream.write(buffer, 0, count);
				}
				fileOutputStream.close();
				is.close();
				ds = new DataService(this);
				ds.createNewTable();
				ds.updateTable();
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("tag", e.getMessage(), e);
		}
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
            	String ISBN = intent.getStringExtra("SCAN_RESULT");
            	         	
            	ii.putExtra("content", ISBN);
            	ii.setClass(this,ShowResultActivity.class);
			    startActivity(ii);
            } else if (resultCode == RESULT_CANCELED) {
//            	Intent ii = new Intent();
//            	ii.setClass(this,ShowResultActivity.class);
//			   startActivity(ii);
            }
        }
    }

}
