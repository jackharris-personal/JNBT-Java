package com.jackgharris.jnbt.tags.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerTagTest {

    @Test
    void write() {

        int number = -3521;

        IntegerTag tag = new IntegerTag("My number",number);

        byte[] bytes = tag.write();

        tag = new IntegerTag(bytes);

        Assertions.assertEquals(number,tag.getValue());
    }
}