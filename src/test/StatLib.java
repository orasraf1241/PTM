package test;


public class StatLib {

	

	// simple average
	public static float avg(float[] x){
		float avg =0;

		for (float v : x) {
			avg += v;
		}
		avg=avg/x.length;
		return avg;
	}

	// returns the variance of X and Y
	public static float var(float[] x){

		float avg = avg(x);
		float variance = 0;
		for (float v : x) {
			variance += Math.pow(v - avg, 2);
		}
		variance /= x.length;

		return variance;
	}

	// returns the covariance of X and Y
	public static float cov(float[] x, float[] y){

		float cov =0;
		float avgX = avg(x);
		float avgY = avg(y);
		for (int i = 0; i < x.length; i++) {
			cov += (x[i]-avgX)*(y[i]- avgY);
		}
		cov = cov/x.length;

		return cov;
	}


	// returns the Pearson correlation coefficient of X and Y
	public static float pearson(float[] x, float[] y){

		float result = cov(x,y);

		result /=Math.sqrt(var(x))*Math.sqrt(var(y));


		return result;
	}

	// performs a linear regression and returns the line equation
	public static Line linear_reg(Point[] points){
		float a;
		float[] x = new float[points.length];
		float[] y = new float[points.length];

		for (int i = 0; i < points.length; i++) {
			x[i]=  points[i].x;
			y[i]=  points[i].y;
		}
		a=cov(x,y)/var(x);
		float b=avg(y)-a*avg(x);
		return new Line(a,b);
	}

	// returns the deviation between point p and the line equation of the points
	public static float dev(Point p,Point[] points){
		Line line = linear_reg(points);
		float dev = line.f(p.x)-p.y;
		if (dev < 0){
			dev *=-1;
		}

		return dev;
	}

	// returns the deviation between point p and the line
	public static float dev(Point p,Line l){

		float dev = l.f(p.x)-p.y;
		if (dev < 0){
			dev *=-1;
		}
		return dev;
	}
	
}
