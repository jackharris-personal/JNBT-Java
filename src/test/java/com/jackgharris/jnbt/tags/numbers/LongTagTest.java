package com.jackgharris.jnbt.tags.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongTagTest {

    @Test
    void write() {

        long number = Long.MIN_VALUE;

        LongTag tag = new LongTag(number);

        byte[] bytes = tag.write();

        tag = new LongTag(bytes);

        Assertions.assertEquals(number,tag.getValue());
    }

}