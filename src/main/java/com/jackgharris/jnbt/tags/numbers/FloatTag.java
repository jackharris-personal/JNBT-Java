package com.jackgharris.jnbt.tags.numbers;

import com.jackgharris.jnbt.utils.TagType;
import com.jackgharris.jnbt.utils.ByteArray;
import com.jackgharris.jnbt.Tag;

import java.nio.ByteBuffer;

public class FloatTag extends Tag {

    private float value;

    public FloatTag(String key) {
        super(key);
    }

    public FloatTag(String key, float value){
        super(key);
        this.value = value;
    }

    public FloatTag(float value)
    {
        super("");
        this.value = value;
    }

    public FloatTag(byte[] data){
        super("");

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

        this.value = ByteBuffer.wrap(bytes.getSubSection(0,bytes.size())).getFloat();
    }

    @Override
    public TagType getTypeId() {
        return TagType.FLOAT;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        this.value = (float)value;
    }

    @Override
    public byte[] write() {

        ByteArray byteArray = new ByteArray();
        byteArray.addByte(TagType.FLOAT.getId());

        byteArray.addBytes(ByteBuffer.allocate(2).putShort((short)this.key.length()).array());

        if(!this.key.isEmpty()){
            //Add our key
            byteArray.addBytes(this.key.getBytes());
        }

        byteArray.addBytes(ByteBuffer.allocate(4).putFloat(this.value).array());

        return byteArray.get();
    }
}
