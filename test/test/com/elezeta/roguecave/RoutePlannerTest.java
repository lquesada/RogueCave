package test.com.elezeta.roguecave;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.elezeta.roguecave.Constants;
import com.elezeta.roguecave.data.SpriteID;
import com.elezeta.roguecave.world.Level;
import com.elezeta.roguecave.world.RoutePlan;
import com.elezeta.roguecave.world.RoutePlanner;
import com.elezeta.roguecave.world.Tile;

public class RoutePlannerTest {

	Level level1;

	Level level2;
	
	public RoutePlannerTest() {
		char[][] map1 = new char[10][10];
		        //0123456789
		map1[0] = "....WWWWWW".toCharArray();
		map1[1] = "....W..WWW".toCharArray();
		map1[2] = "....W.....".toCharArray();
		map1[3] = "..WWWWW...".toCharArray();
		map1[4] = "..WWWWWW..".toCharArray();
		map1[5] = "..WWWWWW..".toCharArray();
		map1[6] = "..WWWWWW..".toCharArray();
		map1[7] = "...WW.....".toCharArray();
		map1[8] = "WWWWW.....".toCharArray();
		map1[9] = "WWWWW.....".toCharArray();
		Map<Character,Tile> tiles1 = new HashMap<Character,Tile>();
		tiles1.put('.',new Tile(SpriteID.voidTile,false));
		tiles1.put('W',new Tile(SpriteID.stoneTile,SpriteID.stoneWall,true));
		level1 = new Level(map1,0,0,tiles1,tiles1.get('.'),64f,64f,64f);

		char[][] map2 = new char[0][0];
		Map<Character,Tile> tiles2 = new HashMap<Character,Tile>();
		tiles2.put('W',new Tile(SpriteID.stoneTile,SpriteID.stoneWall,true));

		level2 = new Level(map2,0,0,tiles2,tiles2.get('W'),64f,64f,64f);

	}
	
	/*
	private void testRoute(float i1,float j1,float i2,float j2,float r,int redirect,int hit,float fi,float fj) {
		PlaneRoute pr = PlaneRoute.checkRoute(level1,i1,j1,i2,j2,r,redirect);
		assertEquals(hit,pr.getHit());
		assertEquals(fi,pr.getI()));
		assertEquals(fj,pr.getJ()));
	}

	private void testRouteEqual(float i1,float j1,float i2,float j2,float r,int redirect,int hit) {
		PlaneRoute pr = PlaneRoute.checkRoute(level1,i1,j1,i2,j2,r,redirect);
		assertEquals(hit,pr.getHit());
		assertEquals(i2,pr.getI()));
		assertEquals(j2,pr.getJ()));
	}*/

	private void testRouteEqualTwo(Level level,float i1,float j1,float i2,float j2,float r,int redirect,int hit) {
		RoutePlan pr = RoutePlanner.checkRoute(level,i1,j1,i2,j2,r,redirect);
		assertEquals(hit,pr.getHit());
		assertEquals(i2,pr.getI(),Constants.EPS);
		assertEquals(j2,pr.getJ(),Constants.EPS);
		RoutePlan pr2 = RoutePlanner.checkRoute(level,i2,j2,i1,j1,r,redirect);
		assertEquals(hit,pr2.getHit());
		assertEquals(i1,pr2.getI(),Constants.EPS);
		assertEquals(j1,pr2.getJ(),Constants.EPS);
		assertEquals(pr.getLength(),pr2.getLength(),Constants.EPS);
		assertEquals(pr.getLength(),(float)Math.sqrt((i2-i1)*(i2-i1)+(j2-j1)*(j2-j1)),Constants.EPS);
	}

	private void testRouteLength(Level level,float i1,float j1,float i2,float j2,float r,int redirect,int hit,float length) {
		RoutePlan pr = RoutePlanner.checkRoute(level,i1,j1,i2,j2,r,redirect);
		assertEquals(hit,pr.getHit());
		assertEquals(pr.getLength(),length,Constants.EPS);
	}
	
	/*
	private void testRoute1(float i1,float j1,float i2,float j2,float r,int redirect,boolean hit,float fi,float fj) {
		PlaneRoute pr = PlaneRoute.checkRoute(level1,i1,j1,i2,j2,r,redirect);
		assertEquals(hit,pr.getHit()!=PlaneRoute.NO_HIT);
		assertEquals(fi,pr.getI()));
		assertEquals(fi,pr.getJ()));
	}

	private void testRoute1(float i1,float j1,float i2,float j2,float r,int redirect,int hit) {
		PlaneRoute pr = PlaneRoute.checkRoute(level1,i1,j1,i2,j2,r,redirect);
		assertEquals(hit,pr.getHit());
	}

	private void testRoute1(float i1,float j1,float i2,float j2,float r,int redirect,boolean hit) {
		PlaneRoute pr = PlaneRoute.checkRoute(level1,i1,j1,i2,j2,r,redirect);
		assertEquals(hit,pr.getHit()!=PlaneRoute.NO_HIT);
	}*/

	@Test public void planeRouteTest1() {
		testRouteEqualTwo(level1,4.5f, 0.5f, 4.75f, 0.6f, 0.0f, RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.NO_HIT);
	}

	@Test public void planeRouteTest2() {
		testRouteEqualTwo(level1,4.5f, 0.5f, 5.75f, 0.6f, 0.0f, RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.NO_HIT);
	}

	@Test public void planeRouteTest3() {
		testRouteEqualTwo(level1,4.01f, 0.7f, 9.75f, 0.98f, 0.0f, RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.NO_HIT);
	}

	@Test public void planeRouteTest4() {
		testRouteEqualTwo(level1,2.3f, 3.5f, 6.75f, 6.4f, 0.0f, RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.NO_HIT);
	}


	@Test public void planeRouteTest5() {
		testRouteEqualTwo(level1,4.5f, 0.7f, 4.5f, 0.98f, 0.0f, RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.NO_HIT);
	}

	@Test public void planeRouteTest6() {
		testRouteEqualTwo(level1,2.3f, 3.5f, 6.75f, 3.5f, 0.0f, RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.NO_HIT);
	}

	@Test public void planeRouteTest7() {
		testRouteLength(level1,5.5f, 3.5f, 7.5f, 3.5f, 0.0f, RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.HIT_BOTTOM,1.5f);
	}

	@Test public void planeRouteTest8() {
		testRouteLength(level1,4.5f, 3.5f, 7.5f, 3.5f, 0.0f, RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.HIT_BOTTOM,2.5f);
	}

	@Test public void planeRouteTest9() {
		testRouteLength(level1,2.5f, 6.5f, 2.5f, 7.5f, 0.0f, RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.HIT_RIGHT,0.5f);
	}

	@Test public void planeRouteTest10() {
		testRouteLength(level1,2.5f, 5.5f, 2.5f, 7.5f, 0.0f, RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.HIT_RIGHT,1.5f);
	}

	@Test public void planeRouteTest11() {
		testRouteLength(level1,3.5f, 3.5f, 3.5f, 2.5f, 0.0f, RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.HIT_LEFT,0.5f);
	}

	@Test public void planeRouteTest12() {
		testRouteLength(level1,4.5f, 3.5f, 0.5f, 3.5f, 0.0f, RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.HIT_TOP,2.5f);
	}

	@Test public void planeRouteTest13() {
		testRouteEqualTwo(level2,3f, 3f, 4f,4f, 0.0f, RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.NO_HIT);
	}

	@Test public void planeRouteRadiusTest1() {
		testRouteEqualTwo(level1,4.5f, 0.5f, 4.6f, 0.6f, 0.1f, RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.NO_HIT);
	}

	private void randomTest(Level level,float startI, float startJ, float endI, float endJ, float inc) {
		for (float i1 = startI;i1 <= endI;i1 += inc)
			for (float j1 = startJ;j1 <= endJ;j1 += inc)
				for (float i2 = startI;i2 <= endI;i2 += inc)
					for (float j2 = startJ;j2 <= endJ;j2 += inc)
						testRouteEqualTwo(level,i1,j1,i2,j2, 0.0f,RoutePlanner.DO_NOT_REDIRECT,RoutePlanner.NO_HIT);
	}

	@Test public void massiveRandomTest1() {
		randomTest(level1,3.8f,4.8f,4.2f,5.2f,0.1f);
	}
	
	@Test public void massiveRandomTest2() {
		randomTest(level1,3.9f,4.9f,4.1f,5.1f,0.05f);
	}

	@Test public void massiveRandomTest3() {
		randomTest(level1,3.95f,3.95f,4.05f,4.05f,0.05f);
	}

	@Test public void massiveRandomTest4() {
		randomTest(level2,-30f,-30f,30f,30f,3f);
	}

	@Test public void massiveRandomTest5() {
		randomTest(level2,-10f,-10f,10f,10f,1f);
	}

	@Test public void massiveRandomTest6() {
		randomTest(level2,-2f,-2f,1f,2f,0.5f);
	}

	@Test public void massiveRandomTest7() {
		randomTest(level2,3f,3f,4f,4f,0.5f);
	}

}
