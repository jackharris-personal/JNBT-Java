package com.jackgharris.jnbt.tags.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ByteTagTest {


    @Test
    void write() {


        ByteTag tag = new ByteTag(13);

        byte[] bytes = tag.write();

        tag = new ByteTag(bytes);

        Assertions.assertEquals(13,(byte)tag.getValue());

    }
}