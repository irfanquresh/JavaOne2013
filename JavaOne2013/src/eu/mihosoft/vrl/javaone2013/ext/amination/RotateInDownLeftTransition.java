package eu.mihosoft.vrl.javaone2013.ext.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * Animate a rotate in down left effect on a node
 * 
 * Port of RotateInDownLeft from Animate.css http://daneden.me/animate by Dan Eden
 * 
 * {@literal @}keyframes rotateInDownLeft {
 * 	0% {
 * 		transform-origin: left bottom;
 * 		transform: rotate(-90deg);
 * 		opacity: 0;
 * 	}
 * 	100% {
 * 		transform-origin: left bottom;
 * 		transform: rotate(0);
 * 		opacity: 1;
 * 	}
 * }
 * 
 * @author Jasper Potts
 */
public class RotateInDownLeftTransition extends CachedTimelineTransition {
    private Rotate rotate;
    /**
     * Create new RotateInDownLeftTransition
     * 
     * @param node The node to affect
     */
    public RotateInDownLeftTransition(final Node node) {
        super(node, null);
        setCycleDuration(Duration.seconds(1));
        setDelay(Duration.seconds(0.2));
    }

    @Override protected void starting() {
        super.starting();
        rotate = new Rotate(0,0,node.getBoundsInLocal().getHeight());
        timeline = TimelineBuilder.create()
            .keyFrames(
                new KeyFrame(Duration.millis(0),    
                    new KeyValue(node.opacityProperty(), 0, WEB_EASE),
                    new KeyValue(rotate.angleProperty(), -90, WEB_EASE)
                ),
                new KeyFrame(Duration.millis(1000),    
                    new KeyValue(node.opacityProperty(), 1, WEB_EASE),
                    new KeyValue(rotate.angleProperty(), 0, WEB_EASE)
                )
            )
            .build();
        node.getTransforms().add(rotate);
    }

    @Override protected void stopping() {
        super.stopping();
        node.getTransforms().remove(rotate);
    }
}
