package com.jackgharris.jnbt.tags.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloatTagTest {

    @Test
    void write() {

        float number = 3.14152F;

        FloatTag tag = new FloatTag(number);

        byte[] bytes = tag.write();

        tag = new FloatTag(bytes);

        Assertions.assertEquals(number,tag.getValue());
    }
}