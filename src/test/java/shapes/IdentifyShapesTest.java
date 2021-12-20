package shapes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IdentifyShapesTest {

    private IdentifyShapes identifyShapes;
    private ArrayList<Coords> coordsList;

    @BeforeEach
    void setup() {
        coordsList = new ArrayList<>();
    }

    @Test
    void should_returnNone_when_EmptyList() {

        identifyShapes = new IdentifyShapes(coordsList);
        String expected = "none";

        assertEquals(expected, identifyShapes.getShapeName());

    }

    @Test
    void should_returnDot_when_SingleCoord() {

        coordsList.add(new Coords(1,0,0));
        identifyShapes = new IdentifyShapes(coordsList);
        String expected = "Dot";

        assertEquals(expected, identifyShapes.getShapeName());

    }

    @Test
    void should_returnLine_when_TwoCoords() {

        coordsList.add(new Coords(1,0,0));
        coordsList.add(new Coords(1,1,0));
        identifyShapes = new IdentifyShapes(coordsList);
        String expected = "Line";

        assertEquals(expected, identifyShapes.getShapeName());

    }

    @Test
    void should_returnTriangle_when_ThreeCoords() {

        coordsList.add(new Coords(1,0,0));
        coordsList.add(new Coords(1,1,0));
        coordsList.add(new Coords(0,1,0));
        identifyShapes = new IdentifyShapes(coordsList);
        String expected = "Triangle";

        assertEquals(expected, identifyShapes.getShapeName());

    }

    @Test
    void should_returnSquare_when_FourCoordsEqualDistance() {

        coordsList.add(new Coords(0,0,0));
        coordsList.add(new Coords(1,0,0));
        coordsList.add(new Coords(1,1,0));
        coordsList.add(new Coords(0,1,0));
        identifyShapes = new IdentifyShapes(coordsList);
        String expected = "Square";

        assertEquals(expected, identifyShapes.getShapeName());

    }

    @Test
    void should_returnRectangle_when_FourCoordsWithTwoSidesEqual() {

        coordsList.add(new Coords(6,4,0));
        coordsList.add(new Coords(13,4,0));
        coordsList.add(new Coords(13,7,0));
        coordsList.add(new Coords(6,7,0));
        identifyShapes = new IdentifyShapes(coordsList);

        String expected = "Rectangle";

        assertEquals(expected, identifyShapes.getShapeName());

    }

    @Test
    void should_returnParallelogram_when_FourCoordsWithTwoSidesEqualDiagonally() {

        coordsList.add(new Coords(2,1,0));
        coordsList.add(new Coords(5,1,0));
        coordsList.add(new Coords(6,5,0));
        coordsList.add(new Coords(3,5,0));
        identifyShapes = new IdentifyShapes(coordsList);

        String expected = "Parallelogram";

        assertEquals(expected, identifyShapes.getShapeName());

    }

    @Test
    void should_returnParallelogram_when_FourCoordsWithTwoSidesEqualDiagonallyDifferentPlanes() {

        coordsList.add(new Coords(0,2,1));
        coordsList.add(new Coords(0,5,1));
        coordsList.add(new Coords(0,6,5));
        coordsList.add(new Coords(0,3,5));
        identifyShapes = new IdentifyShapes(coordsList);

        String expected = "Parallelogram";

        assertEquals(expected, identifyShapes.getShapeName());

    }

    @Test
    void should_return2dShape_when_FourCoordsOnSamePlaneNotEqualDistance() {

        coordsList.add(new Coords(2,1,0));
        coordsList.add(new Coords(7,1,0));
        coordsList.add(new Coords(7,5,0));
        coordsList.add(new Coords(4,5,0));
        identifyShapes = new IdentifyShapes(coordsList);

        String expected = "2d Shape";

        assertEquals(expected, identifyShapes.getShapeName());

    }

    @Test
    void should_returnPyramid_when_FourCoordsMakesSquareOnSamePlaneAndFifthIsOnOtherPlane() {

        coordsList.add(new Coords(4,3,0));
        coordsList.add(new Coords(8,3,0));
        coordsList.add(new Coords(8,7,0));
        coordsList.add(new Coords(4,7,0));
        coordsList.add(new Coords(6,5,4));
        identifyShapes = new IdentifyShapes(coordsList);

        String expected = "Pyramid";

        assertEquals(expected, identifyShapes.getShapeName());

    }

    @Test
    void should_returnPyramid_when_FourCoordsMakesRectangleOnSamePlaneAndFifthIsOnOtherPlane() {

        coordsList.add(new Coords(4,3,0));
        coordsList.add(new Coords(10,3,0));
        coordsList.add(new Coords(10,7,0));
        coordsList.add(new Coords(4,7,0));
        coordsList.add(new Coords(7,5,6));
        identifyShapes = new IdentifyShapes(coordsList);

        String expected = "Pyramid";

        assertEquals(expected, identifyShapes.getShapeName());

    }

    @Test
    void should_returnCube_when_EightCoordsMakeSixEqualSquares() {

        coordsList.add(new Coords(7,3,0));
        coordsList.add(new Coords(10,3,0));
        coordsList.add(new Coords(10,6,0));
        coordsList.add(new Coords(7,6,0));
        coordsList.add(new Coords(7,3,3));
        coordsList.add(new Coords(10,3,3));
        coordsList.add(new Coords(10,6,3));
        coordsList.add(new Coords(7,6,3));
        identifyShapes = new IdentifyShapes(coordsList);

        String expected = "Cube";

        assertEquals(expected, identifyShapes.getShapeName());

    }

    @Test
    void should_returnCube_when_EightCoordsMakeSixEqualSquaresOnOtherPlanes() {

        coordsList.add(new Coords(7,3,3));
        coordsList.add(new Coords(10,3,3));
        coordsList.add(new Coords(10,3,6));
        coordsList.add(new Coords(7,3,6));
        coordsList.add(new Coords(7,6,3));
        coordsList.add(new Coords(10,6,3));
        coordsList.add(new Coords(10,6,6));
        coordsList.add(new Coords(7,6,6));
        identifyShapes = new IdentifyShapes(coordsList);

        String expected = "Cube";

        assertEquals(expected, identifyShapes.getShapeName());

    }

    @Test
    void should_returnRectangularPrism_when_EightCoordsMake3DRectangle() {

        coordsList.add(new Coords(6,3,0));
        coordsList.add(new Coords(11,3,0));
        coordsList.add(new Coords(11,5,0));
        coordsList.add(new Coords(6,5,0));
        coordsList.add(new Coords(6,3,2));
        coordsList.add(new Coords(11,3,2));
        coordsList.add(new Coords(11,5,2));
        coordsList.add(new Coords(6,5,2));
        identifyShapes = new IdentifyShapes(coordsList);

        String expected = "Rectangular Prism";

        assertEquals(expected, identifyShapes.getShapeName());

    }

    @Test
    void should_return3dShape_when_EightCoordsMakeNoMatchingShape() {

        coordsList.add(new Coords(6,4,0));
        coordsList.add(new Coords(10,4,0));
        coordsList.add(new Coords(13,7,0));
        coordsList.add(new Coords(6,7,0));
        coordsList.add(new Coords(6,4,6));
        coordsList.add(new Coords(10,4,6));
        coordsList.add(new Coords(13,7,6));
        coordsList.add(new Coords(6,7,6));
        identifyShapes = new IdentifyShapes(coordsList);

        String expected = "3d Shape";

        assertEquals(expected, identifyShapes.getShapeName());

    }
}