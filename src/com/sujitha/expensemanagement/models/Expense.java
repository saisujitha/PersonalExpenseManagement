package com.sujitha.expensemanagement.models;

import java.util.Date;

public class Expense {
	private Integer expenseId;
	private String Description;
	private Float amount;
	private ExpenseCategory expenseCategory;
	private Date expenseDate;

	public Expense(Integer expenseId, String description, Float amount, ExpenseCategory expenseCategory, Date expenseDate) {
		super();
		this.expenseId = expenseId;
		Description = description;
		this.amount = amount;
		this.expenseCategory = expenseCategory;
		this.expenseDate = expenseDate;
	}

	public Integer getExpenseId() {
		return expenseId;
	}

	public String getDescription() {
		return Description;
	}

	public Float getAmount() {
		return amount;
	}

	public ExpenseCategory getExpenseCategory() {
		return expenseCategory;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}
}
