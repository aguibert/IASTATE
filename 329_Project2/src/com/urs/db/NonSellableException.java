/**
 * 
 */
package com.urs.db;

/**
 * @author Andrew
 */
public class NonSellableException extends RuntimeException {

    public NonSellableException(String string) {
        super(string);
    }

    private static final long serialVersionUID = 1L;

}
