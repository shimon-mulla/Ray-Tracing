package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

/**
 * class of Geometries which represents an collection of geometries which could have intersections
 */
public class Geometries implements Intersectable {

    /**
     * list of geometries
     */
    private List<Intersectable> _geometries;

    /**
     * Constructor that sets new empty list of geometries
     */
    public Geometries()
    {
        _geometries = new ArrayList<>();
    }

    /**
     * Constructor that gets list of geometries and add them to the list
     * @param geometries Intersectable objects
     */
    public Geometries(Intersectable... geometries)
    {
        add(geometries);
    }

    /**
     * adding geometries objects to the list
     * @param geometries Intersectable objects
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geo : geometries ) {
            _geometries.add(geo);
        }
    }

    /**
     * calculate the points of the intersections with the given ray to the collections of geometries
     * @param ray Ray which should intersect with the geometries
     * @return List<GeoPoint> which should return null on none point, or list of points that intersect the geometries
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double max) {
        List<GeoPoint> intersections = null;

        for (Intersectable geo : _geometries) {
            List<GeoPoint> tempIntersections = geo.findIntersections(ray, max);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new ArrayList<GeoPoint>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }
}
