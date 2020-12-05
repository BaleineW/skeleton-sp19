public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet target){
        double dx = xxPos - target.xxPos;
        double dy = yyPos - target.yyPos;
        double r = Math.hypot(dx, dy);
        return r;
    }

    public double calcForceExertedBy(Planet target){
        double G = 6.67e-11;
        double F = G * mass * target.mass / Math.pow(this.calcDistance(target), 2);
        return F;
    }

    public double calcForceExertedByX(Planet target){
        double dx = Math.abs(xxPos - target.xxPos);
        double Fx = this.calcForceExertedBy(target) * dx / this.calcDistance(target);
        return Fx;
    }

    public double calcForceExertedByY(Planet target){
        double dy = Math.abs(yyPos - target.yyPos);
        double Fy = this.calcForceExertedBy(target) * dy / this.calcDistance(target);
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] bodies){
        double netFx = 0;
        for (Planet b : bodies){
            if (!this.equals(b)) netFx += this.calcForceExertedByX(b);
        }
        return netFx;
    }

    public double calcNetForceExertedByY(Planet[] bodies){
        double netFy = 0;
        for (Planet b : bodies){
            if (!this.equals(b)) netFy += this.calcForceExertedByY(b);
        }
        return netFy;
    }

    public void update(double dt, double Fx, double Fy){
        double ax = Fx / this.mass;
        double ay = Fy / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

}
