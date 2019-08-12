package ImportCsv;

public class Point {
	
	public float middlePoint;
	
	public float finalPoint;
	
	public float otherPoint;
	
	public float getMiddlePoint() {
		return middlePoint;
	}

	public void setMiddlePoint(float middlePoint) {
		this.middlePoint = middlePoint;
	}

	public float getFinalPoint() {
		return finalPoint;
	}

	public void setFinalPoint(float finalPoint) {
		this.finalPoint = finalPoint;
	}

	public float getOtherPoint() {
		return otherPoint;
	}

	public void setOtherPoint(float otherPoint) {
		this.otherPoint = otherPoint;
	}

	public float getSumPoint() {
		return sumPoint;
	}

	public void setSumPoint(float sumPoint) {
		this.sumPoint = sumPoint;
	}

	public float sumPoint;
	
	public Point(float middlePoint, float finalPoint, float otherPoint, float sumPoint) {
		this.middlePoint = middlePoint;
		this.finalPoint = finalPoint;
		this.otherPoint = otherPoint;
		this.sumPoint = sumPoint;
	}
	
	public boolean isPassed()
	{
		return this.sumPoint >= 5;
	}
	
}
