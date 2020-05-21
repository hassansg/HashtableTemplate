import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{

    private int numEntries;
    private int numBuckets; 
    private static final double MAX_LOAD_FACTOR = 0.75;
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    //METHOD DONE
    public MyHashTable(int initialCapacity) {
        
    	numEntries = 0;		//Initializes the number of entries to zero
    	numBuckets = initialCapacity;		//Initializes the number of buckets to the initial capacity integer
    	buckets = new ArrayList<LinkedList<HashPair<K,V>>>(numBuckets);		//Creates a new hashmap with the correct number of buckets
    	
    	for(int i = 0; i < numBuckets; i++) buckets.add(new LinkedList<HashPair<K,V>>());		//Adds new entries to the hashmap iteratively
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public boolean isEmpty() {
        return this.numEntries == 0;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
   
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    
    //METHOD DONE
    public V put(K key, V value) {
        
    	int index = hashFunction(key);		//Index of key in the hashTable
    	V initialValue = null;				//Initializing the value to be returned
    	
    	
    	if(value == null) return null;		//If the key does not have a value, return null

        if(get(key) == null) {				//Handles the case where the key does not exist, adds a new hashpair with the
        									//key to the hashtable with the corresponding K and V
        	HashPair<K,V> tempPair = new HashPair<K,V>(key,value);
        	buckets.get(index).add(tempPair);
        	numEntries++;
        }

        else if(get(key) != null) {			//Handles the case where the key already exists and overwrites it
        	
        	for(HashPair<K,V> mainPair : buckets.get(index)) { 
    			
        		if(mainPair.getKey().equals(key)) {
        				
        				initialValue = mainPair.getValue();
        				mainPair.setValue(value); 
        				break;
        		}
        	}
        }
        
        double currentLoad = (double) numEntries / (double) numBuckets;		//Stores the current load on the hashtable
        if (currentLoad > MAX_LOAD_FACTOR) rehash();		//If the load exceeds the factor, rehash the table
        
    	return initialValue;
    }

    //METHOD DONE
    public V get(K key){
       
    	int indexValue = hashFunction(key);		//Retrieve the index of the key 
    	
    	//Iterates through the hashtable and returns the value of the corresponding key
    	for(HashPair<K,V> nodes : buckets.get(indexValue)) if(nodes.getKey().equals(key)) return nodes.getValue();
    	
    	//If key is not found, return null
    	return null;
    }
    

    //METHOD DONE
    public V remove(K key) {

    	V removedV = null;		//Initializes the value to be returned
    	int index = hashFunction(key);		//Finding the index of the input key
    	LinkedList<HashPair<K,V>> listToRemove = buckets.get(index);	//Finding the linkedlist which contains the hashpair to be removed

    	//Iterates through the linkedlist to find the hashpair to remove from the table
        for (int k = 0; k < listToRemove.size(); k++) {
        	
            if (listToRemove.get(k).getKey().equals(key)) {
            	
            	removedV = listToRemove.get(k).getValue();
                buckets.get(index).remove(k);
                numEntries--;
            }
        }

        return removedV;
    }
    
    //METHOD DONE
    public void rehash() {
  
    	LinkedList<HashPair<K, V>> i;	//new bucket
    	K keyAdded = null;				//key and value pair to be added to the new hashtable
    	V valueAdded = null;
    	
    	int newSize = numBuckets * 2;	//Size of hashtable doubles when rehashing
    	
    	//Initialize hashtable with newsize
    	MyHashTable<K,V> newHashTable = new MyHashTable<K,V>(newSize);
    	
    	//Iterate through the current hashtable
    	for(int j = 0; j < buckets.size(); j++) {
    		
    		//Buckets being initialized from old hashtable to compare
    		i = buckets.get(j);
    		
    		
    		for(HashPair<K,V> current : i) {
    			
    			//if the hashpair does not have a null key, then add it to the new hashtable
    			if(current.getKey() != null) {
    				
    				keyAdded = current.getKey();
    				valueAdded = get(keyAdded);
    				
    				//If the value exists then add the hashpair
    				if(valueAdded != null) newHashTable.put(keyAdded, valueAdded);
    			}
    		}
    	}
    	
    	//update the hashtable properties to reflect the rehashing process
    	this.buckets = newHashTable.buckets;
    	this.numEntries = newHashTable.numEntries;
    	this.numBuckets = newHashTable.numBuckets;
    }
    
    //METHOD DONE
    public ArrayList<K> keys() {
    	//Uses the iterator to traverse the hashtable and add the keys to an arrayList
    	
    	ArrayList<K> KeyList = new ArrayList<K>(numEntries);
    	MyHashIterator iKeys = iterator();

    	//while the table is still being iterated through,
    	//add the hashpair key to the arrayList 
        while(iKeys.hasNext()) KeyList.add(iKeys.next().getKey());
        
        return KeyList;
    }
    
    public ArrayList<V> values() {
    	
    	//Hashtable to compare values and integers to make sure there are no duolicate values being returned
    	MyHashTable<V, Integer> ValueInt = new MyHashTable<V, Integer>(numEntries);
    	ArrayList<V> values = new ArrayList<V>();
    	
    	for (int i=0; i< numBuckets; i++) {
    			
    			//If the bucket contains a value
    			if (buckets.get(i).size() != 0) {
    			
    			//iterate through the hashPairs in the bucket	
    			//This also makes sure there are no duplicate values being created
    			Iterator<HashPair<K,V>> iter = buckets.get(i).iterator();
    			
    			while (iter.hasNext() == true) {
    				
    				//Let the hashpair be the next hashpair in the iteration
    				HashPair<K,V> p = iter.next();
    				//Place the hashpair in the hashtable
    				ValueInt.put(p.getValue(), 0); 
    			}
    			
    		}
    		
    	}
    	
    	//Place the keys of the hashtable(which are our values) into the arrayList
    	values = ValueInt.keys(); 
    	
        return values;
    }
    
    @Override
    public MyHashIterator iterator() throws NoSuchElementException {
        return new MyHashIterator();
    }   
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {

    	LinkedList<HashPair<K,V>> PairValues = new LinkedList<HashPair<K,V>>();
        
    	
    
        private MyHashIterator() {
          
        	
            for(int i = 0 ; i < buckets.size(); i++){
              for(int j = 0; j < buckets.get(i).size(); j++){
                if(buckets.get(i).get(j) != null){
                  HashPair<K,V> entry = buckets.get(i).get(j);
                  PairValues.add(entry);
                }
              }
            }
           
        }
        
        @Override
     
        public boolean hasNext() {
        	
        	return !PairValues.isEmpty();
        }
        
        @Override
      
        public HashPair<K,V> next() {
        	if(hasNext() == false) throw new IndexOutOfBoundsException();
        	return PairValues.removeFirst();
        	
        }
        
    }
}