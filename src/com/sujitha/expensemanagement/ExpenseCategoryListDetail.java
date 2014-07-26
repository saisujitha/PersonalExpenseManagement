package com.sujitha.expensemanagement;


import com.sujitha.expensemanagement.adapters.ExpenseCatgDetailListAdapter;
import com.sujitha.expensemanagement.data.DataAccessLayer;
import com.sujitha.expensemanagement.models.Expense;
import com.sujitha.expensemanagement.models.ExpenseCategory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ExpenseCategoryListDetail extends Activity implements OnItemClickListener {

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.expense_list_detailmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.Updateexp:
			Intent Updateexpense = new Intent(getApplicationContext(), UpdateExpense.class);
			startActivity(Updateexpense);
			return true;
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	ExpenseCatgDetailListAdapter expenseCatgDetailListAdapter;
	ExpenseCategory expenseCategory = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_expense_catg_list_detail);
		
		expenseCategory = DataAccessLayer.getInstance(getApplicationContext()).getExpenseCategoryByCatgId(
				getIntent().getIntExtra("catgId", 1));
		setTitle(expenseCategory.getExpCatgName() + " Expenses");
		expenseCatgDetailListAdapter = new ExpenseCatgDetailListAdapter(getApplicationContext());
		ListView expensesList = (ListView) findViewById(R.id.expensesList);
		expensesList.setAdapter(expenseCatgDetailListAdapter);
		
		
		
		TextView emptyText = new TextView(getApplicationContext());
		emptyText.setText("OOps no expenses");
		expensesList.setEmptyView(emptyText);
		
}
	@Override
	protected void onResume() {
		super.onResume();
		expenseCatgDetailListAdapter.setData(DataAccessLayer.getInstance(getApplicationContext()).getExpensesForExpenseCategory(
				expenseCategory.getExpCatgId()));
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Expense expense = expenseCatgDetailListAdapter.getItem(position);
		Intent Updateexp = new Intent(getApplicationContext(), UpdateExpense.class);
		Updateexp.putExtra("catgId", expense.getExpenseId());
		startActivity(Updateexp);
	}
}
