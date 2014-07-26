package com.sujitha.expensemanagement.models;

public class ExpenseCategory {
	private int expCatgId;
	private String expCatgName;

	public int getExpCatgId() {
		return expCatgId;
	}

	public String getExpCatgName() {
		return expCatgName;
	}

	public ExpenseCategory(int expCatgId, String expCatgName) {
		super();
		this.expCatgId = expCatgId;
		this.expCatgName = expCatgName;
	}
}
