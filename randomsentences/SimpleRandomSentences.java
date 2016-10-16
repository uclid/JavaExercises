
/*
  The last verse of a well-known nursery rhyme:

    This is the farmer sowing his corn
    That kept the rooster that crowed in the morn
    That waked the judge all shaven and shorn
    That married the man all tattered and torn
    That kissed the maiden all forlorn
    That milked the cow with the crumpled horn
    That tossed the dog 
    That worried the cat
    That chased the rat
    That ate the cheese
    That lay in the house that Jack built. 
    
  Some rules that capture the syntax of this verse:
    
    <sentence> ::= <simple_sentence> [ and <sentence> ]

    <simple_sentence> ::=  this is [ <noun_phrase> ] the house that Jack built
    
    <noun_phrase> ::= the <noun> [ <modifier> ] that <verb>
    
    <noun> ::= farmer | rooster | judge | man | maiden | cow | dog | cat | cheese
    
    <verb> ::= kept | waked | married | milked | tossed | chased | lay in
    
    <modifier> ::= that crowed in the morn | all shaven and shorn |
                    all tattered and torn | all forlorn | with the crumpled horn
    
  This program implements these rules to generate random sentences.  All the
  verses of the rhyme can be generated, plus a lot of sentences that make no
  sense (but still follow the syntax).   Note that an optional item like
  [ <modifier> ] has a chance of being used, depending on the value of some
  randomly generated number.
  
  The program generates and outputs one random sentence every three seconds until
  it is halted (for example, by typing Control-C in the terminal window where it is
  running).
*/


public class SimpleRandomSentences {

   static final String[] nouns = { "farmer", "rooster", "judge", "man", "maiden",
                                   "cow", "dog", "cat", "cheese" };
                                   
   static final String[] verbs = { "kept", "waked", "married",
                                   "milked", "tossed", "chased", "lay in" };

   static final String[] modifiers = { "that crowed in the morn", "sowing his corn", 
                                       "all shaven and shorn",
                                       "all forlorn", "with the crumpled horn" };                                   

   public static void main(String[] args) {
      while (true) {
         randomSentence();
	     System.out.println(".\n\n");
         try {
             Thread.sleep(3000);
         }
         catch (InterruptedException e) {
         }
      }
   }
   
   static void randomSentence() {
      System.out.print("this is ");
      if (Math.random() > 0.2)
	      randomNounPhrase();
      System.out.print("the house that Jack built");
      if (Math.random() > 0.75) {
         System.out.print(" and ");
         randomSentence();
      }
   }
   
   static void randomNounPhrase() {
          int n = (int)(Math.random()*nouns.length);
          int v = (int)(Math.random()*verbs.length);
          int m = (int)(Math.random()*modifiers.length);
          System.out.print("the " + nouns[n]);
          if (Math.random() > 0.75)
             System.out.print(" " + modifiers[m]);
          System.out.print(" that " + verbs[v] + " ");
          if (Math.random() > 0.5)
	          randomNounPhrase();
   }

}
