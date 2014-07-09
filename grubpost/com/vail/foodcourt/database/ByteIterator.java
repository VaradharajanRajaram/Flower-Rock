                                                                                                                                                                  
 package com.vail.foodcourt.database;


import java.util.Iterator;
import java.util.ArrayList;

public abstract class ByteIterator implements Iterator<Byte> {

	@Override
	public abstract boolean hasNext();

	@Override
	public Byte next() {
		throw new UnsupportedOperationException();
		//return nextByte();
	}

	public abstract byte nextByte();
        /** @return byte offset immediately after the last valid byte */
	public int nextBuf(byte[] buf, int buf_off) {
		int sz = buf_off;
		while(sz < buf.length && hasNext()) {
			buf[sz] = nextByte();
			sz++;
		}
		return sz;
	}

	public abstract long bytesLeft();

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/** Consumes remaining contents of this object, and returns them as a string. */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		while(this.hasNext()) { sb.append((char)nextByte()); }
		return sb.toString();
	}
	/** Consumes remaining contents of this object, and returns them as a byte array. */
	public byte[] toArray() {
	    long left = bytesLeft();
	    if(left != (int)left) { throw new ArrayIndexOutOfBoundsException("Too much data to fit in one array!"); }
	    byte[] ret = new byte[(int)left];
	    int off = 0;
	    while(off < ret.length) {
		off = nextBuf(ret, off);
	    }
	    return ret;
	}

}