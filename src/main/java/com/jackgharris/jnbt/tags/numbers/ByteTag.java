package com.jackgharris.jnbt.tags.numbers;

import com.jackgharris.jnbt.utils.ByteArray;
import com.jackgharris.jnbt.Tag;
import com.jackgharris.jnbt.utils.TagType;

import java.nio.ByteBuffer;

public class ByteTag extends Tag {

    private byte value;

    public ByteTag(String key, Number value) {
        super(key);
        this.value = value.byteValue();
    }

    public ByteTag(Number value){
        super("");
        this.value = value.byteValue();
    }

    public ByteTag(byte[] data){
        super(data);

        ByteArray bytes = new ByteArray(data);

        //Pop our tag type
        bytes.pop(0);

        byte[] keySize = bytes.getSubSection(0,2);
        short keySizeShort = ByteBuffer.wrap(keySize).getShort();

        //Pop our tag type
        bytes.pop(0);
        bytes.pop(0);

        if(keySizeShort != 0){
            byte[] key = bytes.getSubSection(0,keySizeShort);
            this.key = new String(key);

            for(int i = 0; i < this.key.length(); i ++){
                bytes.pop(0);
            }
        }

        this.value = bytes.get(0);
    }

    @Override
    public TagType getTypeId() {
        return TagType.BYTE;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        this.value = (byte) value;
    }


    @Override
    public byte[] write() {

        ByteArray bytes = new ByteArray();

        //Add our Tag Type
        bytes.addByte(TagType.BYTE.getId());

        //Add our key length
        bytes.addBytes(ByteBuffer.allocate(2).putShort((short)this.key.length()).array());

        if(!this.key.isEmpty()){
            //Add our key
            bytes.addBytes(this.key.getBytes());
        }

        //Add our value
        bytes.addByte(this.value);

        return bytes.get();
    }
}
