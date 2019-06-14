package org.springplayground.trace.generator;

import java.util.UUID;

/**
 * This is the default ID generator that uses {@link UUID java.util.UUID} to generate IDs.
 */
public class DefaultIdGenerator implements IdGenerator {

    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
