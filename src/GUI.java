import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

//import java.awt.*;

public class GUI extends Application
{

    Entropy main_entropy = null;

    public static void main(String[] args)
    {


        launch(args);
    }

    @Override
    public void start(Stage primary_stage)
    {
        primary_stage.setTitle("Entropy CYOA Engine");
        primary_stage.setMinWidth(800.0);
        primary_stage.setMinHeight(600.0);
        primary_stage.setResizable(true);

        //AnchorPane main_anchor_pane = new AnchorPane();
        //main_anchor_pane.setPrefSize(800.0, 600.0);

        AnchorPane main_menu_bar_anchor_pane = new AnchorPane();

        MenuBar main_menu_bar = new MenuBar();
        //main_anchor_pane.setLeftAnchor(main_menu_bar, 0.0);
        //main_anchor_pane.setRightAnchor(main_menu_bar, 0.0);
        //main_anchor_pane.setTopAnchor(main_menu_bar, 0.0);
        main_menu_bar_anchor_pane.setLeftAnchor(main_menu_bar, 0.0);
        main_menu_bar_anchor_pane.setRightAnchor(main_menu_bar, 0.0);
        main_menu_bar_anchor_pane.setTopAnchor(main_menu_bar, 0.0);

        Menu file = new Menu("File");
        Menu help = new Menu("Help");

        MenuItem new_page = new MenuItem("New Page");
        MenuItem new_story = new MenuItem("New Story");
        MenuItem open_story = new MenuItem("Open Story");
        open_story.setOnAction(e -> {
            FileChooser open_story_file_chooser = new FileChooser();
            open_story_file_chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));



            String filename = open_story_file_chooser.showOpenDialog(primary_stage).getName();

            main_entropy = new Entropy(filename);

            System.out.println(main_entropy.current_page.getStructureTree());

        });
        MenuItem save = new MenuItem("Save");
        MenuItem save_as = new MenuItem("Save As");
        MenuItem close = new MenuItem("Close");
        close.setOnAction(e ->
                          {
                              System.out.println("Exiting program...");
                              System.exit(0);
                          });

        MenuItem about = new MenuItem("About");
        about.setOnAction(e -> {
            Alert about_alert = new Alert(Alert.AlertType.INFORMATION);

            about_alert.setTitle("About Entropy");
            about_alert.setHeaderText("Entropy copyright symbol 2018");
            about_alert.setContentText("PLACEHOLDER TEXT\n\nTEST");

            about_alert.showAndWait();

        });

        file.getItems().addAll(new_page, new_story, open_story, new SeparatorMenuItem(), save, save_as, new SeparatorMenuItem(), close);
        help.getItems().addAll(about);

        main_menu_bar.getMenus().addAll(file, help);

        main_menu_bar_anchor_pane.getChildren().add(main_menu_bar);

        AnchorPane page_area_anchor_pane = new AnchorPane();

        TextArea page_area = new TextArea();
        //main_anchor_pane.setBottomAnchor(page_area, 0.0);
        //main_anchor_pane.setLeftAnchor(page_area, 0.0);
        //main_anchor_pane.setRightAnchor(page_area, 0.0);
        //main_anchor_pane.setTopAnchor(page_area, 0.0);
        page_area_anchor_pane.setBottomAnchor(page_area, 0.0);
        page_area_anchor_pane.setLeftAnchor(page_area, 0.0);
        page_area_anchor_pane.setRightAnchor(page_area, 0.0);
        page_area_anchor_pane.setTopAnchor(page_area, 0.0);

        page_area_anchor_pane.getChildren().add(page_area);

        ButtonBar options_button_bar = new ButtonBar();




        //VBox main_vbox = new VBox(main_menu_bar, page_area);
        VBox main_vbox = new VBox(main_menu_bar_anchor_pane, page_area_anchor_pane);

        //main_anchor_pane.getChildren().add(main_vbox);
        //main_anchor_pane.getChildren().addAll(main_menu_bar, page_area);



        Scene primary_scene = new Scene(main_vbox, 800.0, 600.0);
        //Scene primary_scene = new Scene(main_anchor_pane, 800.0, 600.0);

        primary_stage.setScene(primary_scene);
        primary_stage.show();
    }
}
