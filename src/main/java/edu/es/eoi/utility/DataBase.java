package edu.es.eoi.utility;

public enum DataBase {
	URL("jdbc:mysql://localhost:3306/eoi?serverTimezone=UTC" + "&useSSL=false"),
	USER("root"),
	PASS("1234");
	
	private String data;

	private DataBase(String data) {
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
}
