package thach.le;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

class ReflectionTest {

    @Test
    void testGetClass() throws ClassNotFoundException {
        final Class<?> stringUtils = Class.forName("org.apache.commons.lang3.StringUtils");
        assertThat(stringUtils).isNotNull();
    }

    @Test
    void testGetFields() throws ClassNotFoundException {
        final Class<?> stringUtils = Class.forName("org.apache.commons.lang3.StringUtils");
        final Field[] declaredFields = stringUtils.getDeclaredFields();
        assertThat(declaredFields).isNotEmpty()
                .hasSize(8);
    }

    @Test
    void testGetMethods() throws ClassNotFoundException {
        final Class<?> stringUtils = Class.forName("org.apache.commons.lang3.StringUtils");
        final Method[] declareMethods = stringUtils.getDeclaredMethods();
        assertThat(declareMethods).isNotEmpty()
                                  .hasSize(248);
    }

    @Test
    void testGetConstructors() throws ClassNotFoundException {
        final Class<?> stringUtils = Class.forName("org.apache.commons.lang3.StringUtils");
        final Constructor<?>[] constructors = stringUtils.getConstructors();
        assertThat(constructors).isNotEmpty()
                                  .hasSize(1);
    }
}
