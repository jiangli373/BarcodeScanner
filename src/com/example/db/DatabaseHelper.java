package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.util.Global;



public class DatabaseHelper extends SQLiteOpenHelper {


	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public DatabaseHelper(Context context) {
		
		super(context, context.getFileStreamPath(Global.DATABASE_FILENAME).getAbsolutePath(), null, 1);
		
	}

	/**
	 * 用户第一次使用软件时调用，实现数据库的操作crud
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub		
//		 db.execSQL("CREATE TABLE IF NOT EXISTS newtable (id integer primary key,isbn varchar(60))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		onCreate(db);
	}

	/**
	 * 根据版本号进行更新
	 * 
	 * @param db
	 * @param mNewVersion
	 */
	public void checkVersionCreate(SQLiteDatabase db, int mNewVersion) {
		int version = db.getVersion();
		if (version != mNewVersion) {
			
			db.beginTransaction();
			try {
				if (version == 0) {
					onCreate(db);
				} else {
					onUpgrade(db, version, mNewVersion);
				}
				db.setVersion(mNewVersion); // 设置为新的版本号
				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}
		}
	}

	
	
}