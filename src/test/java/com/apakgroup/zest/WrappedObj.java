package com.apakgroup.zest;

public class WrappedObj {

	private String b;

	private String e;

	public void a() {
		b = "NEW";
	}

	public String realB() {
		return b;
	}

	public String getB() {
		return b;
	}

	public void setB(final String b) {
		this.b = b;
	}

	public void c() {
		return;
	}

	public String d() {
		return "ABC";
	}

	public void setE(final String e) {
		this.e = e;
	}

	public String getE() {
		return e;
	}

}