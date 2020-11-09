package blackjack.utils;

 // https://stackoverflow.com/questions/22115/using-regex-to-generate-strings-rather-than-match-them
import nl.flotsam.xeger.Xeger; // https://code.google.com/archive/p/xeger/

public class Generator {
    public String suit() {
      String regex = "Hearts|Clubs|Spades|Diamonds";
      String suit = createGenerator(regex).generate();
      //  https://www.geeksforgeeks.org/assertions-in-java/
      assert suit.matches(regex);
      return suit;
    }

    public String face() {
        String regex = "[2-9]|10|Ace|King|Queen|Jack";
        String face = createGenerator(regex).generate();
        assert face.matches(regex);
        return face;
    }



    public static Xeger createGenerator(String regex) {
        return new Xeger(regex);
    }


    public String nextCard() {
        return face() + " of " + suit();
    }

//    public void out() {System.out.println(output);}

}
