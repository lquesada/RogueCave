package test.com.elezeta.roguecave;

import static org.junit.Assert.*;

import org.junit.Test;

import com.elezeta.roguecave.Constants;

public class EntityTest {
	
	public void testShortestMovingDistance(float l1x1,float l1y1,float l1x2,float l1y2,float l2x1,float l2y1,float l2x2,float l2y2,double expected) {

		Line l1;
		Line l2;
		
		l1 = new Line(l1x1,l1y1,l1x2,l1y2);
		l2 = new Line(l2x1,l2y1,l2x2,l2y2);
		assertEquals(expected,l1.shortestMovingDistanceTo(l2),Constants.EPS);

		l1 = new Line(l1x1,l1y1,l1x2,l1y2);
		l2 = new Line(l2x1,l2y1,l2x2,l2y2);
		assertEquals(expected,l2.shortestMovingDistanceTo(l1),Constants.EPS);

		l1 = new Line(l1x1,l1y1,l1x2,l1y2);
		l2 = new Line(l2x1,l2y1,l2x2,l2y2);
		assertEquals(expected,l1.shortestMovingDistanceTo(l2),Constants.EPS);

		l1 = new Line(l1x1,l1y1,l1x2,l1y2);
		l2 = new Line(l2x1,l2y1,l2x2,l2y2);
		assertEquals(expected,l2.shortestMovingDistanceTo(l1),Constants.EPS);

		l1 = new Line(l1y1,l1x1,l1y2,l1x2);
		l2 = new Line(l2y1,l2x1,l2y2,l2x2);
		assertEquals(expected,l1.shortestMovingDistanceTo(l2),Constants.EPS);

		l1 = new Line(l1y1,l1x1,l1y2,l1x2);
		l2 = new Line(l2y1,l2x1,l2y2,l2x2);
		assertEquals(expected,l2.shortestMovingDistanceTo(l1),Constants.EPS);

		l1 = new Line(l1y1,l1x1,l1y2,l1x2);
		l2 = new Line(l2y1,l2x1,l2y2,l2x2);
		assertEquals(expected,l1.shortestMovingDistanceTo(l2),Constants.EPS);

		l1 = new Line(l1y1,l1x1,l1y2,l1x2);
		l2 = new Line(l2y1,l2x1,l2y2,l2x2);
		assertEquals(expected,l2.shortestMovingDistanceTo(l1),Constants.EPS);

		assertEquals(0,l2.shortestMovingDistanceTo(l2),Constants.EPS);
		assertEquals(0,l1.shortestMovingDistanceTo(l1),Constants.EPS);
		}

	public void testDistance(float l1x,float l1y,float l2x,float l2y,double expected) {

		Line l1;
		Line l2;
		
		l1 = new Line(l1x,l1y);
		l2 = new Line(l2x,l2y);
		assertEquals(expected,l1.distanceTo(l2),Constants.EPS);

		l1 = new Line(l1y,l1x);
		l2 = new Line(l2y,l2x);
		assertEquals(expected,l1.distanceTo(l2),Constants.EPS);
		
		l1 = new Line(l1x,l1y);
		l2 = new Line(l2x,l2y);
		assertEquals(expected,l2.distanceTo(l1),Constants.EPS);

		l1 = new Line(l1y,l1x);
		l2 = new Line(l2y,l2x);
		assertEquals(expected,l2.distanceTo(l1),Constants.EPS);
		
		assertEquals(0,l2.distanceTo(l2),Constants.EPS);
		assertEquals(0,l1.distanceTo(l1),Constants.EPS);
	}

	public void testCollision(float l1x1,float l1y1,float l1x2,float l1y2,float radius1,float l2x1,float l2y1,float l2x2,float l2y2,float radius2,boolean expected) {

		Line l1;
		Line l2;

		l1 = new Line(l1x1,l1y1,l1x2,l1y2,radius1);
		l2 = new Line(l2x1,l2y1,l2x2,l2y2,radius2);
		assertEquals(expected,l1.isCollidingWith(l2));
		
		assertEquals(true,l1.isCollidingWith(l1));
		assertEquals(true,l2.isCollidingWith(l2));
	}
	
	@Test
	public void shortestMovingDistanceTest1() {
		testShortestMovingDistance(0f,0f,0f,1f,1f,0f,1f,1f,1d);
	}
	
	@Test
	public void shortestMovingDistanceTest2() {
		testShortestMovingDistance(0f,0f,0f,1f,2f,0f,2f,1f,2d);
	}
	
	@Test
	public void shortestMovingDistanceTest3() {
		testShortestMovingDistance(0f,0f,0f,1f,0.5f,0.5f,1.5f,0.5f,0.5d);
	}
	
	@Test
	public void shortestMovingDistanceTest4() {
		testShortestMovingDistance(0f,0f,0f,1f,0.5f,0.5f,1.5f,0.5f,0.5d);
	}
	
	@Test
	public void shortestMovingDistanceTest5() {
		testShortestMovingDistance(0f,0f,1f,0f,2f,1f,2f,2f,Math.sqrt(2));
	}
	
	@Test
	public void shortestMovingDistanceTest6() {
		testShortestMovingDistance(0f,0f,1f,0f,1f,1f,1f,2f,1d);
	}
	
	@Test
	public void shortestMovingDistanceTest7() {
		testShortestMovingDistance(10f,12f,12f,12f,11.5f,13f,10f,15f,1d);
	}

	@Test
	public void shortestMovingDistanceTest8() {
		testShortestMovingDistance(0f,0f,2f,0f,1f,1f,1f,-1f,0d);
	}
	
	@Test
	public void shortestMovingDistanceTest9() {
		testShortestMovingDistance(0f,0f,2f,0f,0f,1f,0f,-1f,0d);
	}

	@Test
	public void shortestMovingDistanceTest10() {
		testShortestMovingDistance(0f,0f,1f,0f,5f,7f,6f,8f,Math.sqrt(7*7+4*4));
	}

	@Test
	public void shortestMovingDistanceTest11() {
		testShortestMovingDistance(32f,32f,-32f,-32f,0f,0f,0f,0f,0f);
	}

	@Test
	public void shortestMovingDistanceTest12() {
		testShortestMovingDistance(32f,32f,-16f,-16f,0f,0f,0f,0f,0f);
	}

	@Test
	public void shortestMovingDistanceTest13() {
		testShortestMovingDistance(30f,30f,-15f,-15f,0f,0f,0f,0f,0f);
	}

	@Test
	public void shortestMovingDistanceTest14() {
		testShortestMovingDistance(-1f,-1f,1f,1f,0f,0f,0f,0f,0f);
	}

	@Test
	public void shortestMovingDistanceTest15() {
		testShortestMovingDistance(-1f,0f,1f,0f,-0.1f,0f,0f,0f,0f);
	}

	@Test
	public void shortestMovingDistanceTest16() {
		testShortestMovingDistance(-1f,3f,1f,3f,0f,3f,0f,3f,0f);
	}

	@Test
	public void shortestMovingDistanceTest17() {
		testShortestMovingDistance(-1.1f,3f,1.1f,3f,0f,3f,0f,3f,0f);
	}

	@Test
	public void shortestMovingDistanceTest18() {
		testShortestMovingDistance(-1f,0f,1f,0f,0f,-1f,0f,1f,0f);
	}
	
	@Test
	public void distanceTest1() {
		testDistance(0f,0f,0f,0f,0d);
	}

	@Test
	public void distanceTest2() {
		testDistance(1f,0f,0f,0f,1d);
	}
	
	@Test
	public void distanceTest3() {
		testDistance(1f,0f,0f,1f,Math.sqrt(2));
	}

	@Test
	public void distanceTest4() {
		testDistance(1f,0f,5f,7f,Math.sqrt(4*4+7*7));
	}

	@Test
	public void collisionTest1() {
		testCollision(0f,0f,0f,0f,0.5f,0f,1f,1f,1f,0.50f,true);
	}

	@Test
	public void collisionTest2() {
		testCollision(0f,0f,0f,0f,0.5f,0f,1f,1f,1f,0.49f,false);
	}

	@Test
	public void collisionTest3() {
		testCollision(0f,0f,0f,0f,0.01f,0f,0f,0f,0f,0.01f,true);
	}

	@Test
	public void collisionTest4() {
		testCollision(30f,30f,-30f,-30f,1f,0f,0f,0f,0f,1f,true);
	}
	

}
