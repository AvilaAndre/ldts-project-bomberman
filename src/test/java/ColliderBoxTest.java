import Structures.ColliderBox;
import Structures.Position;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ColliderBoxTest {
    @Test
    public void CollisionTest() throws IOException {
        ColliderBox col1 = new ColliderBox(new Position(3,0), 2, 2);
        ColliderBox col2 = new ColliderBox(new Position(0,0), 4, 6);
        ColliderBox col3 = new ColliderBox(new Position(0,3), 1, 1);
        ColliderBox col4 = new ColliderBox(new Position(15,16), 10, 25);
        ColliderBox col5 = new ColliderBox(new Position(20,23), 4, 5);

        assertTrue(col1.collides(col2));

        assertTrue(col2.collides(col1));

        assertFalse(col1.collides(col3));

        assertTrue(col2.collides(col3));

        assertFalse(col2.collides(col4));

        assertTrue(col5.collides(col4));
    }
}
