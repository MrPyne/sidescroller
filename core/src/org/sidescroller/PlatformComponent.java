package org.sidescroller;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by matts on 14/05/17.
 */
public class PlatformComponent implements Component{

    Vector2 originalPosition;

    float timePassed = 0;
}
