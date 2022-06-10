package com.zavadski.dao.exception;

public class NoAccess extends IllegalArgumentException  {
    public NoAccess(String description) {
        super(description);
    }
}
