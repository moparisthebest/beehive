/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * $Header:$
 */
package org.apache.beehive.netui.util.internal;

import java.io.Serializable;
import java.lang.ref.SoftReference;


/**
 * Unsynchronized alternative to StringBuffer.
 */
public final class InternalStringBuilder
        implements Serializable
{
    private char _buffer[];
    private int _length = 0;

    static final long serialVersionUID = 1;

    public InternalStringBuilder()
    {
        this( 16 );
    }

    public InternalStringBuilder( int length )
    {
        _buffer = new char[length];
    }

    public InternalStringBuilder( String str )
    {
        this( str.length() + 16 );
        append( str );
    }

    public int length()
    {
        return _length;
    }

    public void ensureCapacity( int minCapacity )
    {
        int maxCapacity = _buffer.length;

        if ( minCapacity > maxCapacity )
        {
            int newCapacity = ( maxCapacity + 1 ) * 2;
            if ( newCapacity < 0 ) newCapacity = Integer.MAX_VALUE;
            else if ( minCapacity > newCapacity ) newCapacity = minCapacity;
            // allow _buffer to be GCd if needed, rather than throw OOM
            final SoftReference<char[]> softBuffer = new SoftReference<char[]>(_buffer);
            _buffer = null;
            final char newValue[] = new char[newCapacity];
            _buffer = softBuffer.get();
            if (_buffer == null)
                throw new OutOfMemoryError("could not increase capacity in InternalStringBuilder");
            System.arraycopy( _buffer, 0, newValue, 0, _length );
            _buffer = newValue;
        }
    }

    public void setLength( int length )
    {
        if ( length < 0 ) throw new StringIndexOutOfBoundsException( length );
        ensureCapacity( length );

        if ( _length < length )
        {
            while ( _length < length )
            {
                _buffer[_length++] = '\0';
            }
        }
        _length = length;
    }

    public char charAt( int index )
    {
        if ( index < 0 || index >= _length ) throw new StringIndexOutOfBoundsException( index );
        return _buffer[index];
    }

    public InternalStringBuilder append( Object obj )
    {
        return append( String.valueOf( obj ) );
    }

    public InternalStringBuilder append( String str )
    {
        if ( str == null ) str = String.valueOf( str );
        int len = str.length();
        ensureCapacity( _length + len );
        str.getChars( 0, len, _buffer, _length );
        _length += len;
        return this;
    }

    public InternalStringBuilder append( char c )
    {
        ensureCapacity( _length + 1 );
        _buffer[_length++] = c;
        return this;
    }

    public InternalStringBuilder append( int i )
    {
        return append( String.valueOf( i ) );
    }

    public String toString()
    {
        return new String( _buffer, 0, _length );
    }

    public InternalStringBuilder deleteCharAt( int index )
    {
        if ( index < 0 || index >= _length ) throw new StringIndexOutOfBoundsException( index );
        System.arraycopy( _buffer, index + 1, _buffer, index, _length - index - 1 );
        _length--;
        return this;
    }
}
