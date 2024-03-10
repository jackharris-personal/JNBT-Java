package com.jackgharris.jnbt.tags;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringTagTest {

    @Test
    void write() {

        String string = "Hello world im a string!";

        StringTag tag = new StringTag(string);

        byte[] bytes = tag.write();

        tag = new StringTag(bytes);

        Assertions.assertEquals(string,tag.getValue());
    }
}