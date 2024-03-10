package com.jackgharris.jnbt.tags.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleTagTest {

    @Test
    void write() {

        double number = Double.MAX_VALUE;

        DoubleTag tag = new DoubleTag(number);

        byte[] data = tag.write();

        tag = new DoubleTag(data);

        Assertions.assertEquals(tag.getValue(), number);

    }

}