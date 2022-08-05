package com.ssafy.backend.common.exception.handler;

public class DuplicateMemberException extends RuntimeException{
	public DuplicateMemberException(String msg) {
		super(msg);
	}
}
