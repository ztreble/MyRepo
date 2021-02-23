package ProgrammingExam1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
//import org.junit.jupiter.api.MethodOrderer;

import java.util.ArrayList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

class PE1Test {
    Draw blankCanvas = new Draw("Programmign Exam1");

    @Test
    void testNestedCircle_1() {
        String expected = "[0.4, 0.35, 0.3, 0.25, 0.2, 0.15, 0.1, 0.05, 0.0]";
        String actual = PE1.nestedCircle (0.5, 0.5, 0.4, 0.05,  blankCanvas, "");
        String message = "\"" + expected+ "\""+ "expected but " + "\"" + actual+ "\""+ "is given!";

        assertEquals(expected, actual, message);
    }

    @Test
    void testSquares_1() {
        String[] expected = {"[0.7, 0.7]", "[0.7, 0.3]", "[0.3, 0.7]", "[0.3, 0.3]"};
        String actual = PE1.squares(0.5, 0.5, 0.2, 2, blankCanvas);
        String message = "\"";
        for (int i = 0; i < expected.length; i++)
            message = message + expected[i];
        message = message + "\""+ "expected but " + "\"" + actual+ "\""+ "is given!";
        boolean equal = true;
        for (int i = 0; i < expected.length; i++)
            if (!actual.contains(expected[i])) {
                equal = false;
                break;
            }

        assertTrue(equal, message);
    }


    @Test
    void testDrillPoints_1() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("[0.1, 0.1]");
        expected.add("[0.9, 0.1]");
        expected.add("[0.5, 0.9]");
        Point p1 = new Point (0.1, 0.1);
        Point p2 = new Point(0.9, 0.1);
        Point p3 = new Point (0.5, 0.9);
        String message = "";
        ArrayList<String> actual = new ArrayList<String>();
        actual = PE1.drillPoints(p1, p2, p3, 1, blankCanvas, actual);
        boolean equal = (expected.size() == actual.size()) && expected.containsAll(actual);

        if (expected.size() ==  actual.size()) {
            message = "The expected points are not found.";
        } else {
            message = "The number of points is not as expected.";
        }

        assertTrue(equal, message);
    }

    @Test
    void testDrillPoints_3() {
        Point p1 = new Point (0.1, 0.1);
        Point p2 = new Point(0.9, 0.1);
        Point p3 = new Point (0.5, 0.9);
        String message = "";
        ArrayList<String> actual = new ArrayList<String>();
        actual = PE1.drillPoints(p1, p2, p3, 0, blankCanvas, actual);
        boolean equal = (actual.size() == 0);

        message = "The list should be empty.";

        assertTrue(equal, message);
    }

}

