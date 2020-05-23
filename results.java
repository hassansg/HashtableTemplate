import java.util.ArrayList;

public class results{

  //Initializes a new hashtable to store strings corresponding to integers
  private static ArrayList<String> makeHashTable(){

      ArrayList<String> myTable = new ArrayList<String>();

      myTable.add("Montreal");
      myTable.add("CompSci");
      myTable.add("my name is Hassan");
      myTable.add("SpaceX & NASA");
      myTable.add("Toronto");

      return myTable;
  }

  public static void main(String[] args) {

      ArrayList<String> myTable = makeHashTable();
      MyHashTable<int, String> Table;
      int numBuckets = 5;

      Table = new MyHashTable<int, String>(numBuckets);

      for(int i = 0; i < 6; i++){
        Table.put(i, myTable.get(i));
      }

      //Output for get function
      String out = myTable.get(1);
      System.out.println(out);

      //Output for remove function
      myTable.remove(2);

      //Output for rehashing
      myTable.rehash();

      //Output for keys and values functions
      myTable.values();
      myTable.keys();
  }
}
