public class Rotor
{
  private final String out;
  private final String revOut;
  private final String notches;

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

  public boolean isNotch(char c)
  {
    for (int i = 0; i < notches.length(); i++)
    {
      if (notches.charAt(i) == c)
        return true;
    }
    return false;
  }

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

  public char output(char in, int ringSetting)
  {
    char alphChar = offset(in, -(ringSetting-1));
    int offset = out.charAt((int)(alphChar)-65)-alphChar;
    return offset(in, offset);
  }

  public char revOutput(char in, int ringSetting)
  {
    char alphChar = offset(in, -(ringSetting-1));
    int offset = revOut.charAt((int)(alphChar)-65)-alphChar;
    return offset(in, offset);
  }

  public static void main(String[] args)
  {
    // for (int rs = 1; rs <= 26; rs++)
    // {
    //   for (char c = 65; c <= 90; c++)
    //   {
    //     System.out.print(III.revOutput(c, rs));
    //   }
    //   System.out.println();
    // }
    // System.out.println(III.revOut);
  }
}
