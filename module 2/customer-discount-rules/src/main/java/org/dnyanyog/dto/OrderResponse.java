package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

@Component
public class OrderResponse {

	private String status;

	private String message;

	private int price;

	private int afterDiscountPrice;

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAfterDiscountPrice() {
		return afterDiscountPrice;
	}

	public void setAfterDiscountPrice(int afterDiscountPrice) {
		this.afterDiscountPrice = afterDiscountPrice;
	}


}
