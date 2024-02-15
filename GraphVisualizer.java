import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GraphVisualizer extends Application {

    @Override
    public void start(Stage primaryStage) {
        Graph graph = CreateGraph.getGraph();

        Pane pane = new Pane();
        Map<String, Circle> nodeCircles = new HashMap<>();

        double radius = 15;
        Random rand = new Random();

        // Define the pane size
        double paneWidth = 800;
        double paneHeight = 600;

        // Create visual representations for each user
        for (String userId : graph.getNodes().keySet()) {
            // Generate random positions within the pane, accounting for the radius to avoid clipping
            double x = radius + (paneWidth - 2 * radius) * rand.nextDouble();
            double y = radius + (paneHeight - 2 * radius) * rand.nextDouble();

            Circle circle = new Circle(x, y, radius, Color.LIGHTSKYBLUE);
            Text text = new Text(x - 10, y - 20, userId);
            pane.getChildren().addAll(circle, text);

            nodeCircles.put(userId, circle);
        }

        // Draw arrows for "follows" relationships
        graph.getNodes().forEach((userId, user) -> {
            Circle startCircle = nodeCircles.get(userId);
            user.getFollowing().forEach(followingUser -> {
                Circle endCircle = nodeCircles.get(followingUser.getUserId());
                if (endCircle != null) {
                    drawArrow(pane, startCircle, endCircle);
                }
            });
        });

        Scene scene = new Scene(pane, paneWidth, paneHeight);
        primaryStage.setTitle("Simple Graph Visualizer - Users and Relationships Displayed");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawArrow(Pane pane, Circle start, Circle end) {
        double startX = start.getCenterX();
        double startY = start.getCenterY();
        double endX = end.getCenterX();
        double endY = end.getCenterY();

        Line line = new Line(startX, startY, endX, endY);

        // Calculate the angle of the line
        double angle = Math.atan2(endY - startY, endX - startX);

        // Create arrowhead
        Polygon arrowHead = new Polygon();
        arrowHead.getPoints().addAll(
            0.0, 0.0,
            -10.0, -5.0,
            -10.0, 5.0
        );
        arrowHead.setFill(Color.BLACK);

        // Position the arrowhead at the end of the line
        arrowHead.setTranslateX(endX);
        arrowHead.setTranslateY(endY);
        arrowHead.setRotate(Math.toDegrees(angle) - 90);

        pane.getChildren().add(line);
        pane.getChildren().add(arrowHead);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
