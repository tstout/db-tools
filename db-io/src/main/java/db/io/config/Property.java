package db.io.config;

/**
 * This is an experiment...will probably go away. The original
 * motivation was a common base for class-to-instance maps.
 */
public interface Property<T> {
    T value();
}
