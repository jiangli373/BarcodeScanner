package com.example.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.model.BookInfo;



public class DataService {
	private DatabaseHelper databaseHelper;
	private Context context;

	public void close() {
		databaseHelper.close();
	}

	public DataService(Context context) {
		this.context = context;
		databaseHelper = new DatabaseHelper(this.context);
	}


	public void createNewTable(){
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.execSQL("CREATE TABLE IF NOT EXISTS newtable (id integer primary key,isbn varchar(60))");
		db.close();
	}
	
	public void updateTable(){
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.beginTransaction();
		Cursor cursor = db
				.rawQuery("SELECT * FROM marc", new String[] {});
		while(cursor.moveToNext()){
			String isbn = cursor.getString(cursor.getColumnIndex("ISBN"));
			if(isbn!=null){
				String noisbn = isbn.replaceAll("-", "");
				db.execSQL("INSERT INTO newtable (id, isbn) VALUES (?,?)",new Object[] { 
						cursor.getInt(cursor.getColumnIndex("id")),
						noisbn});
			}			
		}
		db.setTransactionSuccessful();        //设置事务处理成功，不设置会自动回滚不提交 
		db.endTransaction();
		db.close();
	}
	/**
	 * getAll
	 * @return
	 */
	public List<BookInfo> getBookInfo(){
		List<BookInfo> arrayList = new ArrayList<BookInfo>();
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		Cursor cursor = db
				.rawQuery("SELECT * FROM marc", new String[] {});
		while (cursor.moveToNext()) {
			BookInfo bi = new BookInfo();
			bi.setId(cursor.getInt(cursor.getColumnIndex("id")));
			bi.setISBN(cursor.getString(cursor.getColumnIndex("ISBN")));
			bi.setBookName(cursor.getString(cursor.getColumnIndex("bookName")));
			bi.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
			bi.setClassifyNumber(cursor.getString(cursor.getColumnIndex("classifyNumber")));
			arrayList.add(bi);
		}
		return arrayList;
	}
	
	/**
	 * 
	 * @param ISBN
	 * @return
	 */
	public BookInfo getBookInfoByIsbn(String ISBN){
		BookInfo bi = null;
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		Cursor newcursor = db
				.rawQuery("SELECT * FROM newtable where isbn = ? ", new String[] {ISBN});
       if(newcursor.moveToFirst()){
    	   Cursor cursor = db.rawQuery("SELECT * FROM marc where id = ? ", new String[] {newcursor.getInt(newcursor.getColumnIndex("id"))+""});
    	   if(cursor.moveToFirst()){
    		bi = new BookInfo();
   			bi.setId(cursor.getInt(cursor.getColumnIndex("id")));
   			bi.setISBN(cursor.getString(cursor.getColumnIndex("ISBN")));
   			bi.setBookName(cursor.getString(cursor.getColumnIndex("bookName")));
   			bi.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
   			bi.setClassifyNumber(cursor.getString(cursor.getColumnIndex("classifyNumber")));
   			cursor.close(); 
    	   }
    	  
		}
       newcursor.close();// 游标关闭
	   db.close();
	   return bi;
	}
}
