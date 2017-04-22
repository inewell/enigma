import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class SettingGenerator
{
  public static String rotors()
  {
    String[] ws = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII"};
    ArrayList<String> choices = new ArrayList<String>(ws.length);
    for (String s : ws)
    {
      choices.add(s);
    }

    String output = "";
    for (int i = 1; i <= 3; i++)
    {
      int index = (int)(Math.random()*choices.size());
      output += choices.get(index);
      if (i != 3) output += " ";
      choices.remove(index);
    }

    return output;
  }

  public static String reflector()
  {
    String[] choices = {"A", "B", "C"};
    int index = (int)(Math.random() * choices.length);
    return choices[index];
  }

  public static String ringSettings()
  {
    String output = "";
    for (int i = 1; i <= 3; i++)
    {
      int s = (int)(Math.random() * 26 + 1);
      String setting = s + "";
      if (s < 10)
        setting = "0" + setting;
      output += setting;
      if (i != 3) output += " ";
    }
    return output;
  }

  public static String groundSettings()
  {
    String output = "";
    for (int i = 1; i <= 3; i++)
    {
      int s = (int)(Math.random() * 26 + 65);
      String setting = String.valueOf((char)s);
      output += setting;
      if (i != 3) output += " ";
    }
    return output;
  }

  public static String plugs()
  {
    ArrayList<String> choices = new ArrayList<String>(26);
    for (char c = 65; c <= 90; c++)
    {
      choices.add(String.valueOf(c));
    }

    String plugs = "";

    for (int i = 1; i <= 10; i++)
    {
      String pair = "";

      int index1 = (int)(Math.random() * choices.size());
      pair += choices.get(index1);
      choices.remove(index1);

      int index2 = (int)(Math.random() * choices.size());
      pair += choices.get(index2);
      choices.remove(index2);

      pair += " ";

      boolean inserted = false;
      for (int j = 0; j < plugs.length(); j += 3)
      {
        if (pair.compareTo(plugs.substring(j, j+1)) < 0)
        {
          plugs = plugs.substring(0, j) + pair + plugs.substring(j);
          inserted = true;
          break;
        }
      }
      if (!inserted)
      {
        plugs += pair;
      }
    }

    return plugs.substring(0, plugs.length()-1);
  }

  public static void main(String[] args)
  {
    Scanner kb = new Scanner(System.in);

    System.out.print("Enter number of days: ");
    String days = kb.nextLine();
    int numDays = Integer.parseInt(days);

    System.out.print("Enter output file name: ");
    String fileName = kb.nextLine();

    kb.close();

    try
    {
      FileWriter fw = new FileWriter(fileName);
      PrintWriter pw = new PrintWriter(fw);

      pw.println("Enigma Machine Daily Key Settings");

      DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
      Calendar cal = Calendar.getInstance();
      Date d = new Date();
      cal.setTime(d);
      String date = dateFormat.format(d);

      pw.println("Daily Key Settings for " + numDays + " days starting " + date);
      pw.println();

      for (int day = 1; day <= numDays; day++)
      {
        date = dateFormat.format(cal.getTime());
        pw.println("Day " + day + " (" + date + ")");
        pw.println("Rotors:    " + rotors());
        pw.println("Reflector: " + reflector());
        pw.println("Ring:      " + ringSettings());
        pw.println("Ground:    " + groundSettings());
        pw.println("Plugs:     " + plugs());
        pw.println();
        cal.add(Calendar.DATE, 1);
      }

      pw.close();
    }
    catch (IOException e)
    {

    }
  }
}
