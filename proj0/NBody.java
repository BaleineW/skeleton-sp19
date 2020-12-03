public class NBody {

    public static double readRadius(String name) {
        In in = new In(name);
        in.readInt();
        return in.readDouble();
    }

    /** Return the body in the file */
    public static Body[] readBodies(String name) {
        In in = new In(name);
        int number = in.readInt();
        Body[] bodys = new Body[number];
        in.readDouble();
        for (int i = 0; i < number; i++) {
            bodys[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readString());
        }
        return bodys;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Body[] bodies = NBody.readBodies(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        double time = 0;
        int number = bodies.length;
        while (time <= T) {
            double[] xForces = new double[number];
            double[] yForces = new double[number];
            for (int i = 0; i < number; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < number; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body b : bodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel, bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
    }
}