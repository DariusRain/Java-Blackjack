package blackjack.utils.generators;

import java.security.SecureRandom;

/**
* <h1>Generator</h1>
* <p>
*     This class is responsible for generating random values based on arbitrary integer arguments in this case,
 *     in the previous version (v1.0) the arbitrary value could be a string.
* </p>
* @author  Darius Rain
* @version 1.1
* @since   YY-MM-DD
*/
public class Generator {

    static SecureRandom rand = new SecureRandom();

    /**
    * Generates a random number for any reason needed.
    * @param max The maximum value that can be returned ranging from 1 to max
    * @see CardGenerator#suit()
     * @see CardGenerator#face()
    * @return int
    */
    public static int generate(int max) { return rand.nextInt(max) + 1; }

}
