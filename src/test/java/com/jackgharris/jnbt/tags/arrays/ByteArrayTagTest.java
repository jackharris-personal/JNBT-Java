package com.jackgharris.jnbt.tags.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByteArrayTagTest {

    @Test
    void write() {


        byte[] bytes = new byte[16];

        for(int i = 0; i < 16; i++){
            bytes[i] = (byte) (i+10);
        }

        ByteArrayTag tag = new ByteArrayTag("Byte test",bytes);

        byte[] data = tag.write();

        tag = new ByteArrayTag(data);

        byte[] newData = (byte[]) tag.getValue();

        for(int i = 0; i < 16; i++){
            Assertions.assertEquals(bytes[i],newData[i]);
        }
    }
}