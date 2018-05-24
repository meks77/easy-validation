package at.meks.validation.validations.date;

class Range<T> {

    private T min;
    private T max;

    static <V> Range<V> of(V min, V max) {
        Range<V> range = new Range<>();
        range.min = min;
        range.max = max;
        return range;
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }
}
