import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Spike work to test how to build a scene and create functional buttons that can communicate with another class.
 * @author Fred Ziegler
 * @version 1.0 (26th March 2019)
 */
public class test extends Application {

    @Override
    public void start(Stage primaryStage) {
        FlowPane root = new FlowPane(); // A type of JavaFX container. FlowPanes wrap their components down onto another row if "overflowing"
        root.setPadding(new Insets(10)); // Sets a margin for all components in the container (in all directions)
        root.setHgap(10); // Sets a default spacing between the container's components horizontally
        root.setVgap(10); // Sets a default spacing between the container's components vertically

        // Creates the buttons and their text (they exist "in the background", but are not yet shown)
        Button button1 = new Button("Press me!!");
        Button button2 = new Button ("Hello! I exist too!");
        Button button3 = new Button("And me! I do nothing, though.");
        Button button4 = new Button ("Not me, though... Just kidding!!");
        // Puts them onto the FlowPane [I think!]
        root.getChildren().addAll(button1, button2, button3, button4);

        // Create an instance of a class that the UI needs to communicate with/access the methods of
        someOtherClass justToTestCommunication = new someOtherClass();

        // Prints into console on click
        button1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                justToTestCommunication.printExampleCommunication2();
            }
        });

        // Prints into console via another class' method on click
        button2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                justToTestCommunication.printExampleCommunication();
            }
        });

        // Changes button text on mouse over/off
        button4.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                button4.setText("Actually... No, don't click me...");
            }
        });
        button4.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                button4.setText("No, wait, I changed my mind! Come back!!");
            }
        });

        // Creates a scene
        Scene scene = new Scene(root, 350, 150); // Parameters: container to use, default horizontal-, vertical- size
                                                               // If no size, automatically generated (wraps around contents)

        // Creates a window. Primary Stage is an argument of this method, not something built-in
        primaryStage.setTitle("Button test"); // Names the window
        primaryStage.setScene(scene); // Attaches the scene as the contents of the window
        primaryStage.show(); // Makes the window display
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
