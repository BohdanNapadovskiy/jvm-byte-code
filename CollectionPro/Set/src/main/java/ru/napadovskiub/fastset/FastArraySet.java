package ru.napadovskiub.fastset;

import ru.napadovskiub.arrayset.SimpleArraySet;

/**
 * Class extends Simple array set.
 * and override method check element.
 * @param <T> generic.
 */
public class FastArraySet<T> extends SimpleArraySet<T> {


    /**
     * Method check element in array.
     * @param t element for check.
     * @return result.
     */
    @Override
    public boolean checkElement(T t) {

        int lower = 0;
        int higher = this.getSize() - 1;
        int current;
        while (true) {
            if (higher <= 0) {
                return true;
            } else if (this.getElement(0) == t) {
                return false;
            }
            current = (lower + higher) / 2;
            if (this.getElement(current) == null) {
                higher = current - 1;
                continue;
            }
            if (this.getElement(current).hashCode() == t.hashCode()) {
                return false;
            } else if (lower > higher) {
                return true;
            } else {
                if (this.getElement(current).hashCode() < t.hashCode()) {
                    lower = current + 1;
                } else {
                    higher = current - 1;
                }
            }
        }
    }
}
