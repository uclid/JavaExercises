package hashtable;

public class HashTable {

   /**
    * Represents a new node of hash table   
    */
   private static class Node {
      String key; // Stores the key
      String value; // Stores the value
      Node next;  // Pointer to next node 
                     
   }

   private Node[] table;  // hash table as an array of linked list
   //chaining method is applied

   private int count;  // Number of pairs in the table
   
   /**
    * Constructor for new hash table of predefined size
    */
   public HashTable(int inputSize) {
      table = new Node[inputSize];
   }

   
   /**
    * Adds a pair to linked list
    */
   public void put(String key, String value) {
      
      assert key != null : "The key must be non-null";
      
      int index = hash(key); // location of the calculated key
      
      Node list = table[index]; // For traversing the list
      while (list != null) {
            // checking if the key already exists.
         if (list.key.equals(key))
            break;
         list = list.next;
      }
      
      if (list != null) {
         list.value = value;
      }
      else {
         Node newNode = new Node();
         newNode.key = key;
         newNode.value = value;
         newNode.next = table[index];
         table[index] = newNode;
         count++;  // Counting the newly added key.
      }
   }

   
   /**
    * Getting the value stored with the key
    * @param key  key of the value to be searched
    * @return the value store with the key
    */
   public String get(String key) {
      
      int index = hash(key);  
      
      Node list = table[index];  // For traversing the list.
      while (list != null) {
            // Checking if key already exists
         if (list.key.equals(key))
            return list.value;
         list = list.next;  // Moving to the next node
      }
      
      return null;  
   }

   
   /**
    * Remove the key and its associated value from the table,
    * if the key occurs in the table.  If it does not occur,
    * then nothing is done.
    */
   public void remove(String key) {  
      
      int index = hash(key); 
      
      if (table[index] == null) {
         return; 
      }
      
      if (table[index].key.equals(key)) {
         table[index] = table[index].next;
         count--; // Remove the count of the nodes
         return;
      }
      
      // Removing node from the middle
      
      Node prev = table[index];  // The node that precedes
                                      // curr in the list.  Prev.next
                                      // is always equal to curr.
      Node curr = prev.next;  // For traversing the list,
                                  // starting from the second node.
      while (curr != null && ! curr.key.equals(key)) {
         curr = curr.next;
         prev = curr;
      }
      
      // If we get to this point, then either curr is null,
      // or curr.key is equal to key.  In the later case,
      // we have to remove curr from the list.  Do this
      // by making prev.next point to the node after curr,
      // instead of to curr.  If curr is null, it means that
      // the key was not found in the table, so there is nothing
      // to do.
      
      if (curr != null) {
         prev.next = curr.next;
         count--;  // Record new number of items in the table.
      }
   }

   
   /**
    * Checking whether it contains key or not.
    * @param key 
    * @return returns true if the key is found
    */
   public boolean containsKey(String key) {
      
      int index = hash(key);  
      
      Node list = table[index]; 
      while (list != null) {
           
         if (list.key.equals(key))
            return true;
         list = list.next;
      }
      
      return false;
   }

   
   /**
    * Return the number of pairs in the table.
    */
   public int size() {
      return count;
   }


   /**
    * Computing the hashcode for key value
    */
   private int hash(Object key) {
      return (Math.abs(key.hashCode())) % table.length; // hashing function
   }
   

} // end class HashTable
