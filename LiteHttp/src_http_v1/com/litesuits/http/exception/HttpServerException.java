package com.litesuits.http.exception;

import com.litesuits.http.data.HttpStatus;

/**
 * exception that happen in server.
 * 
 * @author MaTianyu
 * 2014-1-2上午12:53:13
 */
public class HttpServerException extends HttpException {
	private static final long serialVersionUID = 3695887939006497385L;
	private ServerException exceptionType;

	public enum ServerException {
		ServerInner("Server Inner Exception", "服务器内部异常"),
		ServerReject("Server Reject Client Exception", "服务器拒绝或无法提供服务"),
		RedirectTooMany("Server RedirectTooMany", "重定向次数过多");

		public String reason;
		public String chiReason;

		ServerException(String name, String chiName) {
			this.reason = name;
			this.chiReason = chiName;
		}
	}

	public HttpServerException(ServerException e) {
		super(useChinese ? e.chiReason : e.reason);
		exceptionType = e;
	}

	public HttpServerException(HttpStatus status) {
		super(useChinese ? status.getDescriptionInChinese() : status.getDescription());
		if (status.getCode() >= 500) {
			exceptionType = ServerException.ServerInner;
		} else {
			exceptionType = ServerException.ServerReject;
		}
	}

	public ServerException getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(ServerException exceptionType) {
		this.exceptionType = exceptionType;
	}

}
