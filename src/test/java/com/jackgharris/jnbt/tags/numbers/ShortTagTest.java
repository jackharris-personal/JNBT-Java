package com.jackgharris.jnbt.tags.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShortTagTest {

    @Test
    void write() {

        short number = Short.MAX_VALUE;

        ShortTag tag = new ShortTag(number);

        byte[] bytes = tag.write();

        tag = new ShortTag(bytes);

        Assertions.assertEquals(number,tag.getValue());
    }


}