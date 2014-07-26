package com.sujitha.expensemanagement;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UpdateExpense extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_update_expense);
		Button btnUpdateExp = (Button) findViewById(R.id.btnUpdate);
		btnUpdateExp.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnUpdate:
			UpdateExpense();
			break;
		default:
			break;
		}
	}

	private void UpdateExpense() {
		EditText edtAmount = (EditText) findViewById(R.id.edtAmt);
		if (edtAmount.getText().toString().trim().length() == 0) {
			Toast.makeText(getApplicationContext(), "Please enter amount", Toast.LENGTH_SHORT).show();
			return;
		}
		Float amount = Float.valueOf(edtAmount.getText().toString());
		if (amount <= 0) {
			Toast.makeText(getApplicationContext(), "Please enter a valid amount", Toast.LENGTH_SHORT).show();
			return;
			
			
		}
		
	}
}
