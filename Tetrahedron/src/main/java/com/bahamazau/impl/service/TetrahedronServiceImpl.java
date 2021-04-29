package com.bahamazau.impl.service;

import com.bahamazau.impl.entity.Tetrahedron;
import com.bahamazau.impl.entity.dot.Dot;
import com.bahamazau.api.TetrahedronService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TetrahedronServiceImpl implements TetrahedronService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public boolean isBasedOnXY(Tetrahedron tetrahedron) {
        LOGGER.info("Method is based on XY start");

        Dot dot2 = tetrahedron.getDot2();
        Dot dot3 = tetrahedron.getDot3();
        Dot dot4 = tetrahedron.getDot4();

        return isBasedOn(dot2.getZ(), dot3.getZ(), dot4.getZ());
    }

    @Override
    public boolean isBasedOnXZ(Tetrahedron tetrahedron) {
        LOGGER.info("Method is based on XZ start");

        Dot dot2 = tetrahedron.getDot2();
        Dot dot3 = tetrahedron.getDot3();
        Dot dot4 = tetrahedron.getDot4();

        return isBasedOn(dot2.getY(), dot3.getY(), dot4.getY());
    }

    @Override
    public boolean isBasedOnYZ(Tetrahedron tetrahedron) {
        LOGGER.info("Method is based on YZ start");

        Dot dot2 = tetrahedron.getDot2();
        Dot dot3 = tetrahedron.getDot3();
        Dot dot4 = tetrahedron.getDot4();

        return isBasedOn(dot2.getX(), dot3.getX(), dot4.getX());
    }

    private boolean isBasedOn(double coordinate1, double coordinate2, double coordinate3) {
        return coordinate1 == 0 && coordinate2 == 0 && coordinate3 == 0;
    }

    @Override
    public double calculateVolume(Tetrahedron tetrahedron) {
        LOGGER.info("Method to calculate volume of tetrahedron start");
        double height = calculateHeight(tetrahedron);
        double baseArea = calculateBaseArea(tetrahedron);
        double volume = height * baseArea / 3;

        LOGGER.info("Volume of tetrahedron with id=" + tetrahedron.getId() + " : " + volume);
        return volume;
    }

    @Override
    public double calculateSurfaceArea(Tetrahedron tetrahedron) {
        LOGGER.info("Method to calculate surface area of tetrahedron start");

        Dot apexPoint = tetrahedron.getDot1();
        Dot basePoint1 = tetrahedron.getDot2();
        Dot basePoint2 = tetrahedron.getDot3();
        Dot basePoint3 = tetrahedron.getDot4();

        double area1 = calculateFaceArea(apexPoint,basePoint1,basePoint2);
        double area2 = calculateFaceArea(apexPoint,basePoint2,basePoint3);
        double area3 = calculateFaceArea(apexPoint,basePoint3,basePoint1);
        double baseArea = calculateBaseArea(tetrahedron);
        double surfaceArea = area1 + area2 + area3 + baseArea;

        LOGGER.info("Height of tetrahedron with id=" + tetrahedron.getId() + " : " + surfaceArea);
        return surfaceArea;
    }

    @Override
    public double calculateHeight(Tetrahedron tetrahedron) {
        LOGGER.info("Method to calculate height of tetrahedron start");
        Dot apexPoint = tetrahedron.getDot1();
        Dot basePoint1 = tetrahedron.getDot2();
        Dot basePoint2 = tetrahedron.getDot3();
        Dot basePoint3 = tetrahedron.getDot4();

        double height = 0;
        if (basePoint1.getX() == basePoint2.getX() && basePoint2.getX() == basePoint3.getX()) {
            height = Math.abs(apexPoint.getX() - basePoint1.getX());
        }
        if (basePoint1.getY() == basePoint2.getY() && basePoint2.getY() == basePoint3.getY()) {
            height = Math.abs(apexPoint.getY() - basePoint1.getY());
        }
        if (basePoint1.getZ() == basePoint2.getZ() && basePoint2.getZ() == basePoint3.getZ()) {
            height = Math.abs(apexPoint.getZ() - basePoint1.getZ());
        }

        LOGGER.info("Height of tetrahedron with id=" + tetrahedron.getId() + " : " + height);
        return height;
    }

    @Override
    public double calculateBasePerimeter(Tetrahedron tetrahedron) {
        LOGGER.info("Method to calculate base perimeter of tetrahedron start");

        Dot basePoint1 = tetrahedron.getDot2();
        Dot basePoint2 = tetrahedron.getDot3();
        Dot basePoint3 = tetrahedron.getDot4();

        double baseEdge1 = calculateDistanceBetweenPoints(basePoint1, basePoint2);
        double baseEdge2 = calculateDistanceBetweenPoints(basePoint2, basePoint3);
        double baseEdge3 = calculateDistanceBetweenPoints(basePoint3, basePoint1);
        double perimeter = baseEdge1 + baseEdge2 + baseEdge3;

        LOGGER.info("Base perimeter of tetrahedron with id=" + tetrahedron.getId() + " : " + perimeter);
        return perimeter;
    }

    @Override
    public double calculateBaseArea(Tetrahedron tetrahedron) {
        LOGGER.info("Method to calculate base area of tetrahedron start");

        double area = calculateFaceArea(tetrahedron.getDot2(), tetrahedron.getDot3(), tetrahedron.getDot4());

        LOGGER.info("Base area of tetrahedron with id=" + tetrahedron.getId() + " : " + area);
        return area;
    }

    private double calculateFaceArea(Dot point1, Dot point2, Dot point3) {
        double edge1 = calculateDistanceBetweenPoints(point1, point2);
        double edge2 = calculateDistanceBetweenPoints(point2, point3);
        double edge3 = calculateDistanceBetweenPoints(point3, point1);
        double p = (edge1 + edge2 + edge3) / 2;

        return Math.sqrt(p * (p - edge1) * (p - edge2) * (p - edge3));
    }

    @Override
    public double calculateDistanceBetweenPoints(Dot a, Dot b) {
        double x = a.getX() - b.getX();
        double y = a.getY() - b.getY();
        double z = a.getZ() - b.getZ();

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

}