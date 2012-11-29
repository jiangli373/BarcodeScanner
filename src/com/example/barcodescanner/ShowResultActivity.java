package com.example.barcodescanner;

import com.example.db.DataService;
import com.example.model.BookInfo;
import com.example.util.ClassCategory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowResultActivity extends Activity implements OnClickListener{

	private TextView bookname,author,classnum,postion,isbnno;
	private Button bt;
	private ClassCategory cc = new ClassCategory();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        Intent intent = getIntent();
        String conent = intent.getStringExtra("content");
        isbnno = (TextView)findViewById(R.id.ISBNNO);
        bookname = (TextView)findViewById(R.id.content); 
        author = (TextView)findViewById(R.id.author); 
        classnum = (TextView)findViewById(R.id.classnumber); 
        postion = (TextView)findViewById(R.id.postion); 
        bt = (Button)findViewById(R.id.back);
        bt.setOnClickListener(this);
    		isbnno.setText(intent.getStringExtra("ISBN"));
    		bookname.setText(intent.getStringExtra("bookname"));
    		author.setText(intent.getStringExtra("author"));
    		classnum.setText(intent.getStringExtra("classno"));
    		postion.setText(intent.getStringExtra("position"));
    }
	@Override
	public void onClick(View v) {
		this.finish();
	}
	@Override
	public void finish() {
		super.finish();
	} 
	
}
