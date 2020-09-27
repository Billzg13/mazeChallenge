import models.Move;
import models.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void moveColumnPos() {
        var point = new Point(1, 1);
        Move move = new Move("column", "+");
        assertEquals( new Point(1, 2), point.move(move));
    }

    @Test
    void moveColumnNeg() {
        var point = new Point(2, 2);
        Move move = new Move("column", "-");
        assertEquals( new Point(2, 1), point.move(move));
    }

    @Test
    void moveRowPos() {
        var point = new Point(2, 2);
        Move move = new Move("row", "+");
        assertEquals( new Point(3, 2), point.move(move));
    }

    @Test
    void moveRowNeg(){
        var point = new Point(2, 2);
        Move move = new Move("row", "-");
        assertEquals( new Point(1, 2), point.move(move));
    }

    @Test
    void moveThrowsNullException(){
        var point = new Point(2, 2);
        assertThrows(NullPointerException.class, () -> {
            point.move(null);
        });
    }

    @Test
    void moveThrowsNullExceptionOnEmptyValue(){
        var point = new Point(2, 2);
        assertThrows(NullPointerException.class, () -> {
            point.move(new Move("", ""));
        });
    }

    @Test
    void moveThrowsIllegalArgumentExceptionOnAxis(){
        var point = new Point(2, 2);
        assertThrows(IllegalArgumentException.class, () -> {
            point.move(new Move("dasdsa", "-"));
        });
    }

    @Test
    void moveThrowsIllegalArgumentExceptionOnOperator(){
        var point = new Point(2, 2);
        assertThrows(IllegalArgumentException.class, () -> {
            point.move(new Move("row", "dasdsa"));
        });
    }

}