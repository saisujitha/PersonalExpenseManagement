package com.sujitha.expensemanagement.adapters;

import java.util.ArrayList;

import com.sujitha.expensemanagement.data.DataAccessLayer;
import com.sujitha.expensemanagement.models.ExpenseCategory;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class ExpenseCategoriesTypeSpinnerAdapter implements SpinnerAdapter {

	Context context;
	ArrayList<ExpenseCategory> expenseCategories;

	public ExpenseCategoriesTypeSpinnerAdapter(Context context) {
		this.context = context;
		expenseCategories = DataAccessLayer.getInstance(context).getExpensesCategories();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return expenseCategories.size();
	}

	@Override
	public ExpenseCategory getItem(int position) {
		// TODO Auto-generated method stub
		return expenseCategories.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	// Note:-Create this two method getIDFromIndex and getIndexByID
	public int getIDFromIndex(int Index) {
		return expenseCategories.get(Index).getExpCatgId();
	}

	public int getIndexByID(int ID) {
		for (int i = 0; i < expenseCategories.size(); i++) {
			if (expenseCategories.get(i).getExpCatgId() == ID) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		TextView textview = (TextView) inflater.inflate(android.R.layout.simple_dropdown_item_1line, null);
		textview.setText(expenseCategories.get(position).getExpCatgName());
		textview.setTextColor(Color.BLACK);
		textview.setPadding(15, 15, 15, 15);
		return textview;
	}

	@Override
	public int getViewTypeCount() {
		return android.R.layout.simple_spinner_item;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		TextView textview = (TextView) inflater.inflate(android.R.layout.simple_dropdown_item_1line, null);
		textview.setText(expenseCategories.get(position).getExpCatgName());
		textview.setTextColor(Color.BLACK);
		textview.setPadding(15, 15, 15, 15);
		return textview;
	}
}
