import models.Point;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void findRandomPoint() {
        int rows = 5;
        int columns = 5;
        int zero = 0;
        assertEquals(true, Main.findRandomPoint(rows, columns).getColumn() > zero );
        assertEquals(true, Main.findRandomPoint(rows, columns).getColumn() <= columns );
        assertEquals(true, Main.findRandomPoint(rows, columns).getRow() > zero );
        assertEquals(true, Main.findRandomPoint(rows, columns).getRow() <= rows );
    }

    @Test
    void findRandomPointThrowsNullException(){
        assertThrows(NullPointerException.class, () -> {
            Main.findRandomPoint(0, 0);
        });
        assertThrows(NullPointerException.class, () -> {
            Main.findRandomPoint(12, 0);
        }); assertThrows(NullPointerException.class, () -> {
            Main.findRandomPoint(0, 12);
        });

    }

    @Test
    void validateInputTrue() {
        assertEquals(true,  Main.validateInput(50));
    }

    @Test
    void validateInputFalseUpperRange(){
        assertEquals(false, Main.validateInput(120));
    }

    @Test
    void validateInputFalselowerRange(){
        assertEquals(false, Main.validateInput(1));
    }
    @Test
    void validateInputFalseZero(){
        assertEquals(false, Main.validateInput(0));
    }
}