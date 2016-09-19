/**
 * 
 */
package com.app.stockmarket.exception;

/**
 * @author sramanna
 *
 */
public class InvalidStockException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8492179261485767507L;

	public enum ErrorCode {
		INVALID_STOCK_SYMBOL,
		INVALID_STOCK_QTY,
		INVALID_STOCK_PRICE;
	};
	
	private ErrorCode errorCode;

	public InvalidStockException(ErrorCode errorCode) {
		super();
		this.setErrorCode(errorCode);
	}

	/**
	 * @return the errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
