/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mario;




import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author bahy
 */
public class gamepane extends Pane  {
      private final ImageView marioImage;
    private final ImageView pipe1;
    private final ImageView pipe2;
    private final ImageView block;
    private final ImageView block1;
    private final ImageView block2;
    private final ImageView grub;
    public  gamepane() {
    
        
              
        //BackGround
        Image backgroundImage = new Image("image\\background.png");
        ImageView background = new ImageView(backgroundImage);
        //تحديد حدود الصورة 

        background.setFitWidth(900);
        background.setFitHeight(500);
        background.setPreserveRatio(false);
        //Mario image
        Image marioImg = new Image("image\\marioRight0Lvl1.png");
        marioImage = new ImageView(marioImg);
        marioImage.setTranslateY(75);
        marioImage.setFitWidth(45);
        marioImage.setFitHeight(45);

        //Bug Stuff
        Image grubImage = new Image("image\\enemy1.png");
        grub = new ImageView(grubImage);
        grub.setX(535);
        grub.setY(400);
        grub.setFitWidth(30);
        grub.setFitHeight(30);

        Line grubline = new Line();
        grubline.setStartY(405);
        grubline.setEndY(405);
        grubline.setStartX(549);
        grubline.setEndX(737);

        PathTransition ptgrub = new PathTransition();
        ptgrub.setDuration(Duration.millis(5000));
        ptgrub.setPath(grubline);
        ptgrub.setNode(grub);
        ptgrub.setAutoReverse(true);
        ptgrub.setCycleCount(Timeline.INDEFINITE); // تكرار الحركة
        ptgrub.play();
        //الارض الطائرة
        Image blockImage = new Image("image\\wall.png");
        block = new ImageView(blockImage);
        block.setX(500);
        block.setY(250);
        block.setFitWidth(50);
        block.setFitHeight(50);

        Image blockImage1 = new Image("image\\wall.png");
        block1 = new ImageView(blockImage1);
        block1.setX(200);
        block1.setY(300);
        block1.setFitWidth(50);
        block1.setFitHeight(50);

        Image blockImage2 = new Image("image\\wall.png");
        block2 = new ImageView(blockImage2);
        block2.setX(350);
        block2.setY(250);
        block2.setFitWidth(50);
        block2.setFitHeight(50);

        //PipesImages
        Image pipeImage1 = new Image("image\\pipeSmall.png");
        pipe1 = new ImageView(pipeImage1);
        pipe1.setX(470);
        pipe1.setY(347);

        Image pipeImage2 = new Image("image\\pipeBig.png");
        pipe2 = new ImageView(pipeImage2);
        pipe2.setX(750);
        pipe2.setY(282);

        //adding images to pane
        getChildren().addAll(background, marioImage, block, block1, block2, pipe1, pipe2, grub);
        

    }
    
    public ImageView getMarioImage() {
        return marioImage;
    }

    public ImageView getPipe1() {
        return pipe1;
    }

    public ImageView getPipe2() {
        return pipe2;
    }

    public ImageView getBlock() {
        return block;
    }

    public ImageView getBlock1() {
        return block1;
    }

    public ImageView getBlock2() {
        return block2;
    }
    public ImageView grubImage() {
        return grub ;
    }


    public void getPosition(ImageView image) {
        ImageView marioImage = getMarioImage();
            Mario.jumping = false;
            Mario.yJump = 0;
            marioImage.setY(image.getY() - 45 - 77);
        
    }
  
}