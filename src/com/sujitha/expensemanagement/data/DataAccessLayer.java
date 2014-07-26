package com.sujitha.expensemanagement.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.sujitha.expensemanagement.models.Expense;
import com.sujitha.expensemanagement.models.ExpenseCategory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataAccessLayer {

	static final int currentVersion = 1;
	static final String dbName = "expDb";
	private static DataAccessLayer _dasInstance = null;
	SQLiteDatabase database = null;
	private Context context;

	private ArrayList<ExpenseCategory> exceptionCategories = new ArrayList<ExpenseCategory>();

	private DataAccessLayer(Context context) {
		this.context = context;
		DatabaseHelper databaseHelper = new DatabaseHelper(this.context, dbName, null, currentVersion);
		database = databaseHelper.getWritableDatabase();
		exceptionCategories.add(new ExpenseCategory(0, "Misc"));
		exceptionCategories.add(new ExpenseCategory(1, "Food"));
		exceptionCategories.add(new ExpenseCategory(2, "Rents & Loans"));
		exceptionCategories.add(new ExpenseCategory(3, "Entertainment"));
		exceptionCategories.add(new ExpenseCategory(4, "Transport"));
	}

	public static DataAccessLayer getInstance(Context context) {
		if (_dasInstance == null) {
			_dasInstance = new DataAccessLayer(context);
		}
		return _dasInstance;
	}

	public ArrayList<ExpenseCategory> getExpensesCategories() {
		return exceptionCategories;
	}

	public Float getExpenseForCategory(Integer catgId) {
		Cursor cursor = database.rawQuery("select sum(amount) from Expenses where catgId = " + catgId, null);
		cursor.moveToPosition(0);
		Float expense = cursor.getFloat(0);
		cursor.close();
		return expense;
	}

	public Float getTotalExpense() {
		Cursor cursor = database.rawQuery("select sum(amount) from Expenses", null);
		cursor.moveToPosition(0);
		Float expense = cursor.getFloat(0);
		cursor.close();
		return expense;
	}

	public void createExpense(Integer expCatId, float amount, String desc) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("description", desc);
		contentValues.put("amount", amount);
		contentValues.put("catgId", expCatId);
		contentValues.put("date", (new Date()).getTime());
		database.insert("Expenses", null, contentValues);
	}

	public ExpenseCategory getExpenseCategoryByCatgId(Integer catgId) {
		ExpenseCategory expenseCategory = null;
		for (Iterator<ExpenseCategory> iterator = exceptionCategories.iterator(); iterator.hasNext();) {
			ExpenseCategory item = (ExpenseCategory) iterator.next();
			if (item.getExpCatgId() == catgId) {
				expenseCategory = item;
				break;
			}
		}
		return expenseCategory;
	}

	public ArrayList<Expense> getExpensesForExpenseCategory(Integer expenseCategoryId) {
		ArrayList<Expense> expenses = new ArrayList<Expense>();
		Cursor cursor = database.query("Expenses", null, "catgId = " + expenseCategoryId, null, null, null, "date");
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			String expenseDesc = cursor.getString(cursor.getColumnIndex("description"));
			Integer expenseId = cursor.getInt(cursor.getColumnIndex("id"));
			Integer expCatgId = cursor.getInt(cursor.getColumnIndex("catgId"));
			Float amount = cursor.getFloat(cursor.getColumnIndex("amount"));
			Date date = new Date(cursor.getLong(cursor.getColumnIndex("date")));
			cursor.moveToNext();
			Expense expense = new Expense(expenseId, expenseDesc, amount, getExpenseCategoryByCatgId(expCatgId), date);
			expenses.add(expense);
		}
		return expenses;
	}

	public class DatabaseHelper extends SQLiteOpenHelper {

		private DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE 'Expenses' ( 'id' INTEGER PRIMARY KEY AUTOINCREMENT, 'description' TEXT, 'amount' REAL, 'catgId' INTEGER, 'date' NUMERIC )");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
		}

	}
}
