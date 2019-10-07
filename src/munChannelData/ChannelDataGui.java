package munChannelData;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.regex.Pattern;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import numberlist.InvalidIndexException;
import numberlist.objectlist.Money;
import numberlist.objectlist.UncopiableException;
import numberlist.primitivelist.IntegerArrayList;

/**
 * ChannelDataGui is a javafx application that is used to modify and display my
 * channel data.
 *
 * @author Seonjun Mun
 * @version 09/23/2019
 */
public class ChannelDataGui extends Application {

    final int WIDTH = 1200;
    final int HEIGHT = 700;
    //globals
    private VBox vb_VideoList;
    private HBox hb_ActionsList;
    private HBox hb_Info;
    private HBox hb_SortOptionsList;

    private ScrollPane sp_VideoList;
    private StackPane stp_Info;
    private RadioButton rb_SortByDate;
    private RadioButton rb_SortByViews;
    private RadioButton rb_SortByName;
    private Button btn_Exit;
    private Button btn_Save;
    private Button btn_Add;
    private Button btn_Delete;
    private Button btn_Graph;
    private VBox root;
    private ToggleGroup sortOptionsToggleGroup;
    private TextField txt_Date;
    private TextField txt_Views;
    private TextField txt_Name;
    private ChannelDataManager manager;
    private Label lbl_AdditionalInfo;
    private Scene scene;
    private int selectedIndex;
    private IntegerArrayList a;
    private int b;
    private int c;


    @Override
    /**
     * starts the application
     *
     * @Param primaryStage- the stage to start with
     */
    public void start(Stage primaryStage) throws InvalidIndexException, UncopiableException {

        //set up the toggle group
        sortOptionsToggleGroup = new ToggleGroup();
        root = GetRoot();
        vb_VideoList = GetVideoList();
        hb_ActionsList = GetActionsList();
        hb_Info = GetInfoHBox();
        hb_SortOptionsList = GetSortOptionsList();

        sp_VideoList = GetVideoListScrollPane();
        rb_SortByDate = GetSortByDateRadioButton();
        rb_SortByViews = GetSortByViewsRadioButton();
        rb_SortByName = GetSortByNameRadioButton();
        btn_Exit = GetExitButton();
        btn_Save = GetSaveButton();
        btn_Add = GetAddButton();
        btn_Delete = GetDeleteButton();

        txt_Name = GetNameTextField();
        txt_Views = GetViewsTextField();
        txt_Date = GetDateTextField();
        lbl_AdditionalInfo = GetAdditionalInfoLabel();

        //Set up the children
        AddChildren();

        scene = new Scene(root, WIDTH, HEIGHT);


        primaryStage.setTitle("Seonjun's Channel Library");
        primaryStage.setScene(scene);

        primaryStage.show();
        //set the selected item to 0
        selectedIndex = 0;

        //by default set the sort by name to checked
        rb_SortByName.setSelected(true);

        //try to load the manager
        manager = ChannelDataManager.Deserialize();
        if (manager == null) {
            manager = new ChannelDataManager();
        }

        ReAddVideos();

        //slect the first item if the first item exists
        if (manager.getCount() > 0) {
            ItemClicked(selectedIndex = 0);
        }

        root.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY,
                Insets.EMPTY)));

    }


    /**
     * Creates and returns the additional info label
     *
     * @return the label that was created
     */
    public Label GetAdditionalInfoLabel() {
        return new Label();
    }

    /**
     * launches the videoLibraryGui main instance
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates and returns the root vbox
     *
     * @return the vbox that was created
     */
    public VBox GetRoot() {
        // return the root
        return new VBox();
    }

    /**
     * Creates and returns the video list vbox
     *
     * @return the vbox that was created
     */
    public VBox GetVideoList() {
//        VBox videoList = new VBox();
        return new VBox();
    }

    /**
     * Adds the video to the list
     *
     * @throws numberlist.InvalidIndexException
     * @throws numberlist.objectlist.UncopiableException
     */
    public void Add() throws InvalidIndexException, UncopiableException {
        Money money = new Money();

        String name;
        long date;

        //add a new item to the list but first attemt to convert the text field text
        // into appropriate values
        try {
            String text = txt_Views.getText();
            String[] args = text.split(Pattern.quote("."));
            if (args.length > 2) {
                throw new Exception();
            }
            money = new Money(Long.parseLong(args[0]),
                    args.length == 2 ? Byte.parseByte(args[1]) : 0);

        } catch (Exception ex) {
            //make the text box for views red
            txt_Views.setStyle("-fx-background-color: #ffb590");
            try {
                date = Long.parseLong(txt_Date.getText());
                if (date > 99999999 || date < 10000000) {
                    throw new Exception();
                }
            } catch (Exception e) {
                txt_Date.setStyle("-fx-background-color: #ffb590");
                return;
            }
            return;
        }
        txt_Views.setStyle("-fx-background-color: white");
        //try to parse the date
        try {
            date = Long.parseLong(txt_Date.getText());
            if (date > 99999999 || date < 10000000) {
                throw new Exception();
            }
            txt_Date.setStyle("-fx-background-color: white");
        } catch (Exception ex) {
            txt_Date.setStyle("-fx-background-color: #ffb590");
            return;
        }
        txt_Date.setStyle("-fx-background-color: white");

        name = txt_Name.getText();

        //add the item
        manager.AddVideo(name, money, date);

        // re add the items
        ReAddVideos();
    }

    /**
     * Called when a button in the video list is clicked on
     *
     * @param index- the index of the button
     * @throws numberlist.InvalidIndexException
     */
    public void ItemClicked(int index) throws InvalidIndexException {
        //set the selected index
        selectedIndex = index;
        String name = manager.GetVideoName(index);
        Money views = manager.GetVideoViews(index);
        long date = manager.GetVideoDate(index);
        //set the text data
        txt_Name.setText(name);
        txt_Views.setText(views.getDollars() + "");
        txt_Date.setText(date + "");


    }

    /**
     * Re adds the videos from the manager to the list
     *
     * @throws numberlist.InvalidIndexException
     * @throws numberlist.objectlist.UncopiableException
     */
    public void ReAddVideos() throws InvalidIndexException, UncopiableException {
        //sort with the appropriate list
        if (rb_SortByViews.isSelected()) {
            //sort by Views
            manager.SortByViews();
        } else if (rb_SortByDate.isSelected()) {
            manager.SortByDate();
        } else {
            manager.SortByName();
        }
        //clear the old list
        vb_VideoList.getChildren().clear();
        for (int i = 0; i < manager.getCount(); i++) {
            int index = i;
            //add the item to the hbox
            Button item = new Button();
            item.setPrefWidth(WIDTH / 3);
            item.setText(manager.GetVideoName(index));
            item.setOnAction(e -> {
                try {
                    ItemClicked(index);
                } catch (InvalidIndexException ex) {
                    Logger.getLogger(ChannelDataGui.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            //add the button to the list
            vb_VideoList.getChildren().add(item);
        }


    }

    /**
     * Deletes the selected item from the list
     *
     * @throws numberlist.InvalidIndexException
     * @throws numberlist.objectlist.UncopiableException
     */
    public void Delete() throws InvalidIndexException, UncopiableException {
        //delete the item at the selected index if there is an item at the selected index
        if (selectedIndex == -1 || manager.getCount() == 0) {
            return;
        }
        manager.DeleteVideo(selectedIndex);
        vb_VideoList.getChildren().clear();

        txt_Name.setText("");
        txt_Views.setText("");
        txt_Date.setText("");
        selectedIndex = selectedIndex > 0 ? selectedIndex - 1 : 0;
        //re add the childred
        ReAddVideos();
        if (manager.getCount() > 0) {
            ItemClicked(selectedIndex);
        }

    }

    /**
     * Sets the default info in the info label
     */
    public void SetDefaultInfo() {
        //set the additional info text to tell the user to add a number to the list
        lbl_AdditionalInfo.setText("  Add an item to the list to see information");
    }

    /**
     * Creates and returns the add button
     *
     * @return the button that was created
     */
    public Button GetAddButton() {
        Button addButton = new Button();
        addButton.setOnAction(e -> {
            try {
                Add();
            } catch (InvalidIndexException ex) {
                Logger.getLogger(ChannelDataGui.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UncopiableException ex) {
                Logger.getLogger(ChannelDataGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        addButton.setText("Add");
        addButton.setTooltip(new Tooltip("Adds the selected item to the list"));
        return addButton;
    }

    /**
     * Creates and returns the delete button
     *
     * @return the button that was created.
     */
    public Button GetDeleteButton() {
        Button deleteButton = new Button();
        deleteButton.setOnAction(e -> {
            try {
                Delete();
            } catch (InvalidIndexException ex) {
                Logger.getLogger(ChannelDataGui.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UncopiableException ex) {
                Logger.getLogger(ChannelDataGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        deleteButton.setText("Delete");
        deleteButton.setTooltip(new Tooltip("Deletes the selected game"));
        return deleteButton;
    }

    /**
     * Creates and returns the exit button
     *
     * @return the button that was created
     */
    public Button GetExitButton() {
        Button exitButton = new Button();
        exitButton.setOnAction(e -> Exit());
        exitButton.setText("Exit");
        exitButton.setTooltip(new Tooltip("Exits the application"));
        return exitButton;
    }

    /**
     *
     */
    public void Save() {
        manager.Serialize();
    }

    /**
     * Creates and returns the save button
     *
     * @return the save button that was created
     */
    public Button GetSaveButton() {
        Button saveButton = new Button();
        saveButton.setOnAction(e -> Save());
        saveButton.setText("Save before exit");
        saveButton.setTooltip(new Tooltip("Save the video libary"));
        //save the manager

        return saveButton;
    }

    /**
     * Creates and returns the actions list hbox
     *
     * @return the hbox that was created
     */
    public HBox GetActionsList() {
        HBox box = new HBox();
        box.setPadding(new Insets(50, 50, 50, 50));
        box.setSpacing(5);
        box.getStyleClass().add("hbox");
        box.setPrefSize(WIDTH, HEIGHT / 12);
        return box;
    }

    /**
     * Creates and returns the info hbox
     *
     * @return the hbox that was created
     */
    public HBox GetInfoHBox() {
        HBox box = new HBox();
        box.getStyleClass().add("hbox");
        box.setPadding(new Insets(50, 50, 50, 50));
        box.setPrefSize(WIDTH, (HEIGHT * 5) / 6);
        return box;
    }

    /**
     * Calls re add games
     *
     * @throws numberlist.InvalidIndexException
     * @throws numberlist.objectlist.UncopiableException
     */
    public void Selected() throws InvalidIndexException, UncopiableException {
        //re add the games
        ReAddVideos();
    }

    /**
     * Creates and returns the sort options list
     *
     * @return the hbox that was created
     */
    public HBox GetSortOptionsList() {
        HBox box = new HBox();
        box.getStyleClass().add("hbox");
        box.setPadding(new Insets(50, 50, 50, 50));
        box.setPrefSize(WIDTH, HEIGHT / 12);
        return box;
    }

    /**
     * Creates and returns the sort by name radio button
     *
     * @return the radio button that was created
     */
    public RadioButton GetSortByNameRadioButton() {
        RadioButton sortByName = new RadioButton();
        sortByName.setText("Sort by (Genre) Name  ");
        sortByName.setToggleGroup(sortOptionsToggleGroup);
        sortByName.setOnAction(e -> {
            try {
                Selected();
            } catch (InvalidIndexException ex) {
                Logger.getLogger(ChannelDataGui.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UncopiableException ex) {
                Logger.getLogger(ChannelDataGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return sortByName;
    }

    /**
     * Creates and returns the sort by Date radio button
     *
     * @return the sort by Date radio button
     */
    public RadioButton GetSortByDateRadioButton() {
        RadioButton sortByDate = new RadioButton();
        sortByDate.setText("Sort by Date  ");
        sortByDate.setToggleGroup(sortOptionsToggleGroup);
        sortByDate.setOnAction(e -> {
            try {
                Selected();
            } catch (InvalidIndexException ex) {
                Logger.getLogger(ChannelDataGui.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UncopiableException ex) {
                Logger.getLogger(ChannelDataGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return sortByDate;
    }

    /**
     * Creates and returns the sort by Views radio button
     *
     * @return the radio button that was created
     */
    public RadioButton GetSortByViewsRadioButton() {
        RadioButton sortByViews = new RadioButton();
        sortByViews.setText("Sort by Views  ");
        sortByViews.setToggleGroup(sortOptionsToggleGroup);
        sortByViews.setOnAction(e -> {
            try {
                Selected();
            } catch (InvalidIndexException ex) {
                Logger.getLogger(ChannelDataGui.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UncopiableException ex) {
                Logger.getLogger(ChannelDataGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return sortByViews;
    }

    /**
     * Creates and returns the video list scroll pane
     *
     * @return the video list scroll pane
     */
    public ScrollPane GetVideoListScrollPane() {
        ScrollPane pane = new ScrollPane();
        pane.setPrefWidth(600);
        return new ScrollPane();
    }


    /**
     * Creates and returns the video list vertical box
     *
     * @return the vertical box that was created
     */
    public VBox GetVideoListVBox() {
        VBox box = new VBox();
        return new VBox();
    }

    /**
     * Creates and returns the Date text field
     *
     * @return the Date text field that was created
     */
    public TextField GetDateTextField() {
        return new TextField();
    }

    /**
     * Returns the Views text field to be created
     *
     * @return the Views text field
     */
    public TextField GetViewsTextField() {
        return new TextField();
    }

    /**
     * Returns the name Textfield to be created
     *
     * @return the name text field
     */
    public TextField GetNameTextField() {
        return new TextField();
    }

    /**
     * Adds all of the children to the root pane
     */
    public void AddChildren() {
        //Add the boxes to the layout
        root.getChildren().add(hb_ActionsList);
        root.getChildren().add(hb_SortOptionsList);
        root.getChildren().add(hb_Info);

        //add children to the actions list layout
        hb_ActionsList.getChildren().add(btn_Exit);
        hb_ActionsList.getChildren().add(btn_Save);
        hb_ActionsList.getChildren().add(btn_Add);
        hb_ActionsList.getChildren().add(btn_Delete);
        Label lbl_Name = new Label();
        lbl_Name.setText("(Genre) Name: ");
        hb_ActionsList.getChildren().add(lbl_Name);
        hb_ActionsList.getChildren().add(txt_Name);
        Label lbl_Date = new Label();
        lbl_Date.setText("Date (yyyymmdd): ");
        hb_ActionsList.getChildren().add(lbl_Date);
        hb_ActionsList.getChildren().add(txt_Date);
        Label lbl_Views = new Label();
        lbl_Views.setText("Views: ");
        hb_ActionsList.getChildren().add(lbl_Views);
        hb_ActionsList.getChildren().add(txt_Views);

        Label lbl_sortOptions = new Label();
        lbl_sortOptions.setText("Sort options:  ");
        //add children to the sort options layout
        hb_SortOptionsList.getChildren().add(lbl_sortOptions);
        hb_SortOptionsList.getChildren().add(rb_SortByName);
        hb_SortOptionsList.getChildren().add(rb_SortByDate);
        hb_SortOptionsList.getChildren().add(rb_SortByViews);
        //add the additional info label to the sort options hbox
        hb_SortOptionsList.getChildren().add(lbl_AdditionalInfo);

        //add children to the info box
        hb_Info.getChildren().add(sp_VideoList);


        //add the VBox video list to the scroll pane
        sp_VideoList.setContent(vb_VideoList);

    }

    /**
     * Exits the application
     */
    public void Exit() {
        //close the application
        Platform.exit();
    }


}
