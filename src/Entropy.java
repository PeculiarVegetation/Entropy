import java.util.ArrayList;
import java.util.Scanner;

import org.json.*;

import java.io.*;

public class Entropy
{

    public ArrayList<String> pages;
    public ArrayList<Page> history;
    public Page current_page = null;

    Entropy(String filename)
    {
        this.pages = new ArrayList<>();
        this.history = new ArrayList<>();
        current_page = new Page(readJSONFromFile(filename));
    }

    public Page getCurrentPage()
    {
        return (this.current_page);
    }

    public boolean goToOption(int option)
    {
        if(current_page == null || option < 0 || option >= current_page.options.size())
        {
            return(false);
        }

        history.add(current_page);
        current_page = current_page.getPage(option);

        return(true);
    }

    public void writeJSONToFile(String filename, String data)
    {
        try
        {
            FileOutputStream to_write = new FileOutputStream(filename);

            for(int i = 0; i < data.length(); i++)
            {
                to_write.write(data.charAt(i));
            }

            if(to_write != null)
            {
                to_write.close();
            }
        }
        catch(Exception e)
        {
            System.out.println("Error: could not open file " + filename);
        }
    }

    public JSONObject readJSONFromFile(String filename)
    {
        try
        {
            FileInputStream to_read = new FileInputStream(filename);

            Scanner infile = new Scanner(to_read);

            String input_string = "";

            while(infile.hasNextLine())
            {
                input_string += infile.nextLine();
            }

            JSONObject to_return = new JSONObject(input_string);

            if(to_read != null)
            {
                to_read.close();
            }

            return (to_return);
        }
        catch(Exception e)
        {
            System.out.println("Error: could not open file " + filename);
            return (null);
        }
    }

}
