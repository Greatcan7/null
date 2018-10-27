package nullproject.player;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import nullproject.anim.SpriteAnimation;
import nullproject.config.GameConfigs;
import nullproject.game.Game;
import nullproject.levels.blocks.Door;
import nullproject.player.interfaces.PlayerPower;

import java.util.ConcurrentModificationException;

public class Player extends Pane implements PlayerPower {

    int count = 3;
    int column = 3;
    int offer_x = 96;
    int offer_y = 33;
    int width = 16;
    int height = 16;
    public SpriteAnimation animation;
    private DropShadow dropShadow;

    Image image = new Image(getClass().getResourceAsStream("../../scene/game/mario.png"));
    ImageView imageView = new ImageView(image);

    public Player() {
        dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(4.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        imageView.setViewport(new Rectangle2D(offer_x, offer_y, width, height));
        imageView.setEffect(dropShadow);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        animation = new SpriteAnimation(imageView, Duration.millis(200), count, column, offer_x, offer_y, width, height);
        getChildren().addAll(imageView);
    }

    @Override
    public void moveX(int value) {
        boolean movingRight = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : Game.platforms) {
                if (getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (getTranslateX() + GameConfigs.PLAYER_SIZE == platform.getTranslateX()) {
                            setTranslateX(getTranslateX() - 1);
                            return;
                        }
                    } else {
                        if (getTranslateX() == platform.getTranslateX() + GameConfigs.BLOCK_SIZE) {
                            setTranslateX(getTranslateX() + 1);
                            return;
                        }
                    }
                }
            }
            this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    @Override
    public void moveY(int value) {
        boolean movingDown = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : Game.platforms) {
                if (this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (this.getTranslateY() + GameConfigs.PLAYER_SIZE == platform.getTranslateY()) {
                            this.setTranslateY(this.getTranslateY() - 1);
                            return;
                        }
                    } else {
                        if (this.getTranslateY() == platform.getTranslateY() + GameConfigs.BLOCK_SIZE) {
                            this.setTranslateY(this.getTranslateY() + 1);
                            return;
                        }
                    }
                }
            }
            this.setTranslateY(this.getTranslateY() + (movingDown ? 1 : -1));
        }
    }

    @Override
    public void isDoorOpen() {
        try {
            for (Door coin : Game.doors) {
                if (this.getBoundsInParent().intersects(coin.getBoundsInParent())) {
                    Game.getInstance().showDialog();
                }
            }
        } catch (ConcurrentModificationException e) {
        }
    }


}
