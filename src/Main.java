import tree.BST;


public class Main {
    public static void main(String[] args) {


        BST<Integer, Integer> bst = new BST<>();

        bst.put(6, 1);
        bst.put(3, 2);
        bst.put(7, 3);
        bst.put(4, 4);
        bst.put(2, 5);
        bst.put(9, 6);
        bst.put(5, 7);


        System.out.println(bst);
        bst.put(5, 8);
        System.out.println(bst);
        System.out.println(bst.get(9));
        bst.remove(4);
        System.out.println(bst);

    }
}
