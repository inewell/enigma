/* Project Title: Enigma
* Description: This emulates the rotor component.
*
* Created by: Isaac Newell
* Date: 04/22/2017
*/

public class Rotor
{
  // Output of the rotor
  private final String out;
  // Reverse output of the rotor (useful for coming back through rotors)
  private final String revOut;
  // Location of turnover notch(es)
  private final String notches;

  // Actual wirings of rotors I-VIII
  public static final Rotor I = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", "R");
  public static final Rotor II = new Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", "F");
  public static final Rotor III = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", "W");
  public static final Rotor IV = new Rotor("ESOVPZJAYQUIRHXLNFTGKDCMWB", "K");
  public static final Rotor V = new Rotor("VZBRGITYUPSDNHLXAWMJQOFECK", "A");

  public static final Rotor VI = new Rotor("JPGVOUMFYQBENHZRDKASXLICTW", "AN");
  public static final Rotor VII = new Rotor("NZJHGRCXMYSWBOUFAIVLPEKQDT", "AN");
  public static final Rotor VIII = new Rotor("FKQHTLXOCBJSPDZRAMEWNIUYGV", "AN");

  public Rotor(String out, String notches)
  {
    this.out = out;
    this.notches = notches;

    // Create the reverse mapping, so as to not have to do it every time
    //    Traversing the rotors in the reverse direction
    char[] revList = new char[26];
    for (int i = 0; i < out.length(); i++)
    {
      revList[(int)out.charAt(i)-65] = (char)(i+65);
    }
    String ro = "";
    for (char c : revList)
      ro += String.valueOf(c);
    revOut = ro;
  }

  public String getNotches()
  {
    return notches;
  }

  public String out()
  {
    return out;
  }

  // Returns whether or not c is one of the turnover notches
  public boolean isNotch(char c)
  {
    for (int i = 0; i < notches.length(); i++)
    {
      if (notches.charAt(i) == c)
        return true;
    }
    return false;
  }

  // Useful for the Enigma class, offsets a char in a circular fashion by a
  //    specified amount. i.e. offset('A', 2) = 'C' and offset('Z', 3) = 'C'
  public static char offset(char start, int shift)
  {
    if (shift > 0)
    {
      if ((start+shift) > 90)
        return (char)(65 + shift - (90-start) - 1);
      else
        return (char)(start+shift);
    }
    else
    {
      if ((start+shift) < 65)
        return (char)(90 + (shift + (start-65)) + 1);
      else
        return (char)(start+shift);
    }
  }

  // Output of the rotor givin its ringSetting
  public char output(char in, int ringSetting)
  {
    char alphChar = offset(in, -(ringSetting-1));
    int offset = out.charAt((int)(alphChar)-65)-alphChar;
    return offset(in, offset);
  }

  // Same as above, but for traversing in reverse direction
  public char revOutput(char in, int ringSetting)
  {
    char alphChar = offset(in, -(ringSetting-1));
    int offset = revOut.charAt((int)(alphChar)-65)-alphChar;
    return offset(in, offset);
  }
}
