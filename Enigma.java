import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Enigma
{
  private Rotor[] rotors;
  private Reflector reflector;
  private Plugboard plugboard;

  private char[] tops;
  private int[] ringSettings;

  private int blockSize = 4;

  public Enigma(int numRotors)
  {
    rotors = new Rotor[numRotors];
    tops = new char[numRotors];
    ringSettings = new int[numRotors];

    plugboard = new Plugboard();
  }

  public Enigma()
  {
    this(3);
  }

  public void setRotor(int index, Rotor rotor)
  {
    rotors[index] = rotor;
  }

  public void setReflector(Reflector reflector)
  {
    this.reflector = reflector;
  }

  public void setRings(int[] ringSettings)
  {
    this.ringSettings = ringSettings;
  }

  public void setTops(char[] tops)
  {
    this.tops = tops;
  }

  public void step()
  {
    int rotorIndex = rotors.length-1;
    while (rotorIndex >= 0)
    {
      if (tops[rotorIndex] != 'Z')
        tops[rotorIndex] ++;
      else
        tops[rotorIndex] = 'A';

      if (rotors[rotorIndex].isNotch(tops[rotorIndex]))
        rotorIndex --;
      else
      {
        if (rotorIndex > 0)
        {
          if (rotors[rotorIndex-1].isNotch(Rotor.offset(tops[rotorIndex-1], 1)))
            rotorIndex --;
          // if (tops[rotorIndex-1] == Rotor.offset(rotors[rotorIndex-1].getNotch(), -1))
          //   rotorIndex --;
          else
            break;
        }
        else
          break;
      }
    }
    // for (char c : tops)
    // {
    //   System.out.print(c);
    // }
    // System.out.println();
  }

  public char forwardRotors(char input)
  {
    int rotorIndex = rotors.length-1;
    // char rotorInput = Rotor.offset(input, (int)(tops[rotorIndex]-65));
    // char rotorOutput = rotors[rotorIndex].output(rotorInput, ringSettings[rotorIndex]);
    // rotorIndex--;
    char current = input;
    while (rotorIndex >= 0)
    {
      current = Rotor.offset(current, (int)(tops[rotorIndex]-65));
      // System.out.println("input: " + current);
      // System.out.println(rotors[rotorIndex].out());
      current = rotors[rotorIndex].output(current, ringSettings[rotorIndex]);
      // System.out.println("output: " + current);
      current = Rotor.offset(current, (int)(65-tops[rotorIndex]));
      rotorIndex--;
    }
    return current;
  }

  public char backwardRotors(char input)
  {
    int rotorIndex = 0;
    char current = input;
    while (rotorIndex < rotors.length)
    {
      current = Rotor.offset(current, (int)(tops[rotorIndex]-65));
      current = rotors[rotorIndex].revOutput(current, ringSettings[rotorIndex]);
      current = Rotor.offset(current, (int)(65-tops[rotorIndex]));
      rotorIndex++;
    }
    return current;
  }

  public char encrypt(char input)
  {
    step();
    char current = input;
    current = plugboard.output(current);
    current = forwardRotors(current);
    current = reflector.output(current);
    current = backwardRotors(current);
    current = plugboard.output(current);
    return current;
  }

  public String encrypt(String message)
  {
    String cyphertext = "";
    int count = 0;
    for (int i = 0; i < message.length(); i++)
    {
      char next = message.charAt(i);
      if (next >= 97 && next <= 122)
        next -= 32;
      if (next >= 65 && next <= 90)
      {
        cyphertext += String.valueOf(encrypt(next));
        count++;
        if (count % blockSize == 0)
          cyphertext += " ";
      }
    }
    return cyphertext;
  }

  public static void main(String[] args)
  {
    Scanner kb = new Scanner(System.in);

    System.out.println("Enter rotors in order, left to right (i.e. I V II): ");
    System.out.println();
    String rotorsString = kb.nextLine();

    String nextRotor = "";
    ArrayList<Rotor> rotorsToAdd = new ArrayList<Rotor>();
    for (int i = 0; i < rotorsString.length(); i++)
    {
      if (rotorsString.charAt(i) == ' ')
      {
        if (nextRotor.equals("I"))
          rotorsToAdd.add(Rotor.I);
        else if (nextRotor.equals("II"))
          rotorsToAdd.add(Rotor.II);
        else if (nextRotor.equals("III"))
          rotorsToAdd.add(Rotor.III);
        else if (nextRotor.equals("IV"))
          rotorsToAdd.add(Rotor.IV);
        else if (nextRotor.equals("V"))
          rotorsToAdd.add(Rotor.V);
        else if (nextRotor.equals("VI"))
          rotorsToAdd.add(Rotor.VI);
        else if (nextRotor.equals("VII"))
          rotorsToAdd.add(Rotor.VII);
        else if (nextRotor.equals("VIII"))
          rotorsToAdd.add(Rotor.VIII);

        nextRotor = "";
      }
      else
        nextRotor += String.valueOf(rotorsString.charAt(i));
    }
    if (!nextRotor.equals(""))
    {
      if (nextRotor.equals("I"))
        rotorsToAdd.add(Rotor.I);
      else if (nextRotor.equals("II"))
        rotorsToAdd.add(Rotor.II);
      else if (nextRotor.equals("III"))
        rotorsToAdd.add(Rotor.III);
      else if (nextRotor.equals("IV"))
        rotorsToAdd.add(Rotor.IV);
      else if (nextRotor.equals("V"))
        rotorsToAdd.add(Rotor.V);
      else if (nextRotor.equals("VI"))
        rotorsToAdd.add(Rotor.VI);
      else if (nextRotor.equals("VII"))
        rotorsToAdd.add(Rotor.VII);
      else if (nextRotor.equals("VIII"))
        rotorsToAdd.add(Rotor.VIII);
    }

    Enigma enigma = new Enigma(rotorsToAdd.size());

    int rotorIndex = 0;
    for (Rotor r : rotorsToAdd)
    {
      enigma.setRotor(rotorIndex, r);
      rotorIndex++;
    }

    System.out.println();
    System.out.println("Enter reflector (i.e. B): ");
    System.out.println();
    String ref = kb.nextLine();
    if (ref.equals("A"))
      enigma.setReflector(Reflector.A);
    else if (ref.equals("B"))
      enigma.setReflector(Reflector.B);
    else if (ref.equals("C"))
      enigma.setReflector(Reflector.C);

    System.out.println();
    System.out.println("Enter ring settings (i.e. 01 02 21): ");
    System.out.println();
    String ringSets = kb.nextLine();

    int[] rsets = new int[enigma.rotors.length];
    int ringIndex = 0;
    String currRing = "";
    for (int i = 0; i < ringSets.length(); i++)
    {
      if (ringSets.charAt(i) == ' ')
      {
        if (currRing.charAt(0) == '0')
        {
          currRing = currRing.substring(1);
        }
        rsets[ringIndex] = Integer.parseInt(currRing);
        ringIndex++;
        currRing = "";
      }
      else
      {
        currRing += String.valueOf(ringSets.charAt(i));
      }
    }
    if (!currRing.equals(""))
    {
      if (currRing.charAt(0) == '0')
      {
        currRing = currRing.substring(1);
      }
      rsets[ringIndex] = Integer.parseInt(currRing);
    }
    enigma.setRings(rsets);

    System.out.println();
    System.out.println("Enter ring start positions (i.e. A W V): ");
    System.out.println();
    String ringPosStr = kb.nextLine();

    int topsIndex = 0;
    char[] topsToAdd = new char[enigma.rotors.length];
    for (int i = 0; i < ringPosStr.length(); i++)
    {
      if (ringPosStr.charAt(i) == ' ')
      {
        topsIndex++;
      }
      else
      {
        topsToAdd[topsIndex] = ringPosStr.charAt(i);
      }
    }
    enigma.setTops(topsToAdd);

    System.out.println();
    System.out.println("Enter plugboard pairs (i.e. AQ BZ LM ...): ");
    System.out.println();

    String plugString = kb.nextLine();

    String currPair = "";
    for (int i = 0; i < plugString.length(); i++)
    {
      if (plugString.charAt(i) == ' ')
      {
        currPair = "";
      }
      else
      {
        currPair += String.valueOf(plugString.charAt(i));
      }
      if (currPair.length() == 2)
      {
        enigma.plugboard.connect(currPair.charAt(0), currPair.charAt(1));
      }
    }

    System.out.println();
    System.out.println("Enter your message to decrypt/encrypt: ");
    System.out.println();
    String text = kb.nextLine();
    System.out.println();
    System.out.println("Your enigma's output message is: ");
    System.out.println();
    System.out.println(enigma.encrypt(text));

    kb.close();

    // for (char c : enigma.tops)
    // {
    //   System.out.print(c + " ");
    // }
    // System.out.println();

    // String message = "AAAAA";
    // for (int i = 0; i < message.length(); i++)
    // {
    //   System.out.print(enigma.encrypt(message.charAt(i)));
    // }
    // System.out.println();
  }
}
