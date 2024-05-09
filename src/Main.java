import hashtable.MyHashTable;

public class Main {
    public static void main(String[] args) {
        MyHashTable<Integer, Integer> hashTable = new MyHashTable<>();

        hashTable.put(1, 1);
        hashTable.put(1, 2);
        System.out.println(hashTable.contains(2));
        System.out.println(hashTable.contains(1));
        System.out.println(hashTable.get(1));
        System.out.println(hashTable.remove(1));
        System.out.println(hashTable.get(1));

        MyHashTable<String, Integer> newHashTable = new MyHashTable<>(100);

        for (int i = 0; i < 1000; i++) {
            double f = Math.random();
            newHashTable.put(String.valueOf((char) (65 * (1.0 - f) + 122 * f)), i);
        }

        System.out.println(newHashTable);
    }
}
