import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;

public class GUI extends Application
{

    private Entropy main_entropy = null;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primary_stage)
    {
        primary_stage.setTitle("Entropy");
        primary_stage.setWidth(800.0);
        primary_stage.setHeight(450.0);
        primary_stage.setResizable(false);

        VBox main_vbox = new VBox();

        MenuBar main_menu_bar = new MenuBar();
        main_menu_bar.setPrefSize(800.0, 25.0);

        //following items moved here to allow modification via "open" menu item
        TextArea main_text_area = new TextArea();
        Button option_1 = new Button();
        Button option_2 = new Button();
        Button option_3 = new Button();
        Button option_4 = new Button();

        Menu file_menu = new Menu();
        file_menu.setText("File");

        FileChooser file_chooser = new FileChooser();
        file_chooser.setInitialDirectory(new File("."));
        file_chooser.getExtensionFilters().addAll(new ExtensionFilter("JSON files", "*.json"), new ExtensionFilter("Entropy files (NYI)", "*.ent"));

        MenuItem open = new MenuItem();
        open.setText("Open");
        open.setOnAction(e -> {
            File to_open = file_chooser.showOpenDialog(primary_stage);

            main_entropy = new Entropy(to_open.getAbsolutePath());
            main_text_area.setText(main_entropy.getCurrentPage().getContents());

            //I'm so sorry for this
            if(main_entropy.getCurrentPage().getNumOptions() >= 1)
            {
                option_1.setText(main_entropy.getCurrentPage().getOption(0));
                option_1.setDisable(false);
            }
            else
            {
                option_1.setText("Option 1");
                option_1.setDisable(true);
            }

            if(main_entropy.getCurrentPage().getNumOptions() >= 2)
            {
                option_2.setText(main_entropy.getCurrentPage().getOption(1));
                option_2.setDisable(false);
            }
            else
            {
                option_2.setText("Option 2");
                option_2.setDisable(true);
            }

            if(main_entropy.getCurrentPage().getNumOptions() >= 3)
            {
                option_3.setText(main_entropy.getCurrentPage().getOption(2));
                option_3.setDisable(false);
            }
            else
            {
                option_3.setText("Option 3");
                option_3.setDisable(true);
            }

            if(main_entropy.getCurrentPage().getNumOptions() >= 4)
            {
                option_4.setText(main_entropy.getCurrentPage().getOption(3));
                option_4.setDisable(false);
            }
            else
            {
                option_4.setText("Option 4");
                option_4.setDisable(true);
            }
        });

        file_menu.getItems().add(open);

        MenuItem close = new MenuItem();
        close.setText("Close");
        close.setOnAction(e -> {
            main_entropy = null;
            main_text_area.setText("No page loaded!");

            option_1.setText("Option 1");
            option_1.setDisable(true);

            option_2.setText("Option 2");
            option_2.setDisable(true);

            option_3.setText("Option 3");
            option_3.setDisable(true);

            option_4.setText("Option 4");
            option_4.setDisable(true);
        });

        file_menu.getItems().add(close);

        Menu misc_menu = new Menu();
        misc_menu.setText("Misc.");

        MenuItem go_back = new MenuItem();
        go_back.setText("Go back");
        go_back.setOnAction(e -> {
            if(main_entropy != null)
            {
                main_entropy.goBack();
                main_text_area.setText(main_entropy.getCurrentPage().getContents());

                //I'm so sorry for this
                if(main_entropy.getCurrentPage().getNumOptions() >= 1)
                {
                    option_1.setText(main_entropy.getCurrentPage().getOption(0));
                    option_1.setDisable(false);
                }
                else
                {
                    option_1.setText("Option 1");
                    option_1.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 2)
                {
                    option_2.setText(main_entropy.getCurrentPage().getOption(1));
                    option_2.setDisable(false);
                }
                else
                {
                    option_2.setText("Option 2");
                    option_2.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 3)
                {
                    option_3.setText(main_entropy.getCurrentPage().getOption(2));
                    option_3.setDisable(false);
                }
                else
                {
                    option_3.setText("Option 3");
                    option_3.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 4)
                {
                    option_4.setText(main_entropy.getCurrentPage().getOption(3));
                    option_4.setDisable(false);
                }
                else
                {
                    option_4.setText("Option 4");
                    option_4.setDisable(true);
                }
            }
        });

        misc_menu.getItems().add(go_back);

        main_menu_bar.getMenus().add(file_menu);
        main_menu_bar.getMenus().add(misc_menu);

        main_vbox.getChildren().add(main_menu_bar);

        //TextArea main_text_area = new TextArea();
        main_text_area.setPrefSize(800.0, 375.0);
        main_text_area.setText("No page loaded!");
        main_text_area.setWrapText(true);
        main_text_area.setDisable(false);

        main_vbox.getChildren().add(main_text_area);

        HBox button_hbox = new HBox();
        button_hbox.setPrefSize(800.0, 100.0);

        //Button option_1 = new Button();
        option_1.setPrefSize(200.0, 100.0);
        option_1.setText("Option 1");
        option_1.setDisable(true);
        option_1.setOnAction(e -> {
            if(main_entropy != null && !option_1.isDisable())
            {
                main_entropy.goToOption(0);

                main_text_area.setText(main_entropy.getCurrentPage().getContents());

                //I'm so sorry for this
                if(main_entropy.getCurrentPage().getNumOptions() >= 1)
                {
                    option_1.setText(main_entropy.getCurrentPage().getOption(0));
                    option_1.setDisable(false);
                }
                else
                {
                    option_1.setText("Option 1");
                    option_1.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 2)
                {
                    option_2.setText(main_entropy.getCurrentPage().getOption(1));
                    option_2.setDisable(false);
                }
                else
                {
                    option_2.setText("Option 2");
                    option_2.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 3)
                {
                    option_3.setText(main_entropy.getCurrentPage().getOption(2));
                    option_3.setDisable(false);
                }
                else
                {
                    option_3.setText("Option 3");
                    option_3.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 4)
                {
                    option_4.setText(main_entropy.getCurrentPage().getOption(3));
                    option_4.setDisable(false);
                }
                else
                {
                    option_4.setText("Option 4");
                    option_4.setDisable(true);
                }
            }
        });

        //Button option_2 = new Button();
        option_2.setPrefSize(200.0, 100.0);
        option_2.setText("Option 2");
        option_2.setDisable(true);
        option_2.setOnAction(e -> {
            if(main_entropy != null && !option_2.isDisable())
            {
                main_entropy.goToOption(1);

                main_text_area.setText(main_entropy.getCurrentPage().getContents());

                //I'm so sorry for this
                if(main_entropy.getCurrentPage().getNumOptions() >= 1)
                {
                    option_1.setText(main_entropy.getCurrentPage().getOption(0));
                    option_1.setDisable(false);
                }
                else
                {
                    option_1.setText("Option 1");
                    option_1.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 2)
                {
                    option_2.setText(main_entropy.getCurrentPage().getOption(1));
                    option_2.setDisable(false);
                }
                else
                {
                    option_2.setText("Option 2");
                    option_2.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 3)
                {
                    option_3.setText(main_entropy.getCurrentPage().getOption(2));
                    option_3.setDisable(false);
                }
                else
                {
                    option_3.setText("Option 3");
                    option_3.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 4)
                {
                    option_4.setText(main_entropy.getCurrentPage().getOption(3));
                    option_4.setDisable(false);
                }
                else
                {
                    option_4.setText("Option 4");
                    option_4.setDisable(true);
                }
            }
        });

        //Button option_3 = new Button();
        option_3.setPrefSize(200.0, 100.0);
        option_3.setText("Option 3");
        option_3.setDisable(true);
        option_3.setOnAction(e -> {
            if(main_entropy != null && !option_3.isDisable())
            {
                main_entropy.goToOption(2);

                main_text_area.setText(main_entropy.getCurrentPage().getContents());

                //I'm so sorry for this
                if(main_entropy.getCurrentPage().getNumOptions() >= 1)
                {
                    option_1.setText(main_entropy.getCurrentPage().getOption(0));
                    option_1.setDisable(false);
                }
                else
                {
                    option_1.setText("Option 1");
                    option_1.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 2)
                {
                    option_2.setText(main_entropy.getCurrentPage().getOption(1));
                    option_2.setDisable(false);
                }
                else
                {
                    option_2.setText("Option 2");
                    option_2.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 3)
                {
                    option_3.setText(main_entropy.getCurrentPage().getOption(2));
                    option_3.setDisable(false);
                }
                else
                {
                    option_3.setText("Option 3");
                    option_3.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 4)
                {
                    option_4.setText(main_entropy.getCurrentPage().getOption(3));
                    option_4.setDisable(false);
                }
                else
                {
                    option_4.setText("Option 4");
                    option_4.setDisable(true);
                }
            }
        });

        //Button option_4 = new Button();
        option_4.setPrefSize(200.0, 100.0);
        option_4.setText("Option 4");
        option_4.setDisable(true);
        option_4.setOnAction(e -> {
            if(main_entropy != null && !option_4.isDisable())
            {
                main_entropy.goToOption(3);

                main_text_area.setText(main_entropy.getCurrentPage().getContents());

                //I'm so sorry for this
                if(main_entropy.getCurrentPage().getNumOptions() >= 1)
                {
                    option_1.setText(main_entropy.getCurrentPage().getOption(0));
                    option_1.setDisable(false);
                }
                else
                {
                    option_1.setText("Option 1");
                    option_1.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 2)
                {
                    option_2.setText(main_entropy.getCurrentPage().getOption(1));
                    option_2.setDisable(false);
                }
                else
                {
                    option_2.setText("Option 2");
                    option_2.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 3)
                {
                    option_3.setText(main_entropy.getCurrentPage().getOption(2));
                    option_3.setDisable(false);
                }
                else
                {
                    option_3.setText("Option 3");
                    option_3.setDisable(true);
                }

                if(main_entropy.getCurrentPage().getNumOptions() >= 4)
                {
                    option_4.setText(main_entropy.getCurrentPage().getOption(3));
                    option_4.setDisable(false);
                }
                else
                {
                    option_4.setText("Option 4");
                    option_4.setDisable(true);
                }
            }
        });

        button_hbox.getChildren().addAll(option_1, option_2, option_3, option_4);

        main_vbox.getChildren().add(button_hbox);


        Scene main_scene = new Scene(main_vbox, 800.0, 450.0);

        primary_stage.setScene(main_scene);
        primary_stage.show();
    }
}
