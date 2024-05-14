package hashtable;

public class MyHashTable<K, V> {
    /**
     * HashNode class for the MyHashTable class
     * It is a class for storing a key-value pair
     *
     * @param <K> Any immutable type that implements Objects.hashCode() method.
     * @param <V> Any object.
     */
    private static class HashNode<K, V> {
        final K key;
        V value;
        HashNode<K, V> next;

        HashNode(K key, V value) {
            this.key = key;
            this.value = value;

            this.next = null;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private static final int DEFAULT_SIZE = 11;
    private int size;

    public MyHashTable() {
        chainArray = createArray(DEFAULT_SIZE);
    }

    /**
     * @param size Size of the
     */
    public MyHashTable(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("Size must be positive value!");
        chainArray = createArray(size);
    }

    /**
     * Adds a key-value pair to the HashTable.
     * Executing increaseSize method for checking remaining size.
     * Calculates the index of the key in the chainArray.
     * If the slot at the calculated index is empty, inserts the new node directly.
     * If the slot at the index have nodes, iterates through the chain to find the key.
     * If the key is found, updates the value of the existing key-value pair.
     * If the key is not found in the chain, appends the new key-value pair to the end of the chain.
     *
     * @param key   The key to be inserted.
     * @param value The value associated with the key.
     */
    public void put(K key, V value) {
        increaseSize(); // Size check inside the function.

        int index = getIndex(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);


        if (chainArray[index] == null) {
            chainArray[index] = newNode;
            size++;
            return;
        }

        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            if (node.next == null)
                break;
            node = node.next;
        }
        if (node == null)
            throw new NullPointerException("Node element cannot be null");
        node.next = newNode;
        size++;
    }

    /**
     * This method finding the mapped value for key in HashTable.
     * The value getting by calculating the index by key.
     * Then checking every node in index position of the array for key.
     * If node with valid key presents, returning mapped value, null otherwise.
     *
     * @param key Key object for getting value.
     * @return Value of mapped key in HashTable or null.
     */
    public V get(K key) {
        int index = getIndex(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) return node.value;
            node = node.next;
        }

        return null;
    }

    /**
     * Method for removing key and value from HashTable.
     * This method calculating index of the key in chainArray.
     * If node at index position is null, null will be returned.
     * If node at index is alone, asserting that node have correct key then deleting.
     * If node doesn't alone, going through every node at index position.
     * In case of absence the node with key that equal given key, null be returned.
     * When node was found, returning value and cutting the node from chain.
     *
     * @param key Key for deletion "key-value" pair.
     * @return Value of key that deleted or null if key doesn't present.
     */
    public V remove(K key) {
        int index = getIndex(key);

        HashNode<K, V> node = chainArray[index];
        if (node == null) return null; // If node equals null, returning null.
        V value;

        // Case for alone node at index position.
        if (node.key.equals(key)) {
            value = chainArray[index].value;
            chainArray[index] = null;
            return value;
        }

        // Case for node is not alone, have node.next nodes.
        while (node.next != null) {
            if (node.next.key.equals(key)) {
                value = node.next.value;
                node.next = node.next.next;
                return value;
            }
            node = node.next;
        }

        return null;
    }

    /**
     * Method that checking if value have any key in HashTable.
     * Method goes through every element in array and node.
     * If value doesn't present in HashTable, false will return, otherwise true.
     *
     * @param value Value for finding key
     * @return True if key for value exists, false otherwise.
     */
    public boolean contains(V value) {
        return getKey(value) != null;
    }

    /**
     * Method for finding key by value in "key-value" pair.
     * Method goes through every element in array and every node.
     * If HashTable doesn't contain key for this value, null will return.
     *
     * @param value Value for finding key in "key-value" pair.
     * @return Key or null.
     */
    public K getKey(V value) {
        for (HashNode<K, V> node : chainArray)
            while (node != null) {
                if (node.value.equals(value)) return node.key;
                node = node.next;
            }
        return null;
    }

    /**
     * Goes through every element and adding hashNode string to StringBuilder.
     * Returns HashTable in this format: "MyHashTable{hashNode, hashNode, hashNode, ...}"
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MyHashTable{");
        for (HashNode<K, V> node : chainArray)
            while (node != null) {
                builder.append(node).append(",");
                node = node.next;
            }
        builder.append("}");
        return builder.toString();
    }

    /**
     * Method that creating new empty array with size M.
     * Returns empty array of HashNodes with generic types of Key and Value.
     * Every element in array is null.
     *
     * @param M Size of the new array.
     * @return New empty array of HashNodes
     */
    @SuppressWarnings("unchecked")
    private HashNode<K, V>[] createArray(int M) {
        return new HashNode[M];
    }

    /**
     * Increasing size of the array if HashTable filled on 75%.
     * If HashTable doesn't filled on 75%, method will be ignored.
     * Saving old array then creating new array.
     * Old array used in reindexing method.
     */
    private void increaseSize() {
        if (size < chainArray.length * 3 / 4) return;

        HashNode<K, V>[] oldChain = chainArray;
        chainArray = createArray(chainArray.length * 2);
        reIndex(oldChain);
    }

    /**
     * Going through every element and node in old array and putting it to the new.
     * New array is this.chainArray.
     * Method use old array as reference for nodes,
     * then using put() method for adding elements to existing new array.
     *
     * @param oldChain Old array of HashNodes
     */
    private void reIndex(HashNode<K, V>[] oldChain) {
        for (HashNode<K, V> node : oldChain) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    /**
     * Using default Object method for calculating hash of the object.
     *
     * @param key Key object for node.
     * @return Integer of hash the key object.
     */
    private int hash(K key) {
        return key.hashCode();
    }

    /**
     * This method takes hashCode from key object
     * and using the remainder of the division the hash by the length of this.chainArray.
     * Returning value, that greater or equal than 0 and lesser than length of the array.
     *
     * @param key Key object for calculating index.
     * @return Index position for key in this.chainArray from 0 inclusive to array length not inclusive.
     */
    private int getIndex(K key) {
        int hash = hash(key);
        return Math.abs(hash) % chainArray.length;
    }
}
