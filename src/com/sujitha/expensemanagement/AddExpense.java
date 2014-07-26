package com.sujitha.expensemanagement;

import com.sujitha.expensemanagement.adapters.ExpenseCategoriesTypeSpinnerAdapter;
import com.sujitha.expensemanagement.data.DataAccessLayer;
import com.sujitha.expensemanagement.models.ExpenseCategory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddExpense extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_add_expense);
		Button btnAddExpense = (Button) findViewById(R.id.btnAddExpense);
		btnAddExpense.setOnClickListener(this);
		Spinner spnExpCatgs = (Spinner) findViewById(R.id.spinExpCatg);
		spnExpCatgs.setAdapter(new ExpenseCategoriesTypeSpinnerAdapter(getApplicationContext()));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAddExpense:
			addExpense();
			break;
		default:
			break;
		}
	}

	private void addExpense() {
		EditText edtAmount = (EditText) findViewById(R.id.edtAmount);
		if (edtAmount.getText().toString().trim().length() == 0) {
			Toast.makeText(getApplicationContext(), "Please enter amount", Toast.LENGTH_SHORT).show();
			return;
		}
		Float amount = Float.valueOf(edtAmount.getText().toString());
		if (amount <= 0) {
			Toast.makeText(getApplicationContext(), "Please enter a valid amount", Toast.LENGTH_SHORT).show();
			return;
		}

		EditText edtDesc = (EditText) findViewById(R.id.edtDesc);
		String description = edtDesc.getText().toString();
		if (description.trim().length() == 0) {
			Toast.makeText(getApplicationContext(), "Please enter description", Toast.LENGTH_SHORT).show();
			return;
		}

		Spinner spnExpCatgs = (Spinner) findViewById(R.id.spinExpCatg);
		ExpenseCategory expenseCategory = (ExpenseCategory) spnExpCatgs.getSelectedItem();

		DataAccessLayer.getInstance(getApplicationContext()).createExpense(expenseCategory.getExpCatgId(), amount, description);
		finish();
	}

}
