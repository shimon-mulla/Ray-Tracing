/**
 *
 */
package unittests;

import geometries.Plane;
import org.junit.Test;

import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 *
 */
public class ReflectionRefractionTest {


    @Test
    public void specialCreationGeometriesTest() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));


        Point3D p1 = new Point3D(300,100,6000);
        Point3D p2 = new Point3D(0,-200,6000);


        Vector v1 = p1.subtract(p2);

        //Vector vSub =

        scene.addGeometries(
                //new Sphere(new Color(java.awt.Color.YELLOW), new Material(0.8, 0.2, 200, 0, 0), new Point3D(300, 0, 3000), 80),
                new Triangle(new Color(java.awt.Color.BLACK), new Material(0.5,0,60,0.5,0),
                        new Point3D(-300,100,6000), new Point3D(300,100,6000), new Point3D(0,-200,6000)),
                new Triangle(new Color(java.awt.Color.BLACK), new Material(0.5,0,60,0.5,0),
                        new Point3D(0,200,6000), new Point3D(-300,-100,6000), new Point3D(300,-100,6000)),
                new Triangle(new Color(java.awt.Color.YELLOW), new Material(0.1,0,0,0,0.9),
                        new Point3D(-150,50,8000), new Point3D(150,50,8000), new Point3D(0,-100,8000)),
                new Triangle(new Color(java.awt.Color.YELLOW), new Material(0.1,0,0,0,0.9),
                        new Point3D(0,100,8000), new Point3D(-150,-50,8000), new Point3D(150,-50,8000)),
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0),
                        new Point3D(20, -30, 200), 30)

        );
//        new Color(1000, 600, 0)
        scene.addLights(/*new SpotLight(new Color(java.awt.Color.YELLOW), new Point3D(200, -100, -500), 1,
                0.0004, 0.0000006, new Vector(-1, 1, 2)),*/
                new SpotLight(new Color(java.awt.Color.YELLOW), new Point3D(500, 100, 2000), 1,
                        0.0004, 0.0000006, new Vector(-1,1,2)),
                new SpotLight(new Color(700, 400, 400),
                        new Point3D(60, -50, 0), 1, 4E-5, 2E-7, new Vector(0, 0, 1))/*,
                new DirectionalLight(new Color(java.awt.Color.YELLOW), new Vector(-1, 1, 2))*/);

        ImageWriter imageWriter = new ImageWriter("specialCreationGeometries", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void specialCreationGeometriesTest2() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Plane(new Color(java.awt.Color.BLUE),new Material(0.4, 0.3, 100, 0.3, 0),
                        new Point3D(50,-50,60), new Point3D(50,50,60), new Point3D(-100,0,100))
                //new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), new Point3D(0, 0, 50), 50),
                //new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), new Point3D(0, 0, 50), 25)
                );

        scene.addGeometries(new Triangle(new Color(java.awt.Color.GREEN), new Material(0.5,0.5,120, 0.6,0.6),
                new Point3D(-50,-50,100), new Point3D(-50,50,100), new Point3D(10,0,-100)));

        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), 1,
                0.0004, 0.0000006, new Vector(-1, 1, 2)));

        ImageWriter imageWriter = new ImageWriter("specialCreationGeometries2", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }




    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), new Point3D(0, 0, 50), 50),
                new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), new Point3D(0, 0, 50), 25));

        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), 1,
                0.0004, 0.0000006, new Vector(-1, 1, 2)));

        ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.addGeometries(
                new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 20, 0.5, 0), new Point3D(-950, 900, 1000), 400),
                new Sphere(new Color(100, 20, 20), new Material(0.25, 0.25, 20), new Point3D(-950, 900, 1000), 200),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));

        scene.addLights(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, 750, 150),
                1, 0.00001, 0.000005, new Vector(-1, 1, 4)));

        ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
     *  producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        new Point3D(60, -50, 50), 30));

        scene.addLights(new SpotLight(new Color(700, 400, 400), //
                new Point3D(60, -50, 0), 1, 4E-5, 2E-7, new Vector(0, 0, 1)));

        ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
}