package com.jackgharris.jnbt.utils;

public class ByteArray {


    private byte[] data;

    public ByteArray(){
        this.data = new byte[0];
    }

    public ByteArray(byte[] bytes){
        this.data = bytes;
    }


    public void addByte(byte value){
        this.expandArray(1);
        this.data[this.data.length-1] = value;
    }

    public void pop(int index){
        byte[] newData = new byte[this.data.length-1];

        int i = 0;
        int ii = 0;
        while(i < this.data.length){

            if(i != index){
                newData[ii] = this.data[i];
                ii++;
            }

            i++;
        }

        this.data = newData;
    }

    /**
     * @param start Specifies the starting index that we will start removing from.
     * @param count Specifies inclusively how many index from the start we will remove.
     */
    public void pop(int start, int count){
        byte[] newData = new byte[this.data.length-count];

        int i = 0;
        int ii = 0;
        while(i < this.data.length){

            if(i < start || i >= start+count){
                newData[ii] = this.data[i];
                ii++;
            }

            i++;
        }

        this.data = newData;
    }

    public byte[] getSubSection(int start, int count){

        int end = start+count;
        byte[] output = new byte[count];

        int i = 0;
        while(start < end){
            output[i] = this.data[start];

            i++;
            start++;
        }

        return output;
    }

    public byte get(int index){
        return this.data[index];
    }

    public void addBytes(byte[] bytes){
        int i = 0;
        int startingLength = this.data.length;
        int ii = startingLength;

        this.expandArray(bytes.length);

        while(ii<this.data.length){
            this.data[i+startingLength] = bytes[i];
            i++;
            ii++;
        }
    }

    public byte[] get(){
        return this.data;
    }

    public int size(){
        return this.data.length;
    }

    private void expandArray(int size){

        byte[] newData = new byte[this.data.length+size];

        int i = 0;
        while(i < data.length){
            newData[i] = this.data[i];
            i++;
        }

        this.data = newData;
    }

}
