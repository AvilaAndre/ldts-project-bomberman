package DrawingMethods;

import Structures.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

public class DrawingAnimation {
    int frameIndex = 0;
    int currentFrameTick = 0;
    DrawingImage[] frames = null;
    int[] frameDuration = null;

    DrawingAnimation(DrawingImage[] images_, int[] durations_) {
        this.frames = images_;
        this.frameDuration = durations_;
        if (!(frameDuration.length == frames.length))
            if (frameDuration.length < frames.length) {
                int[] newDurations = new int[frames.length];
                for (int i = 0; i < frames.length; i++){
                    if (i < frameDuration.length)
                        newDurations[i] = frameDuration[i];
                    else
                        newDurations[i] = frameDuration[frameDuration.length-1];
                }
                frameDuration = newDurations;
            }

    }

    public void draw(TextGraphics graphics_, Position pos_, boolean boardOffset_) {
        currentFrameTick++;
        if (currentFrameTick > frameDuration[frameIndex]) {
            currentFrameTick = 0;
            frameIndex++;
            if (frameIndex >= frames.length)
                frameIndex = 0;
        }
        frames[frameIndex].draw(graphics_, pos_, boardOffset_);
    }
}
