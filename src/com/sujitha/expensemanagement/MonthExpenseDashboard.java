package com.sujitha.expensemanagement;

import com.sujitha.expensemanagement.adapters.ExpenseCatgListAdapter;
import com.sujitha.expensemanagement.data.DataAccessLayer;
import com.sujitha.expensemanagement.models.ExpenseCategory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MonthExpenseDashboard extends Activity implements OnItemClickListener {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.monthexpensemenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addExpense:
			Intent addExpense = new Intent(getApplicationContext(), AddExpense.class);
			startActivity(addExpense);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
		totalExpense.setText(DataAccessLayer.getInstance(getApplicationContext()).getTotalExpense().toString());
		expenseCatgListAdapter.notifyDataSetChanged();
	}

	ExpenseCatgListAdapter expenseCatgListAdapter;
	ListView expCatgList;
	TextView totalExpense;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_month_expense);
		expenseCatgListAdapter = new ExpenseCatgListAdapter(null, null, getApplicationContext());
		expCatgList = (ListView) findViewById(R.id.exp_catg_list);
		expCatgList.setAdapter(expenseCatgListAdapter);
		expCatgList.setOnItemClickListener(this);
		totalExpense = (TextView) findViewById(R.id.totalExpenseAmount);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		ExpenseCategory expenseCategory = expenseCatgListAdapter.getItem(position);
		Intent expenseCatgListDetail = new Intent(getApplicationContext(), ExpenseCategoryListDetail.class);
		expenseCatgListDetail.putExtra("catgId", expenseCategory.getExpCatgId());
		startActivity(expenseCatgListDetail);
	}

}
