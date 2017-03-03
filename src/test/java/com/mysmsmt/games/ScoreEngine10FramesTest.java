package com.mysmsmt.games;

import junit.framework.Assert;
import junit.framework.TestCase;


public class ScoreEngine10FramesTest extends TestCase {

   private ScoringEngine scoringEngine = new ScoringEngineImpl();

   /**
    * See EXAMPLE 1 on http://breaktimetenpin.com/tprules.php
    */
   public void testExample1Scenario() {
      runOneFrame(9, 0, 9);
      runOneFrame(3, 5, 17);
      runOneFrame(6, 1, 24);
      runOneFrame(3, 6, 33);
      runOneFrame(8, 1, 42);
      runOneFrame(5, 3, 50);
      runOneFrame(2, 5, 57);
      runOneFrame(8, 0, 65);
      runOneFrame(7, 1, 73);
      runOneFrame(8, 1, 82);
   }

   /**
    * See EXAMPLE 2 on http://breaktimetenpin.com/tprules.php
    */
   public void testExample2ScenarioSpares() {
      runOneGameExample2(new Frame(8, 0), 121);
   }

   /**
    * See EXAMPLE 2 on http://breaktimetenpin.com/tprules.php
    */
   public void testExample2ScenarioStrikesEndWithSpare() {
      runOneGameExample2(new Frame(8, 2, 8), 131);
   }

   private void runOneGameExample2(Frame f, int finalScore) {
      acceptNewFrame(9, 0);
      acceptNewFrame(3, 7);
      acceptNewFrame(6, 1);
      acceptNewFrame(3, 7);
      acceptNewFrame(8, 1);
      acceptNewFrame(5, 5);
      acceptNewFrame(0, 10);
      acceptNewFrame(8, 0);
      acceptNewFrame(7, 3);
      runOneFrame(f);

      Assert.assertEquals(finalScore, scoringEngine.totalScore());
   }

   /**
    * See EXAMPLE 3 on http://breaktimetenpin.com/tprules.php
    */
   public void testExample3ScenarioStrikes() {
      runOneGameExample3(new Frame(8, 0), 161, 169);
   }

   /**
    * See EXAMPLE 3 on http://breaktimetenpin.com/tprules.php
    */
   public void testExample3ScenarioStrikesEndWithStrikes() {
      runOneGameExample3(new Frame(10, 10, 10), 163, 193);
   }

   /**
    * See EXAMPLE 3 on http://breaktimetenpin.com/tprules.php
    */
   public void testExample3ScenarioIllegal3BallFrame() {
      try {
         runOneGameExample3(new Frame(4, 5, 4), 999, 999);
         Assert.fail("Should not have got here");
      }
      catch (ScoringException se) {
         // ignore
      }
   }

   private void runOneGameExample3(Frame f, int lastButOnefinalScore, int finalScore) {
      acceptNewFrame(10, 0);
      acceptNewFrame(3, 7);
      acceptNewFrame(6, 1);
      acceptNewFrame(10, 0);
      acceptNewFrame(10, 0);
      acceptNewFrame(10, 0);
      acceptNewFrame(2, 8);
      acceptNewFrame(9, 0);
      acceptNewFrame(7, 3);
      runOneFrame(f);

      assertTotalFrameScore(0, 20);
      assertTotalFrameScore(1, 36);
      assertTotalFrameScore(2, 43);
      assertTotalFrameScore(3, 73);
      assertTotalFrameScore(4, 95);
      assertTotalFrameScore(5, 115);
      assertTotalFrameScore(6, 134);
      assertTotalFrameScore(7, 143);
      assertTotalFrameScore(8, lastButOnefinalScore);
      assertTotalFrameScore(9, finalScore);
   }

   private void assertTotalFrameScore(int frameIndex, int score) {
      Assert.assertEquals(score, scoringEngine.totalScore(frameIndex));
   }

   private void runOneFrame(int ball1, int ball2, int totalScore) {
      acceptNewFrame(ball1, ball2);
      Assert.assertEquals(totalScore, scoringEngine.totalScore());
   }

   private void acceptNewFrame(int ball1, int ball2) {
      Frame f = new Frame(ball1, ball2);
      runOneFrame(f);
   }

   private void runOneFrame(Frame f) {
      scoringEngine.accept(f);
   }
}
