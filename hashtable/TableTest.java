package hashtable;

public class TableTest {

	   public static void main(String[] args){
	      HashTable table = new HashTable(5);  // initial size for test is 5.
	      String key,value;
	      while (true) {
	         System.out.println("\nMenu:");
	         System.out.println("   1. Put Key/Value");
	         System.out.println("   2. Get Value");
	         System.out.println("   3. Check Key");
	         System.out.println("   4. Remove Key");
	         System.out.println("   5. Exit");
	         System.out.print("Enter your command:  ");
	         switch ( TextIO.getlnInt()) {
	         case 1:
	            System.out.print("\n   Key: ");
	            key = TextIO.getln();
	            System.out.print("   Value: ");
	            value = TextIO.getln();
	            table.put(key,value);
	            break;         
	         case 2:
	            System.out.print("\n   Key: ");
	            key = TextIO.getln();
	            System.out.println("   Value: " + table.get(key));
	            break;         
	         case 3:
	            System.out.print("\n   Key: ");
	            key = TextIO.getln();
	            if(table.containsKey(key)){
	            System.out.println( key + " is in the list");
	            }
	            else
	            	System.out.println( key + " is not in the list");
	            break;         
	         case 4:
	            System.out.print("\n   Key: ");
	            key = TextIO.getln();
	            table.remove(key);
	            break;         
	         case 5:
	            return;           
	         default:
	            System.out.println("   Invalid Choice!!.");
	         break;
	         }
	         System.out.println("\nHash Table size is " + table.size());
	      }
	   }

	}
