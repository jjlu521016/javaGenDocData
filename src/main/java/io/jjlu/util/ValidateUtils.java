package io.jjlu.util;

import com.sun.istack.internal.Nullable;

/**
 * @author lujie
 * @since 20191226
 */
public class ValidateUtils {

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

}
