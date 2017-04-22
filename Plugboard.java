public class Plugboard
{
  private char[] out;

  public Plugboard()
  {
    out = new char[26];
    for (int i = 0; i < 26; i++)
    {
      out[i] = (char)(i+65);
    }
  }

  public void connect(char c1, char c2)
  {
    out[(int)c1 - 65] = c2;
    out[(int)c2 - 65] = c1;
  }

  public char output(char in)
  {
    return out[(int)in - 65];
  }

  public static void main(String[] args)
  {
    Plugboard p = new Plugboard();
    p.connect('A', 'D');
    for (int i = 0; i < p.out.length; i++)
      System.out.print(p.out[i]);
    System.out.println();
  }
}
