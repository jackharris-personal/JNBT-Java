package com.jackgharris.jnbt.tags;


import com.jackgharris.jnbt.tags.arrays.ListTag;
import com.jackgharris.jnbt.tags.numbers.ByteTag;
import com.jackgharris.jnbt.tags.numbers.LongTag;
import com.jackgharris.jnbt.utils.TagType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class CompoundTagTest {

    @Test
    void write() {

        CompoundTag root = new CompoundTag();

        CompoundTag user = new CompoundTag("user");

            StringTag name = new StringTag("name", "Jack Harris");
            ByteTag age = new ByteTag("age", 26);
            LongTag registrationDate = new LongTag("registration_date", Instant.now().toEpochMilli());

            user.addChild(name);
            user.addChild(age);
            user.addChild(registrationDate);

        root.addChild(user);


        ListTag servers = new ListTag("websites", TagType.COMPOUND);

            CompoundTag s1 = new CompoundTag();
            s1.addChild(new StringTag("name","Google"));
            s1.addChild(new StringTag("address","127.0.0.1"));
            s1.addChild(new LongTag("last_accessed", Instant.now().toEpochMilli()));


            CompoundTag s2 = new CompoundTag();
            s2.addChild(new StringTag("name","Github"));
            s2.addChild(new StringTag("address","192.168.0.1"));
            s2.addChild(new LongTag("last_accessed", Instant.now().toEpochMilli()-25000));

        servers.addChild(s1);
        servers.addChild(s2);

        root.addChild(servers);

        byte[] bytes = root.write();

        CompoundTag newRoot = new CompoundTag(bytes);

        Assertions.assertEquals(root.toString(),newRoot.toString());
    }

}