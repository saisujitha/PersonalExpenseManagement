package com.sujitha.expensemanagement.adapters;

import java.sql.Date;
import java.util.ArrayList;
import com.sujitha.expensemanagement.R;
import com.sujitha.expensemanagement.data.DataAccessLayer;
import com.sujitha.expensemanagement.models.ExpenseCategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ExpenseCatgListAdapter extends BaseAdapter {

	Date StartDate = null, EndDate = null;
	private Context Context;

	ArrayList<ExpenseCategory> expenseCategories = null;

	public ExpenseCatgListAdapter(Date startDate, Date endDate, Context context) {
		StartDate = startDate;
		EndDate = endDate;
		Context = context;
		expenseCategories = DataAccessLayer.getInstance(context).getExpensesCategories();
	}

	@Override
	public int getCount() {
		return expenseCategories.size();
	}

	@Override
	public ExpenseCategory getItem(int position) {
		return expenseCategories.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(Context).inflate(R.layout.listitem_expense_catg_list, null);
		}
		TextView txtCatgName = (TextView) convertView.findViewById(R.id.catgName);
		txtCatgName.setText(getItem(position).getExpCatgName());
		
		TextView catgExpense = (TextView) convertView.findViewById(R.id.expenseAmount);
		catgExpense.setText(DataAccessLayer.getInstance(Context).getExpenseForCategory(position)+"");
		return convertView;
	}

}
