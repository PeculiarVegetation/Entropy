import java.util.ArrayList;

import org.json.*;

public class Page
{
    public final int MAX_CONTENTS_DISPLAY_LENGTH = 32;

    public String name;
    public String contents;
    public ArrayList<Page> options;

    Page(String name, String contents)
    {
        this.name = name;
        this.contents = contents;
        this.options = new ArrayList<>();
    }

    Page(JSONObject info)
    {
        this.name = info.getString("name");
        this.contents = info.getString("contents");
        this.options = new ArrayList<>();
        JSONArray temp_JSON_array = info.getJSONArray("options");
        for(int i = 0; i < temp_JSON_array.length(); i++)
        {
            this.options.add(new Page(temp_JSON_array.getJSONObject(i)));
        }
    }

    public String getName()
    {
        return (this.name);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getContents()
    {
        return (this.contents);
    }

    public void setContents(String contents)
    {
        this.contents = contents;
    }

    public int getNumPages()
    {
        return (this.options.size());
    }

    public Page getPage(int i)
    {
        if(i < 0 || i >= this.options.size())
        {
            return (null);
        }

        return (this.options.get(i));
    }

    public void addPage(Page p)
    {
        this.options.add(p);
    }

    public void addPages(Page... pages)
    {
        for(int i = 0; i < pages.length; i++)
        {
            this.options.add(pages[i]);
        }
    }

    public void removePage(int i)
    {
        if(!this.hasChildren() || i < 0 || i >= this.options.size())
        {
            return;
        }

        this.options.remove(i);
    }

    public boolean hasChildren()
    {
        return (this.options.size() > 0);
    }


    public String getJSONString()
    {
        //open result string
        String result = "{";

        //add name field
        result += "\"name\":\"" + this.name + "\",";  //Add name string

        //add data for any options
        result += "\"options\":[";
        for(int i = 0; i < this.options.size(); i++)
        {
            if(i < this.options.size() - 1)
            {
                result += this.options.get(i).getJSONString() + ",";
            }
            else
            {
                result += this.options.get(i).getJSONString();
            }
        }
        result += "],";

        //add contents field
        result += "\"contents\":\"";
        result += deformat(this.contents);
        result += "\"";

        //close result string
        result += "}";

        return (result);
    }

    public String getStructureTree()
    {
        String result = "";

        result += "Name: " + this.name + "\n";

        if(this.contents.length() >= MAX_CONTENTS_DISPLAY_LENGTH)
        {
            result += "Contents (truncated): " + deformat(this.contents.substring(0, MAX_CONTENTS_DISPLAY_LENGTH)) + "...\n";
        }
        else
        {
            result += "Contents: " + deformat(this.contents) + "\n";
        }

        for(int i = 0; i < this.options.size(); i++)
        {
            result += "Option " + i + ":\n" + getStructureTreeInternal(this.options.get(i), 1);
        }

        return (result);
    }

    private String getStructureTreeInternal(Page p, int depth)
    {
        String result = "";
        String dashes = "";

        for(int i = 0; i < depth; i++)
        {
            dashes += "----";
        }

        result += dashes + "Name: " + p.name + "\n";

        if(p.contents.length() >= MAX_CONTENTS_DISPLAY_LENGTH)
        {
            result += dashes + "Contents (truncated): " + deformat(p.contents.substring(0, MAX_CONTENTS_DISPLAY_LENGTH)) + "...\n";
        }
        else
        {
            result += dashes + "Contents: " + deformat(p.contents) + "\n";
        }

        for(int i = 0; i < p.options.size(); i++)
        {
            result += dashes + "Option " + i + ":\n" + getStructureTreeInternal(p.options.get(i), depth + 1);
        }

        return (result);
    }

    private String deformat(String s)
    {
        String result = "";

        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) == '\n')
            {
                result += "\\n";
            }
            else if(s.charAt(i) == '\t')
            {
                result += "\\t";
            }
            else
            {
                result += s.charAt(i);
            }
        }

        return (result);
    }

}
