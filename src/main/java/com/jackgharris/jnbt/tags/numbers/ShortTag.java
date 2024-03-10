package com.jackgharris.jnbt.tags.numbers;

import com.jackgharris.jnbt.Tag;
import com.jackgharris.jnbt.utils.TagType;
import com.jackgharris.jnbt.utils.ByteArray;

import java.nio.ByteBuffer;

public class ShortTag extends Tag {

    private short value;

    public ShortTag(String key) {
        super(key);
    }

    public ShortTag(String key, short value){
        super(key);
        this.value = value;
    }

    public ShortTag(short value){
        super("");
        this.value = value;
    }

    public ShortTag(byte[] data){
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

        this.value = ByteBuffer.wrap(bytes.getSubSection(0,bytes.size())).getShort();
    }

    @Override
    public TagType getTypeId() {
        return TagType.SHORT;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        this.value = (short) value;
    }

    @Override
    public byte[] write() {
        ByteArray bytes = new ByteArray();

        //Add our Tag Type
        bytes.addByte(TagType.SHORT.getId());

        //Add our key length
        bytes.addBytes(ByteBuffer.allocate(2).putShort((short)this.key.length()).array());

        if(!this.key.isEmpty()){
            //Add our key
            bytes.addBytes(this.key.getBytes());
        }
        //Add our value
        bytes.addBytes(ByteBuffer.allocate(2).putShort(this.value).array());


        return bytes.get();
    }
}
