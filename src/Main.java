import hashtable.MyHashTable;
import test.hashtable.MyKey;
import test.hashtable.TestMyHashTable;
import tree.BST;


public class Main {
    public static void main(String[] args) {
//        BST<Integer, Integer> bst = new BST<>();
//
//        bst.put(6, 1);
//        bst.put(3, 2);
//        bst.put(7, 3);
//        bst.put(4, 4);
//        bst.put(2, 5);
//        bst.put(9, 6);
//        bst.put(5, 7);
//
//
//        System.out.println(bst);
//        bst.put(5, 8);
//        System.out.println(bst);
//        System.out.println(bst.get(9));
//        bst.remove(4);
//        System.out.println(bst);


        MyKey key1 = new MyKey(5);
        MyKey key2 = new MyKey(6);
        MyKey key3 = new MyKey(7);
        MyKey key4 = new MyKey(8);
        MyKey key5 = new MyKey(9);

        System.out.print(key1.hashCode() + " ");
        System.out.print(key2.hashCode() + " ");
        System.out.print(key3.hashCode() + " ");
        System.out.print(key4.hashCode() + " ");
        System.out.print(key5.hashCode() + " ");

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

        TestMyHashTable.testIt();
    }
}
