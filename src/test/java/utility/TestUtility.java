package utility;

import java.util.HashSet;
import java.util.List;

/**
 * The Class TestUtility.
 */
public final class TestUtility {

    /**
     * List equals ignore order.
     *
     * @param       <T> the generic type
     * @param list1 the list 1
     * @param list2 the list 2
     * @return true, if successful
     */
    public static <T> boolean listEqualsIgnoreOrder(final List<T> list1,
            final List<T> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }
}
