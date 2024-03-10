package com.jackgharris.jnbt.tags.arrays;

import com.jackgharris.jnbt.Tag;
import com.jackgharris.jnbt.utils.TagType;
import com.jackgharris.jnbt.utils.ByteArray;

import java.nio.ByteBuffer;

public class ByteArrayTag extends Tag {

    private byte[] value = new byte[0];

    public ByteArrayTag(String key) {
        super(key);
    }

    public ByteArrayTag() {
        super("");
    }

    public ByteArrayTag(byte[] data){
        super("");

        ByteArray bytes = new ByteArray(data);

        bytes.pop(0);


        byte[] keySize = bytes.getSubSection(0,2);
        short keySizeShort = ByteBuffer.wrap(keySize).getShort();

        bytes.pop(0,2);

        if(keySizeShort != 0) {
            byte[] key = bytes.getSubSection(0, keySizeShort);
            this.key = new String(key);
            bytes.pop(0,keySizeShort);
        }

        //We also know the next four bytes detail the total length,
        //so we can ignore them and pop them off, this should leave us
        //with the total size remaining.
        bytes.pop(0,4);

        this.value = bytes.get();
    }



    public ByteArrayTag(String key, byte[] value){
        super(key);
        this.value = value;
    }

    @Override
    public TagType getTypeId() {
        return TagType.BYTE_ARRAY;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        this.value = (byte[])value;
    }

    @Override
    public byte[] write() {

        ByteArray bytes = new ByteArray();
        bytes.addByte(TagType.BYTE_ARRAY.getId());

        //Next we allocate the next two bytes to the length of the key
        bytes.addBytes(ByteBuffer.allocate(2).putShort((short)this.key.length()).array());

        //Provided the key is not empty we then add the key.
        if(!this.key.isEmpty()){
            bytes.addBytes(this.key.getBytes());
        }

        //Finally we then allocate 4 bytes to our size and then specify how large the size
        //of the child array is.
        bytes.addBytes(ByteBuffer.allocate(4).putInt(this.value.length).array());

        if(this.value.length > 0){
            bytes.addBytes(this.value);
        }

        return bytes.get();
    }

    @Override
    public String toString(){

        String output = "";

        if(this.key.isEmpty()) {
            output += this.getClass().getSimpleName()+": ";
        }else{
            output += this.getClass().getSimpleName() + "('" + this.key + "'): ";
        }

        output+="\n    [";

        for(int i = 0; i< this.value.length; i++){
            output += "\n    "+this.value[i];
            if(i != this.value.length){
                output +=",";
            }
        }

        return output += "\n    ]";
    }
}
