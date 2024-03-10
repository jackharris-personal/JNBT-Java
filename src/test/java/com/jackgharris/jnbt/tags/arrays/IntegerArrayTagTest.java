package com.jackgharris.jnbt.tags.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerArrayTagTest {

    @Test
    void write() {


        int[] integers = new int[16];

        for(int i = 0; i < 16; i++){
          integers[i] = Integer.MAX_VALUE-i;
        }

        IntegerArrayTag tag = new IntegerArrayTag();
        tag.setValue(integers);

        byte[] data = tag.write();

        tag = new IntegerArrayTag(data);

        int[] newIntegers = (int[]) tag.getValue();

        for(int i = 0; i < 16; i++){
            Assertions.assertEquals(integers[i],newIntegers[i]);
        }
    }
}