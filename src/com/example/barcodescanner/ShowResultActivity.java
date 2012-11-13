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

public class ShowResultActivity extends Activity implements OnClickListener{

	private TextView bookname,author,classnum,postion;
	private Button bt;
	private ClassCategory cc = new ClassCategory();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        Intent intent = getIntent();
        String conent = intent.getStringExtra("content");
        bookname = (TextView)findViewById(R.id.content); 
        author = (TextView)findViewById(R.id.author); 
        classnum = (TextView)findViewById(R.id.classnumber); 
        postion = (TextView)findViewById(R.id.postion); 
        bt = (Button)findViewById(R.id.back);
        bt.setOnClickListener(this);
        DataService ds = new DataService(this);
    	BookInfo bi = ds.getBookInfoByIsbn(conent);
    	if(bi!=null){
    		bookname.setText(bi.getBookName());
    		author.setText(bi.getAuthor());
    		classnum.setText(bi.getClassifyNumber());
    		String thispostion = ClassCategory.getpostion(bi.getClassifyNumber());
    		postion.setText(thispostion);
    	}  
       
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
