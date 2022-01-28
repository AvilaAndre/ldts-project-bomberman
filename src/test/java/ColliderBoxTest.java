import Structures.ColliderBox;
import Structures.Position;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ColliderBoxTest {
    @Test
    public void CollisionTest() {
        // These represent the Positions of the parents of both colliders, instead of having a parent variable inside the ColliderBox class
        //the parent's position is passed as an argument so scenarios (example: the parent being one x position ahead) can be used.
        Position parentPosition = new Position(0,0);
        Position colliderPosition = new Position(0,0);
        ColliderBox col1 = new ColliderBox(new Position(3,0), 2, 2);
        ColliderBox col2 = new ColliderBox(new Position(0,0), 4, 6);
        ColliderBox col3 = new ColliderBox(new Position(0,3), 1, 1);
        ColliderBox col4 = new ColliderBox(new Position(15,16), 10, 25);
        ColliderBox col5 = new ColliderBox(new Position(20,23), 4, 5);

        assertTrue(col1.collides(parentPosition, col2, colliderPosition));

        assertTrue(col2.collides(parentPosition, col1, colliderPosition));

        assertFalse(col1.collides(parentPosition, col3, colliderPosition));

        assertTrue(col2.collides(parentPosition, col3, colliderPosition));

        assertFalse(col2.collides(parentPosition, col4, colliderPosition));

        assertTrue(col5.collides(parentPosition, col4, colliderPosition));
    }
}
