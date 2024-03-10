package com.jackgharris.jnbt.tags.numbers;

import com.jackgharris.jnbt.Tag;
import com.jackgharris.jnbt.utils.TagType;
import com.jackgharris.jnbt.utils.ByteArray;

import java.nio.ByteBuffer;

public class DoubleTag extends Tag {
    private double value;

    public DoubleTag(String key) {
        super(key);
    }

    public DoubleTag(String key, double value){
        super(key);
        this.value =value;
    }

    public DoubleTag(double value){
        super("");
        this.value = value;
    }

    public DoubleTag(byte[] data){
        super("");

        ByteArray bytes = new ByteArray(data);

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

        this.value = ByteBuffer.wrap(bytes.getSubSection(0,bytes.size())).getDouble();

    }

    @Override
    public TagType getTypeId() {
        return TagType.DOUBLE;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        this.value = (double)value;
    }

    @Override
    public byte[] write() {

        ByteArray byteArray = new ByteArray();

        byteArray.addByte(TagType.DOUBLE.getId());

        byteArray.addBytes(ByteBuffer.allocate(2).putShort((short)this.key.length()).array());

        if(!this.key.isEmpty()){
            //Add our key
            byteArray.addBytes(this.key.getBytes());
        }

        byteArray.addBytes(ByteBuffer.allocate(8).putDouble(this.value).array());

        return byteArray.get();
    }
}
