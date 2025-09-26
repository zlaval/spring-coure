package hu.zlaval.springcourse.order;

import com.chrylis.codec.base58.Base58UUID;

import java.util.UUID;

public class IDGenerator {

    public static String newId() {
        return new Base58UUID().encode(UUID.randomUUID());
    }

}
