# HashtableTemplate
Organizes critical functions for hashtable databases ordered by linked lists.

## Functions:
1. `put(K key, V value)` - Takes a key and value input and stores them in the hashtable. If the key does not exist in the hashtable, then a new one will be created. If the key already exists, then the value will be overwritten. Otherwise, if there is no key, then null will be returned. Also implements rehashing conditions of the hashtable.
2. `get(K key)` - Returns the value of the corresponding key. If the key is not found, null is returned.
3. `remove(K key)` - Removes the hashpair associated with the key inputted.
4. `rehash()` - Doubles the number of buckets in the hashtable to allow for new entries. It is used in the put function
5. `keys()` - returns an arraylist with all the keys in the hashtable, in order.
6. `values()` - returns an arraylist with all unique values in the hashtable, in order.
7. `iterator()` - Java iterator which can iterate through entries of the hashtable, used in other functions.

The `results.txt` file shows demonstrations of all the above in a functional hashtable.
