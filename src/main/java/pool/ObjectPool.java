package pool;

import java.util.Enumeration;
import java.util.Hashtable;

// TODO Auto-generated Javadoc

/**
 * Object Pool Design Pattern Questa è una classe astratta che definisce
 * l'interfaccia di un ObjectPool. Vanno implementati i metodi {@link #create()}
 * e {@link #destroy(Object)}
 *
 * @param <T> È la classe dell'oggetto di cui si vuole realizzare un pool
 * @author Andrea Mennillo
 */
public abstract class ObjectPool<T> {

    /**
     * The Constant DEFAULT_DEADTIME.
     */
    public static final long DEFAULT_DEADTIME = 50000;

    /** The dead time. */
    private long deadTime;

    /** The unlock. */
    private final Hashtable<T, Long> lock, unlock;

    /**
     * Instantiates a new object pool.
     */
    ObjectPool() {
        deadTime = DEFAULT_DEADTIME;
        lock = new Hashtable<T, Long>();
        unlock = new Hashtable<T, Long>();
    }

    /**
     * Creates the.
     *
     * @return the t
     */
    abstract T create();

    /**
     * Validate.
     *
     * @param o the o
     * @return true, if successful
     */
    abstract boolean validate(T o);

    /**
     * Destroy.
     *
     * @param o the o
     */
    abstract void destroy(T o);

    /**
     * Destroy unlocked.
     */
    public final synchronized void destroyUnlocked() {
        T t;
        if (unlock.size() > 0) {
            final Enumeration<T> e = unlock.keys();
            while (e.hasMoreElements()) {
                t = e.nextElement();
                unlock.remove(t);
                destroy(t);
                t = null;
            }
        }

        // Could be very dangerous
        // for(T t: lock.keySet()){
        // lock.remove(t);
        // dead(t);
        // }
    }

    /**
     * Take out.
     *
     * @return the t
     */
    public final synchronized T takeOut() {
        final long now = System.currentTimeMillis();
        T t;
        if (unlock.size() > 0) {
            final Enumeration<T> e = unlock.keys();
            while (e.hasMoreElements()) {
                t = e.nextElement();
                if ((now - unlock.get(t)) > deadTime) {
                    // object has dead
                    unlock.remove(t);
                    destroy(t);
                    t = null;
                } else {
                    if (validate(t)) {
                        unlock.remove(t);
                        lock.put(t, now);
                        return (t);
                    } else {
                        // object failed validation
                        unlock.remove(t);
                        destroy(t);
                        t = null;
                    }
                }
            }
        }
        // no objects available, create a new one
        t = create();
        if (t != null) {
            lock.put(t, now);
        }
        return (t);
    }

    /**
     * Take in.
     *
     * @param t the t
     */
    public final synchronized void takeIn(final T t) {
        lock.remove(t);
        unlock.put(t, System.currentTimeMillis());
    }

    /**
     * Sets the deadtime.
     *
     * @param deadtime the new deadtime
     */
    public final void setDeadtime(final long deadtime) {
        this.deadTime = deadtime;
    }
}
