import java.util.Scanner;

public class Main
{

    public static void main(String args[])
    {
        if(args.length != 1)
        {
            System.out.println("Error: program takes exactly 1 argument!");
            System.exit(1);
        }

        Scanner input = new Scanner(System.in);
        Entropy main_entropy = new Entropy(args[0]);

        if(main_entropy == null)
        {
            System.out.println("Error: unable to open file " + args[0]);
        }

        //main loop
        String user_input;
        int option;
        while(true)
        {
            System.out.println("--------------------------------");
            System.out.println(main_entropy.getCurrentPage().getName());
            System.out.println();
            System.out.println(main_entropy.getCurrentPage().getContents());
            System.out.println("--------------------------------");

            for(int i = 0; i < main_entropy.getCurrentPage().getNumOptions(); i++)
            {
                System.out.println(i + ": " + main_entropy.getCurrentPage().getOption(i));
            }

            System.out.print("> ");

            while(true)
            {
                user_input = input.nextLine();

                if(user_input.toLowerCase().equals("q") || user_input.toLowerCase().equals("quit"))
                {
                    System.out.println("Exiting program...");
                    System.exit(0);
                }

                try
                {
                    option = Integer.valueOf(user_input);
                    
                    if(main_entropy.goToOption(option))
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Error: invalid option " + option);
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Error: invalid input " + user_input);
                }
            }
        }
    }
}
