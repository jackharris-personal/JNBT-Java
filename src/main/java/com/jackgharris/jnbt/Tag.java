package com.jackgharris.jnbt;

import com.jackgharris.jnbt.utils.TagType;

public abstract class Tag {

    protected String key = "";

    public static final byte TAG_END = 0;
    public static final byte TAG_BYTE = 1;
    public static final byte TAG_SHORT = 2;
    public static final byte TAG_INT = 3;
    public static final byte TAG_LONG = 4;
    public static final byte TAG_FLOAT = 5;
    public static final byte TAG_DOUBLE = 6;
    public static final byte TAG_BYTE_ARRAY = 7;
    public static final byte TAG_STRING = 8;
    public static final byte TAG_LIST = 9;
    public static final byte TAG_COMPOUND = 10;
    public static final byte TAG_INT_ARRAY = 11;
    public static final byte TAG_FLOAT_ARRAY= 12;
    public static final byte TAG_SHORT_ARRAY= 13;
    public static final byte TAG_LONG_ARRAY = 14;
    public static final byte TAG_DOUBLE_ARRAY = 15;

    public Tag(String key){
        this.key = key;
    }

    public Tag(byte[] data){}

    public String getKey(){
        return this.key;
    }

    public void removeKey(){
        this.key = "";
    }

    /**
     * @return getTypeID returns the current type
     * matching the instance of this tag.
     */
    public abstract TagType getTypeId();

    /**
     * @return getValue returns the current value
     * as a java object for the current tag, note
     * that compound, lists, byte arrays, integer arrays
     * and float arrays will also return as they have
     * an array of children not a set single value.
     */
    public abstract Object getValue();

    /**
     * @param value accepts a java object or primitive
     *              and will set the current value to the
     *              value provided, Note: Providing the wrong
     *              type will cause a cast error.
     */
    public abstract void setValue(Object value);

    /**
     * @return write() will write all the object data
     * to an array of bytes matching the specification
     * and return it.
     */
    public abstract byte[] write();

    @Override
    public String toString(){
        String output;

        if(this.key.isEmpty()){
            output = this.getClass().getSimpleName()+": "+this.getValue();
        }else{
            output =this.getClass().getSimpleName()+"('"+this.key+"'): "+this.getValue();
        }
        return output;
    }

}
