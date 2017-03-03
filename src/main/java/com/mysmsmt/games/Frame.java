package com.mysmsmt.games;

/**
 * Created with IntelliJ IDEA.
 * User: jerryshea
 * Date: 30/08/12
 * Time: 11:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class Frame {
   private static final int TOTAL_PINS = 10;
   private static final int NO_THROW_YET = -1;
   private int ball1 = NO_THROW_YET;
   private int ball2 = NO_THROW_YET;
   private int ball3 = NO_THROW_YET;

   public Frame(int ball1, int ball2) {
      this(ball1, ball2, NO_THROW_YET);
   }

   public Frame(int ball1, int ball2, int ball3) {
      this.ball1 = ball1;
      this.ball2 = ball2;
      this.ball3 = ball3;
      validateBalls(ball1, ball2, ball3);
   }

   private void validateBalls(int ball1, int ball2, int ball3) {
      checkBall10OrLess(ball1, "Ball 1 too high");
      checkBall10OrLess(ball2, "Ball 2 too high");
      checkBall10OrLess(ball3, "Ball 3 too high");

      if (ball1 + ball2 > TOTAL_PINS) {
         if (! isLegal3BallFrame()) {
            throw new ScoringException("Total of balls 1&2 too high");
         }
      }
   }

   private void checkBall10OrLess(int ball3, String s) {
      if (ball3 > TOTAL_PINS) {
         throw new ScoringException(s);
      }
   }

   public int getTotalScore(Frame nextFrame1, Frame nextFrame2) {
      int score = ball1 + ball2;



      if (nextFrame1 != null) {
         if (this.isStrike()) {
            score += (nextFrame1.ball1 + nextFrame1.ball2);
            if (nextFrame1.ball2 == 0 && nextFrame2 != null) {
               score += nextFrame2.ball1;
            }
         }
         else if (this.isSpare()) {
            score += nextFrame1.ball1;
         }
      }
      else if (this.isLegal3BallFrame()) {
         score += ball3;
      }
      return score;
   }

   public boolean isSpare() {
      return (ball1 + ball2) == TOTAL_PINS;
   }

   public boolean isStrike() {
      return ball1 == TOTAL_PINS;
   }

   public boolean isLegal3BallFrame() {
      return is3BallFrame() && (this.isSpare() || this.isStrike());
   }

   public boolean is3BallFrame() {
      return ball3 > 0;
   }
}
