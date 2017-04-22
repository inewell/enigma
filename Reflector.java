public class Reflector
{
  private final String out;

  public static final Reflector A = new Reflector("EJMZALYXVBWFCRQUONTSPIKHGD");
  public static final Reflector B = new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
  public static final Reflector C = new Reflector("FVPJIAOYEDRZXWGCTKUQSBNMHL");

  public Reflector(String out)
  {
    this.out = out;
  }

  public String out()
  {
    return out;
  }

  public char output(char in)
  {
    return out.charAt((int)in - 65);
  }
}
