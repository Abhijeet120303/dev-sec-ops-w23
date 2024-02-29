package org.dnyanyog.dto;

public class DiscountResponse {
	
	private String status;
	
	private String message;
	
	private int afterDiscountPrice;
	
	


	public int getAfterDiscountPrice() {
		return afterDiscountPrice;
	}

	public void setAfterDiscountPrice(int price) {
		this.afterDiscountPrice = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

	

}
