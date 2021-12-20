package shapes;

import java.util.ArrayList;

public class IdentifyShapes {

    // X,Y,Z coordinate variables
    private Coords P1;
    private Coords P2;
    private Coords P3;
    private Coords P4;
    private Coords P5;
    private Coords P6;
    private Coords P7;
    private Coords P8;

    private final ArrayList<Coords> coordsList;

    // Integers for measuring distance
    private int P1_P2;
    private int P2_P3;
    private int P3_P4;
    private int P4_P1;
    private int P5_P6;
    private int P6_P7;
    private int P7_P8;
    private int P8_P5;
    private int P1_P5;
    private int P2_P6;
    private int P3_P7;
    private int P4_P8;


    public IdentifyShapes(ArrayList<Coords> coordsList) {
        this.coordsList = coordsList;

        switch (coordsList.size()) {
            case 8:
                P8 = coordsList.get(7);
            case 7:
                P7 = coordsList.get(6);
            case 6:
                P6 = coordsList.get(5);
            case 5:
                P5 = coordsList.get(4);
            case 4:
                P4 = coordsList.get(3);
            case 3:
                P3 = coordsList.get(2);
            case 2:
                P2 = coordsList.get(1);
            case 1:
                P1 = coordsList.get(0);
        }

        switch (coordsList.size()) {
            case 8:
                P5_P6 = getSquaredDistance(P5, P6);
                P6_P7 = getSquaredDistance(P6, P7);
                P7_P8 = getSquaredDistance(P7, P8);
                P8_P5 = getSquaredDistance(P8, P5);
                P1_P5 = getSquaredDistance(P1, P5);
                P2_P6 = getSquaredDistance(P2, P6);
                P3_P7 = getSquaredDistance(P3, P7);
                P4_P8 = getSquaredDistance(P4, P8);
            case 4:
                P1_P2 = getSquaredDistance(P1, P2);
                P2_P3 = getSquaredDistance(P2, P3);
                P3_P4 = getSquaredDistance(P3, P4);
                P4_P1 = getSquaredDistance(P4, P1);
        }
    }

    public String getShapeName() {
        String name = "";

        switch (coordsList.size()) {
            case 0:
                name = "none";
                break;
            case 1:
                name = "Dot";
                break;
            case 2:
                name = "Line";
                break;
            case 3:
                name = "Triangle";
                break;
            case 4:
                if (checkIfSquare()) {
                    name = "Square";
                } else if (checkIfRectangle()) {
                    name = "Rectangle";
                } else if (checkIfParallelogram()) {
                    name = "Parallelogram";
                } else if (checkIf2dShape()) {
                    name = "2d Shape";
                }
                break;
            case 5:
                if (checkIfPyramid()) {
                    name = "Pyramid";
                }
                break;
            case 8:
                if (checkIfCube()) {
                    name = "Cube";
                } else if (checkIfRectPrism()) {
                    name = "Rectangular Prism";
                } else if (!checkIf2dShape()) {
                    name = "3d Shape";
                }
                break;

        }
        return name;

    }



    private boolean checkIfRectPrism() {

        if (!checkLengthOppSides(P1_P2, P2_P3, P3_P4, P4_P1)) {
            return false;
        }

        if (!checkLengthOppSides(P5_P6, P6_P7, P7_P8, P8_P5)) {
            return false;
        }
        return equalDistance(P1_P5, P2_P6, P3_P7, P4_P8);
    }

    private boolean checkIfCube() {
        return equalDistance(
                P1_P2, P2_P3, P3_P4, P4_P1,
                P5_P6, P6_P7, P7_P8, P8_P5,
                P1_P5, P2_P6, P3_P7, P4_P8);
    }

    private boolean checkIfPyramid() {

        if (checkIfSquare() || checkIfRectangle()) {
            return P5.z > P1.z;
        }
        return false;

    }

    private boolean checkIf2dShape() {

        int xPlane = 0;
        int yPlane = 0;
        int zPlane = 0;

        for (Coords coord : coordsList) {

            if (coord.x != 0) {
                xPlane = 1;
            }

            if (coord.y != 0) {
                yPlane = 1;
            }

            if (coord.z != 0) {
                zPlane = 1;
            }
        }
        return xPlane + yPlane + zPlane == 2;
    }


    private boolean checkIfParallelogram() {

        return checkLengthOppSides(P1_P2, P2_P3, P3_P4, P4_P1) &&
                calcPointOnPlane() == 2;

    }

    private boolean checkIfRectangle() {

        return checkLengthOppSides(P1_P2, P2_P3, P3_P4, P4_P1) &&
                calcPointOnPlane() == 4;
    }

    private boolean checkIfSquare() {

        return equalDistance(P1_P2, P2_P3, P3_P4, P4_P1);

    }

    private boolean equalDistance(int... distances) {
        for (int i = 1; i < distances.length; i++) {
            int first = distances[i-1];
            int second = distances[i];
            if (first != second) {
                return false;
            }
        }
        return true;
    }

    private int getSquaredDistance(Coords a, Coords b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y) + (a.z - b.z) * (a.z - b.z);
    }

    private boolean checkLengthOppSides(int d1, int d2, int d3, int d4) {
        return d1 == d3 && d2 == d4;
    }


    private int calcPointOnPlane() {

        int xPlane = 0;
        int yPlane = 0;
        int zPlane = 0;

        for (int i = 0; i < coordsList.size(); i++) {
            int j = i+1 == coordsList.size() ? 0 : i+1;
            Coords first = coordsList.get(i);
            Coords second = coordsList.get(j);

            if (first.x == second.x && first.x != 0) {
                xPlane += 1;
            }

            if (first.y == second.y && first.y != 0) {
                yPlane += 1;
            }

            if (first.z == second.z && first.z != 0) {
                zPlane += 1;
            }
        }


        return xPlane + yPlane + zPlane;
    }

}
