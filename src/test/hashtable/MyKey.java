package test.hashtable;

public class MyKey {
    private int id;

    public MyKey(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int temp = id;
        temp >>>= 1;
        return temp ^ 0xaaaaaaaa;
    }


}
