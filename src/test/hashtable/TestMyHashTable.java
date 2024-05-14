package test.hashtable;

import hashtable.MyHashTable;

public class TestMyHashTable {
    public static void testIt() {
        MyHashTable<MyKey, MyValue> hashTable = new MyHashTable<>();

        for (int i = 0; i < 1000000; i++) {
            MyKey key = new MyKey(i ^ 0xbbbb);
            MyValue value = new MyValue(i);
            hashTable.put(key, value);
        }
    }
}
