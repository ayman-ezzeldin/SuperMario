/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package mario;




import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.SPACE;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Mario extends Application {
    
    private Stage primaryStage;
    static double yJump = 0;
    private double gravity = 0.5;
    static boolean jumping = false;
    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean isCollision = false;
    private boolean isCollisionRight = false;
    private boolean isCollisionLeft = false;
    static boolean jumpingFromObstacles = false;
    //for coinsss
    private List<ImageView> coinImages;
    private boolean[] coinCollected;
    private int coinsCount;

    @Override
    public void start(Stage primaryStage) throws Exception {
          
        this.primaryStage = primaryStage;
        Button startButton = new Button("Start Game"); // Stuff of MainScene
        startButton.setStyle("-fx-background-color: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 10;");
        startButton.setFont(Font.font("Arial", 20));
        Button endButton = new Button("End Game"); // Stuff of MainScene
        endButton.setStyle("-fx-background-color: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 10;");
        endButton.setFont(Font.font("Arial", 20));

        // إضافة تأثير الانتقال عند تمرير الماوس على الزرار
        startButton.setOnMouseEntered(event -> {
            startButton.setScaleX(1.1);
            startButton.setScaleY(1.1);
        });
        
        endButton.setOnMouseEntered(event -> {
            endButton.setScaleX(1.1);
            endButton.setScaleY(1.1);
        });

        // إزالة تأثير الانتقال عندما يترك الماوس الزرار
        startButton.setOnMouseExited(event -> {
            startButton.setScaleX(1.0);
            startButton.setScaleY(1.0);
        });
        endButton.setOnMouseExited(event -> {
            endButton.setScaleX(1.0);
            endButton.setScaleY(1.0);
        });
        
        VBox v = new VBox(15);
        v.getChildren().addAll(startButton, endButton);
           StackPane sp =new StackPane();
        ImageView startimage = new ImageView(new Image("image\\startimage.jpg"));   
        startimage.setFitWidth(900);
        startimage.setFitHeight(500);
        startimage.setPreserveRatio(false);
        sp.getChildren().add(startimage);
        sp.getChildren().add(v);
        startButton.setTranslateX(-5);
        v.setTranslateX(420);
        v.setTranslateY(160);
        Scene mainScene = new Scene(sp,900,500);
        
        //for the game over
        Button newgame = new Button("New game"); // Stuff of MainScene
        newgame.setStyle("-fx-background-color: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 10;");
        newgame.setFont(Font.font("Arial", 20));
        Button exit = new Button("Exit"); // Stuff of MainScene
        exit.setStyle("-fx-background-color: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20;");
        exit.setFont(Font.font("Arial", 20));
        Label targetLabel = new Label();
        targetLabel.setPrefWidth(40);
        targetLabel.setPrefHeight(30);
        targetLabel.setLayoutX(40); 
        targetLabel.setLayoutY(50);
        targetLabel.setStyle("-fx-text-fill: white; -fx-font-size: 30px; -fx-font-weight: bold;");

        newgame.setOnMouseEntered(event -> {
            newgame.setScaleX(1.1);
            newgame.setScaleY(1.1);
        });
        
        exit.setOnMouseEntered(event -> {
            exit.setScaleX(1.1);
            exit.setScaleY(1.1);
        });

        newgame.setOnMouseExited(event -> {
            newgame.setScaleX(1.0);
            newgame.setScaleY(1.0);
        });
        exit.setOnMouseExited(event -> {
            exit.setScaleX(1.0);
            exit.setScaleY(1.0);
        });
        
        VBox vbox = new VBox(120);
        vbox.getChildren().addAll(newgame, exit);
           StackPane end =new StackPane();
        ImageView gameover = new ImageView(new Image("C:\\Users\\Ayman\\Documents\\NetBeansProjects\\mario\\src\\image\\last.jpg"));   
        gameover.setFitWidth(900);
        gameover.setFitHeight(500);
        gameover.setPreserveRatio(false);
        end.getChildren().add(gameover);
        end.getChildren().add(vbox);
        end.getChildren().add(targetLabel);
        exit.setTranslateX(30);
        vbox.setTranslateX(380);
        vbox.setTranslateY(150);
        Scene lastscene = new Scene(end,900,500);

        
        BorderPane borderPane = new BorderPane(); // Stuff of GameScene
        Label coinsLabel = new Label("coins:");

        coinsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Label coinsValueLabel = new Label("0");
        coinsValueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        HBox coinsHBox = new HBox(10);
        coinsHBox.getChildren().addAll(coinsLabel, coinsValueLabel);
        borderPane.setTop(coinsHBox);

        gamepane pane = new gamepane();
        Pane root = new Pane();
        root.getChildren().add(pane);
        root.getChildren().add(borderPane);

        //reading images
        ImageView marioImage = pane.getMarioImage();
        ImageView pipe1 = pane.getPipe1();
        ImageView pipe2 = pane.getPipe2();
        ImageView block = pane.getBlock();
        ImageView block1 = pane.getBlock1();
        ImageView block2 = pane.getBlock2();
        ImageView grub = pane.grubImage();
        
        coinImages = new ArrayList<>();
        coinCollected = new boolean[3]; // Assuming you have 3 coin images
        coinsCount = 0;
        
        ImageView coinImage1 = new ImageView(new Image("image\\coin1.png"));
        coinImage1.setX(510);
        coinImage1.setY(210);
        coinImage1.setFitWidth(30);
        coinImage1.setFitHeight(30);
        root.getChildren().add(coinImage1);
        
        coinImages.add(coinImage1);
        
        Image coin2 = new Image("image\\coin1.png");
        ImageView coinImage2 = new ImageView(coin2);
        coinImage2.setX(360);
        coinImage2.setY(210);
        coinImage2.setFitWidth(30);
        coinImage2.setFitHeight(30);
        root.getChildren().add(coinImage2);
        
        coinImages.add(coinImage2);
        
        Image coin3 = new Image("image\\coin1.png");
        ImageView coinImage3 = new ImageView(coin3);
        coinImage3.setX(210);
        coinImage3.setY(260);
        coinImage3.setFitWidth(30);
        coinImage3.setFitHeight(30);
        root.getChildren().add(coinImage3);
        
        coinImages.add(coinImage3);
       
        // Create animation timer to update Mario's position
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double marioYMax = marioImage.getBoundsInParent().getMaxY();
                double marioXMin = marioImage.getBoundsInParent().getMinX();
                double marioXMax = marioImage.getBoundsInParent().getMaxX();
                double pipe1XMin = pipe1.getBoundsInParent().getMinX();
                double pipe1XMax = pipe1.getBoundsInParent().getMaxX();
                double pipe1YMin = pipe1.getBoundsInParent().getMinY();
                double pipe2XMin = pipe2.getBoundsInParent().getMinX();
                double pipe2XMax = pipe2.getBoundsInParent().getMaxX();
                double pipe2YMin = pipe2.getBoundsInParent().getMinY();
                double blockXMin = block.getBoundsInParent().getMinX();
                double blockXMax = block.getBoundsInParent().getMaxX();
                double blockYMin = block.getBoundsInParent().getMinY();
                double block1XMin = block1.getBoundsInParent().getMinX();
                double block1XMax = block1.getBoundsInParent().getMaxX();
                double block1YMin = block1.getBoundsInParent().getMinY();
                double block2XMin = block2.getBoundsInParent().getMinX();
                double block2XMax = block2.getBoundsInParent().getMaxX();
                double block2YMin = block2.getBoundsInParent().getMinY();
                
                // Update Mario's position based on jump velocity
             
                marioImage.setY(marioImage.getY() - yJump); // Update Mario's position based on jump velocity
                yJump -= gravity;
                if (marioImage.getY() >= 300) { // Check if Mario has landed
                    jumping = false;
                    yJump = 0;
                    marioImage.setY(300);
                }
                if ((pipe1YMin >= marioYMax && marioYMax >= pipe1YMin - 30) && ((marioXMax >= pipe1XMin && marioXMax <= pipe1XMax) // Mario Standing on obejcts
                        || (marioXMin >= pipe1XMin && marioXMin <= pipe1XMax)) && !jumpingFromObstacles) {
                    pane.getPosition(pipe1);
                } else if ((pipe2YMin >= marioYMax && marioYMax >= pipe2YMin - 30) && ((marioXMax >= pipe2XMin && marioXMax <= pipe2XMax)
                        || (marioXMin >= pipe2XMin && marioXMin <= pipe2XMax)) && !jumpingFromObstacles) {
                    pane.getPosition(pipe2);
                } else if ((blockYMin >= marioYMax && marioYMax >= blockYMin - 10) && ((marioXMax >= blockXMin && marioXMax <= blockXMax)
                        || (marioXMin >= blockXMin && marioXMin <= blockXMax)) && !jumpingFromObstacles) {
                    pane.getPosition(block);
                } else if ((block1YMin >= marioYMax && marioYMax >= block1YMin - 10) && ((marioXMax >= block1XMin && marioXMax <= block1XMax)
                        || (marioXMin >= block1XMin && marioXMin <= block1XMax)) && !jumpingFromObstacles) {
                    pane.getPosition(block1);
                } else if ((block2YMin >= marioYMax && marioYMax >= block2YMin - 10) && ((marioXMax >= block2XMin && marioXMax <= block2XMax)
                        || (marioXMin >= block2XMin && marioXMin <= block2XMax)) && !jumpingFromObstacles) {
                    pane.getPosition(block2);
                }
                
                if (marioImage.getBoundsInParent().intersects(pipe2.getBoundsInParent())) //Collision + Movenment
                {
                    isCollision = true;
                    if (marioXMin < pipe2XMin) {
                        isCollisionRight = true;
                    } else if (marioXMin > pipe2XMin) {
                        isCollisionLeft = true;
                    }
                } else if (marioImage.getBoundsInParent().intersects(pipe1.getBoundsInParent())) {
                    isCollision = true;
                    if (marioXMin < pipe1XMin) {
                        isCollisionRight = true;
                    } else if (marioXMin > pipe1XMin) {
                        isCollisionLeft = true;
                    }

                } else {
                    isCollision = false;
                    isCollisionRight = false;
                    isCollisionLeft = false;
                }

                if (!isCollision) {
                    if (movingRight) {
                        marioImage.setX(marioImage.getX() + 4);
                    }

                    if (movingLeft) {
                        marioImage.setX(marioImage.getX() - 4);
                    }
                }

                if (isCollisionRight) {
                    if (movingLeft) {
                        marioImage.setX(marioImage.getX() - 4);
                    }
                }

                if (isCollisionLeft) {
                    if (movingRight) {
                        marioImage.setX(marioImage.getX() + 4);
                    }
                }
                // Check for collision between Mario and coin
                for (int i = 0; i < coinImages.size(); i++) {
                if (!coinCollected[i] && checkCollision(marioImage, coinImages.get(i))) {
                    coinImages.get(i).setVisible(false);
                    coinCollected[i] = true;
                    coinsCount++;
                    coinsValueLabel.setText(Integer.toString(coinsCount));
                    String text = coinsValueLabel.getText();
                    if (text != null) {
                        targetLabel.setText(text);
                    } else {
                       targetLabel.setText("0"); 
                    }
                    
                }}
                
                if(checkCollision(marioImage, grub)) {
//                    primaryStage.close();
                    setScene(lastscene);
                }
                
                }

        };

         Scene gameScene = new Scene(root, 900, 500);
        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                movingRight = true;
            } else if (event.getCode() == KeyCode.LEFT) {
                movingLeft = true;
                
            } else if (!jumping && event.getCode() == KeyCode.SPACE) {
                jumping = true;
                jumpingFromObstacles = true;
                yJump = 12;

            }
        });

        gameScene.setOnKeyReleased(event -> {
            if (null != event.getCode()) switch (event.getCode()) {
                case RIGHT:
                    movingRight = false;
                    marioImage.setScaleX(1);
                    break;
                case LEFT:
                    movingLeft = false;
                    marioImage.setScaleX(-1);
                    break;
                case SPACE:
                    jumpingFromObstacles = false;
                    break;
                default:
                    break;
            }
        });
        timer.start();
        

        startButton.setOnAction(e -> {
        setScene(gameScene);

        });
        endButton.setOnAction(e -> {
            primaryStage.close();
        });
        
        newgame.setOnAction(e -> {
        setScene(mainScene);

        });
        exit.setOnAction(e -> {
            primaryStage.close();
        });

     
        primaryStage.setScene(mainScene);
        primaryStage.show();
        primaryStage.setTitle("Mario Game");
        primaryStage.setResizable(false);  
        
    }
    private boolean checkCollision(ImageView imageView1, ImageView imageView2) {
        // TODO: Implement collision detection logic
        Bounds bounds1 = imageView1.getBoundsInParent();
        Bounds bounds2 = imageView2.getBoundsInParent();
        return bounds1.intersects(bounds2);
    }
    
    public void setScene(Scene s) {
        primaryStage.setScene(s);
    }
    
    private void openNewScene() {
        // Create the new stage and scene
       gamepane2 pane2 =new gamepane2();
        StackPane newRoot = new StackPane();
        newRoot.getChildren().add(pane2);
      
        ImageView marioImage = pane2.getMarioImage();
        ImageView pipe1 = pane2.getPipe1();
        ImageView pipe2 = pane2.getPipe2();
        ImageView block = pane2.getBlock();
        ImageView block1 = pane2.getBlock1();
        ImageView block2 = pane2.getBlock2();
        ImageView grub = pane2.grubImage();
        
          
          AnimationTimer timer2 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double marioYMax = marioImage.getBoundsInParent().getMaxY();
                double marioXMin = marioImage.getBoundsInParent().getMinX();
                double marioXMax = marioImage.getBoundsInParent().getMaxX();
                double pipe1XMin = pipe1.getBoundsInParent().getMinX();
                double pipe1XMax = pipe1.getBoundsInParent().getMaxX();
                double pipe1YMin = pipe1.getBoundsInParent().getMinY();
                double pipe2XMin = pipe2.getBoundsInParent().getMinX();
                double pipe2XMax = pipe2.getBoundsInParent().getMaxX();
                double pipe2YMin = pipe2.getBoundsInParent().getMinY();
                double blockXMin = block.getBoundsInParent().getMinX();
                double blockXMax = block.getBoundsInParent().getMaxX();
                double blockYMin = block.getBoundsInParent().getMinY();
                double block1XMin = block1.getBoundsInParent().getMinX();
                double block1XMax = block1.getBoundsInParent().getMaxX();
                double block1YMin = block1.getBoundsInParent().getMinY();
                double block2XMin = block2.getBoundsInParent().getMinX();
                double block2XMax = block2.getBoundsInParent().getMaxX();
                double block2YMin = block2.getBoundsInParent().getMinY();
                
                // Update Mario's position based on jump velocity
             
                marioImage.setY(marioImage.getY() - yJump); // Update Mario's position based on jump velocity
                yJump -= gravity;
                if (marioImage.getY() >= 300) { // Check if Mario has landed
                    jumping = false;
                    yJump = 0;
                    marioImage.setY(300);
                }
                if ((pipe1YMin >= marioYMax && marioYMax >= pipe1YMin - 30) && ((marioXMax >= pipe1XMin && marioXMax <= pipe1XMax) // Mario Standing on obejcts
                        || (marioXMin >= pipe1XMin && marioXMin <= pipe1XMax)) && !jumpingFromObstacles) {
                    pane2.getPosition(pipe1);
                } else if ((pipe2YMin >= marioYMax && marioYMax >= pipe2YMin - 30) && ((marioXMax >= pipe2XMin && marioXMax <= pipe2XMax)
                        || (marioXMin >= pipe2XMin && marioXMin <= pipe2XMax)) && !jumpingFromObstacles) {
                    pane2.getPosition(pipe2);
                } else if ((blockYMin >= marioYMax && marioYMax >= blockYMin - 10) && ((marioXMax >= blockXMin && marioXMax <= blockXMax)
                        || (marioXMin >= blockXMin && marioXMin <= blockXMax)) && !jumpingFromObstacles) {
                    pane2.getPosition(block);
                } else if ((block1YMin >= marioYMax && marioYMax >= block1YMin - 10) && ((marioXMax >= block1XMin && marioXMax <= block1XMax)
                        || (marioXMin >= block1XMin && marioXMin <= block1XMax)) && !jumpingFromObstacles) {
                    pane2.getPosition(block1);
                } else if ((block2YMin >= marioYMax && marioYMax >= block2YMin - 10) && ((marioXMax >= block2XMin && marioXMax <= block2XMax)
                        || (marioXMin >= block2XMin && marioXMin <= block2XMax)) && !jumpingFromObstacles) {
                    pane2.getPosition(block2);
                }
                
                if (marioImage.getBoundsInParent().intersects(pipe2.getBoundsInParent())) //Collision + Movenment
                {
                    isCollision = true;
                    if (marioXMin < pipe2XMin) {
                        isCollisionRight = true;
                    } else if (marioXMin > pipe2XMin) {
                        isCollisionLeft = true;
                    }
                } else if (marioImage.getBoundsInParent().intersects(pipe1.getBoundsInParent())) {
                    isCollision = true;
                    if (marioXMin < pipe1XMin) {
                        isCollisionRight = true;
                    } else if (marioXMin > pipe1XMin) {
                        isCollisionLeft = true;
                    }

                } else {
                    isCollision = false;
                    isCollisionRight = false;
                    isCollisionLeft = false;
                }

                if (!isCollision) {
                    if (movingRight) {
                        marioImage.setX(marioImage.getX() + 4);
                    }

                    if (movingLeft) {
                        marioImage.setX(marioImage.getX() - 4);
                    }
                }

                if (isCollisionRight) {
                    if (movingLeft) {
                        marioImage.setX(marioImage.getX() - 4);
                    }
                }

                if (isCollisionLeft) {
                    if (movingRight) {
                        marioImage.setX(marioImage.getX() + 4);
                    }
                }
               
                
                if(checkCollision(marioImage, grub)) {
                    primaryStage.close();
                }  
                
                if (marioImage.getX() >= 900) {
                      openNewScene();            
                        stop();
                }
          
             }
                
            

        };

           Scene newScene = new Scene(newRoot, 900, 500);
       newScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                movingRight = true;
            } else if (event.getCode() == KeyCode.LEFT) {
                movingLeft = true;
                
            } else if (!jumping && event.getCode() == KeyCode.SPACE) {
                jumping = true;
                jumpingFromObstacles = true;
                yJump = 12;

            }
        });

       newScene.setOnKeyReleased(event -> {
            if (null != event.getCode()) switch (event.getCode()) {
                case RIGHT:
                    movingRight = false;
                    marioImage.setScaleX(1);
                    break;
                case LEFT:
                    movingLeft = false;
                    marioImage.setScaleX(-1);
                    break;
                case SPACE:
                    jumpingFromObstacles = false;
                    break;
                default:
                    break;
            }
        });
        timer2.start();
        

        // Set the new scene on the new stage
      
       primaryStage.setScene(newScene);
        primaryStage.show();
        primaryStage.setTitle("Mario Game");
        primaryStage.setResizable(false);  
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}