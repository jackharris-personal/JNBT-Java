package com.jackgharris.jnbt.tags;


import com.jackgharris.jnbt.utils.ByteArray;
import com.jackgharris.jnbt.Tag;
import com.jackgharris.jnbt.utils.TagType;

import java.nio.ByteBuffer;

public class StringTag extends Tag {

    String value;

    public StringTag(String key, String value) {
        super(key);
        this.value = value.trim();
    }

    public StringTag(){
        super("");
    }

    public StringTag(String value){
        super("");
        this.value= value;
    }

    public StringTag(byte[] data){
        super(data);

        ByteArray bytes = new ByteArray(data);

        //Pop our tag type
        bytes.pop(0);

        byte[] keySize = bytes.getSubSection(0,2);
        short keySizeShort = ByteBuffer.wrap(keySize).getShort();

        //Pop our tag type
        bytes.pop(0);
        bytes.pop(0);

        if(keySizeShort != 0) {
            byte[] key = bytes.getSubSection(0, keySizeShort);
            this.key = new String(key);

            for (int i = 0; i < this.key.length(); i++) {
                bytes.pop(0);
            }
        }

        //Get value size;
        byte[] valueSize = bytes.getSubSection(0,2);
        short valueSizeShort = ByteBuffer.wrap(valueSize).getShort();

        bytes.pop(0);
        bytes.pop(0);

        this.value = new String(bytes.getSubSection(0,valueSizeShort));
    }

    @Override
    public TagType getTypeId() {
        return TagType.STRING;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value.toString().trim();
    }
    
    @Override
    public byte[] write() {
        ByteArray bytes = new ByteArray();

        //Add our Tag Type
        bytes.addByte(TagType.STRING.getId());

        //Add our key length
        bytes.addBytes(ByteBuffer.allocate(2).putShort((short)this.key.length()).array());

        if(!this.key.isEmpty()){
            //Add our key
            bytes.addBytes(this.key.getBytes());
        }

        //Add our value length
        bytes.addBytes(ByteBuffer.allocate(2).putShort((short)this.value.length()).array());

        //Add our value
        bytes.addBytes(this.value.getBytes());

        return bytes.get();
    }
}
