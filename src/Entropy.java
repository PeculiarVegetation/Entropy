import java.util.ArrayList;
import java.util.Scanner;

import org.json.*;

import java.io.*;

public class Entropy
{

    private final String FILE_EXTENSION = ".json";

    private ArrayList<String> history;
    private Page current_page;

    Entropy(String filename)
    {
        this.history = new ArrayList<>();
        current_page = new Page(readJSONFromFile(filename));
    }

    public Page getCurrentPage()
    {
        return (this.current_page);
    }

    public boolean goToOption(int option)
    {
        if(current_page == null || option < 0 || option >= current_page.getOptions().size())
        {
            return (false);
        }

        String temp_string = current_page.getOption(option) + FILE_EXTENSION;
        JSONObject temp_JSON = readJSONFromFile(temp_string);

        if(temp_JSON == null)
        {
            return (false);
        }

        history.add(current_page.getName());
        current_page = loadPageFromJSON(temp_JSON);


        return (true);
    }

    public boolean goBack()
    {
        if(this.history.size() == 0)
        {
            return (false);
        }

        String temp_string = history.get(history.size() - 1) + FILE_EXTENSION;
        JSONObject temp_JSON = readJSONFromFile(temp_string);

        if(temp_JSON == null)
        {
            return (false);
        }

        current_page = loadPageFromJSON(temp_JSON);
        history.remove(history.size() - 1);

        return (true);
    }

    public Page loadPageFromJSON(JSONObject j)
    {
        return (new Page(j));
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
            System.out.println(e);
            return (null);
        }
    }

}
