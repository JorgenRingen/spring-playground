package org.springplayground.trace.generator;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.lang.NonNull;

/**
 * <p>
 * This is a factory class to be used to create {@link IdGenerator} objects.
 * </p>
 * <p>
 * It also doubles as a Spring Factory Bean in order to create a Spring bean of type {@link IdGenerator}.
 * </p>
 */
public class IdGeneratorFactory extends AbstractFactoryBean<IdGenerator> {

    /**
     * This flag specifies if the generated ID should be a 128 bit or 64 bit ID.
     * It defaults to 64 bit.
     */
    @SuppressWarnings("WeakerAccess")
    public static boolean TRACE_ID_128 = false;

    public IdGeneratorFactory(final boolean traceId128) {
        TRACE_ID_128 = traceId128;
    }

    @Override
    public Class<?> getObjectType() {
        return IdGenerator.class;
    }

    @NonNull
    @Override
    protected IdGenerator createInstance() {
        return create();
    }

    /**
     * This static method is the factory method for creating {@link IdGenerator} objects.
     * <p>
     * It will instantiate an {@link SleuthIdGenerator} if {@link brave.internal.Platform brave.internal.Platform} is on the classpath.
     * Otherwise it will instantiate an {@link DefaultIdGenerator}.
     *
     * @return An implementation of {@link IdGenerator}.
     */
    public static IdGenerator create() {
        try {
            Class.forName("brave.internal.Platform");
            return new SleuthIdGenerator(TRACE_ID_128);
        } catch (final ClassNotFoundException e) {
            return new DefaultIdGenerator();
        }
    }
}
