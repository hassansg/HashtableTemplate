/*Class for hashpair datatypes and their constructors to be used in other functions*/

public class HashPair<K,V> {
    private K key;
    private V value;
   
    public HashPair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }
    
    public void setValue(V value) {
        this.value = value;
    }
}