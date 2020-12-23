import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int INITIAL_SIZE = 16;
    private static final double LOAD_FACTOR = 0.75;
    private int size;
    private double loadFactor;
    private BucketEntity<K, V>[] buckets;

    private class BucketEntity<K, V> {
        private K key;
        private V value;
        private BucketEntity<K, V> next;
        private int hashCode;

        public BucketEntity(K k, V v, BucketEntity<K, V> next, int hashCode) {
            this.key = k;
            this.value = v;
            this.next = next;
            this.hashCode = hashCode;
        }

        public int getHashCode() {
            return this.hashCode;
        }

        public void setHashCode(int hashCode) {
            this.hashCode = hashCode;
        }

        public K getKey() {
            return this.key;
        }

        public void setKey(K k) {
            this.key = k;
        }

        public V getValue() {
            return this.value;
        }

        public void setValue(V v) {
            this.value = v;
        }

        public BucketEntity<K, V> getNext() {
            return this.next;
        }

        public void setNext(BucketEntity<K, V> next) {
            this.next = next;
        }
    }

    public MyHashMap() {
        buckets = new BucketEntity[INITIAL_SIZE];
        size = 0;
        loadFactor = LOAD_FACTOR;
    }

    public MyHashMap(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException();
        }
        buckets = new BucketEntity[initialSize];
        size = 0;
        loadFactor = LOAD_FACTOR;
    }

    public MyHashMap(int initialSize, double loadFactor) {
        if (initialSize < 1 || loadFactor < 0.0) {
            throw new IllegalArgumentException();
        }
        buckets = new BucketEntity[initialSize];
        size = 0;
        this.loadFactor = loadFactor;
    }

    @Override
    /** Removes all of the mappings from this map. */
    public void clear(){
        buckets = new BucketEntity[buckets.length];
        size = 0;
    }
    @Override
    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }
    @Override
    /** Returns the value to which the specified key is mapped, or null.*/
    public V get(K key){
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int h = hash(key, buckets.length);
        BucketEntity<K, V> bucket = get(key, h);
        return bucket == null ? null : bucket.getValue();
    }
    private int hash(K key, int length) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int h = (key.hashCode() & 0x7fffffff) % length;
        return h;
    }
    private BucketEntity<K, V> get(K key, int hashCode) {
        BucketEntity<K, V> entity = buckets[hashCode];
        while (entity != null) {
            if (entity.getHashCode() == hashCode && entity.getKey().equals(key)) {
                return entity;
            }
            entity = entity.getNext();
        }
        return null;
    }
    @Override
    /** Returns the number of key-value mappings in this map. */
    public int size(){
        return this.size;
    }
    @Override
    /** Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.*/
    public void put(K key, V value){
        int h = hash(key, buckets.length);
        BucketEntity<K, V> entity = get(key, h);
        while (entity != null) {
            if (entity.getHashCode() == h && entity.getKey().equals(key)) {
                entity.setValue(value);
                return;
            }
            entity = entity.getNext();
        }
        put(key, value, h);
    }
    private void put(K key, V value, int hashCode) {
        BucketEntity<K, V> entity = new BucketEntity<>(key, value, buckets[hashCode], hashCode);
        buckets[hashCode] = entity;
        size += 1;
        if (size >= buckets.length * loadFactor) {
            resize(buckets.length * 2);
        }
    }
    private void resize(int newLength) {
        BucketEntity<K, V>[] newBuckets = new BucketEntity[newLength];
        for (int i=0; i < buckets.length; i++) {
            BucketEntity<K, V> entity = buckets[i];
            while (entity != null) {
                BucketEntity<K, V> prevNext = entity.getNext();
                int newHashCode = hash(entity.getKey(), newLength);
                entity.setHashCode(newHashCode);
                entity.setNext(newBuckets[newHashCode]);
                newBuckets[newHashCode] = entity;
                entity = prevNext;
            }
        }
        buckets = newBuckets;
    }
    @Override
    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        Set<K> keySet = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            BucketEntity<K, V> entity = buckets[i];
            while (entity != null) {
                keySet.add(entity.getKey());
                entity = entity.getNext();
            }
        }
        return keySet;
    }
    @Override
    public Iterator<K> iterator(){
        return keySet().iterator();
    }
    @Override
    /** Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an UnsupportedOperationException.*/
    public V remove(K key){
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int h = hash(key, buckets.length);
        return remove(key, h);
    }
    private V remove(K key, int hashCode) {
        BucketEntity<K, V> entity = buckets[hashCode];
        BucketEntity<K, V> nextEntity = entity.getNext();
        if (entity.getKey().equals(key)) {
            V toRemove = entity.getValue();
            buckets[hashCode] = nextEntity;
            size -= 1;
            return toRemove;
        } else {
            while (!nextEntity.getKey().equals(key)){
                entity = nextEntity;
                nextEntity = nextEntity.getNext();
            }
            V toRemove = nextEntity.getValue();
            entity.setNext(nextEntity.getNext());
            size -= 1;
            return toRemove;
        }
    }
    @Override
    /** Removes the entry for the specified key only if it is currently mapped to the specified value. */
    public V remove(K key, V value){
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int h = hash(key, buckets.length);
        return remove(key, value, h);
    }
    private V remove(K key, V value, int hashCode) {
        BucketEntity<K, V> entity = buckets[hashCode];
        BucketEntity<K, V> nextEntity = entity.getNext();
        if (entity.getKey().equals(key) && entity.getValue().equals(value)) {
            V toRemove = entity.getValue();
            buckets[hashCode] = nextEntity;
            size -= 1;
            return toRemove;
        } else {
            while (!nextEntity.getKey().equals(key) || !nextEntity.getValue().equals(value)) {
                entity = nextEntity;
                nextEntity = nextEntity.getNext();
            }
            V toRemove = nextEntity.getValue();
            entity.setNext(nextEntity.getNext());
            size -= 1;
            return toRemove;
        }
    }


}
