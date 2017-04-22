/* Project Title: Enigma
* Description: This emulates the plugboard component.
*
* Created by: Isaac Newell
* Date: 04/22/2017
*/

public class Plugboard
{
  // Output of the plugboard
  private char[] out;

  public Plugboard()
  {
    // Initialize it with no swaps
    out = new char[26];
    for (int i = 0; i < 26; i++)
    {
      out[i] = (char)(i+65);
    }
  }

  // Create a swap between c1 and c2
  public void connect(char c1, char c2)
  {
    out[(int)c1 - 65] = c2;
    out[(int)c2 - 65] = c1;
  }

  // Output for specified input char
  public char output(char in)
  {
    return out[(int)in - 65];
  }
}
