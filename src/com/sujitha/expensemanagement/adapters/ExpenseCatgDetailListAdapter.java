package com.sujitha.expensemanagement.adapters;

import java.util.ArrayList;

import com.sujitha.expensemanagement.R;
import com.sujitha.expensemanagement.models.Expense;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ExpenseCatgDetailListAdapter extends BaseAdapter {

	private Context context;
	ArrayList<Expense> expenses = new ArrayList<Expense>();

	public ExpenseCatgDetailListAdapter(Context context) {
		this.context = context;
	}

	public void setData(ArrayList<Expense> expenses) {
		this.expenses = expenses;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return expenses.size();
	}

	@Override
	public Expense getItem(int position) {
		return expenses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getExpenseId();
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.listiem_expense_catg_detail_item, null);
		}
		Expense expense = getItem(position);
		TextView txtExpDesc = (TextView) convertView.findViewById(R.id.expDesc);
		txtExpDesc.setText(expense.getDescription());
		
		TextView txtExpAmount = (TextView) convertView.findViewById(R.id.expAmount);
		txtExpAmount.setText(expense.getAmount().toString());
		
		TextView txtExpDate = (TextView) convertView.findViewById(R.id.expDate);
		txtExpDate.setText(expense.getExpenseDate().toLocaleString());
		
		return convertView;
		
	}
	
}
	

