package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.text.DecimalFormat;

public class SimpleLinear {

    private final DecimalFormat df = new DecimalFormat("#.###");


    private SimpleRegression regression;

    private double[][] parameters;

    private int n;
    private double Sxx;

    private double Syy;

    private double Sxy;

    private double SE;

    private double SR;

    private double significanceLevel;
    private double avgX;

    private double avgY;

    private double slope;

    private double intercept;

    private double squareR;


    public SimpleLinear(SimpleRegression regression, int n, double Sxx, double Syy, double Sxy, double SE, double SR, double avgX, double avgY, double squareR, double slope, double intercept, double[][] parameters, double significanceLevel) {
        this.regression=regression;
        this.n=n;
        this.Sxx = Sxx;
        this.Syy = Syy;
        this.Sxy = Sxy;
        this.SE = SE;
        this.SR = SR;
        this.avgX = avgX;
        this.avgY = avgY;
        this.squareR = squareR;
        this.slope = slope;
        this.intercept = intercept;
        this.parameters = parameters;
        this.significanceLevel = significanceLevel;
    }

    public SimpleRegression getRegression() {
        return regression;
    }

    public SimpleLinear() {

    }

    /**
     * This method gets the regression slope and intercept
     *
     * @return - double[]
     */
    public double[] getCoefficients(){
        return new double[]{this.slope, this.intercept};
    }

    /**
     * This method gets the Sum of X's
     *
     * @return - double
     */
    public double getSxx() {
        return Sxx;
    }

    /**
     * This method gets the Sum of X's times Y's
     *
     * @return - double
     */

    public double getSxy() {
        return Sxy;
    }

    /**
     * This method gets the Sum of Y's
     *
     * @return - double
     */
    public double getSyy() {
        return Syy;
    }

    /**
     * This method gets the X mean
     *
     * @return - double
     */
    public double getAvgX() {
        return avgX;
    }

    /**
     * This method gets the Y mean
     *
     * @return - double
     */

    public double getAvgY() {
        return avgY;
    }



    @Override
    public String toString() {
        return "-----RegressionModel-----" +
                "\nn = " + this.n+
                "\nSxx= " + df.format(Sxx) +
                "\nSyy= " + df.format(Syy) +
                "\nSxy= " + df.format(Sxy) +
                "\nSE= " + df.format(SE) +
                "\nSR= " + df.format(SR) +
                "\nST= " +df.format(SR+SE) +
                "\navgX= " + df.format(avgX) +
                "\navgY= " + df.format(avgY) +
                "\nslope= " + df.format(slope) +
                "\nintercept= " + df.format(intercept) +
                "\nR^2 = " + df.format(squareR) +
                "\nR =" + df.format(Math.sqrt(squareR)) +
                "\n\n\n Equation -> y = " + df.format(this.intercept) + " + (" + df.format(this.slope) + ")X + " + "";
    }

    /**
     * This method predicts a Y value
     *
     * @param parameter
     * @return
     */

    public String predictSimple(double parameter){

        if (n-2 != -2) {
            double yVal = this.regression.predict(parameter);
            TDistribution tDistribution = new TDistribution(this.n - 2);
            double criticalValue = tDistribution.inverseCumulativeProbability(1 - this.significanceLevel / 2);
            double s = Math.sqrt(this.SE / (this.n - 2));

        double lowerBound = yVal - criticalValue * (s * Math.sqrt(1+((double) 1/this.n)+ Math.pow((parameter - avgX),2)/this.Sxx));
        double upperBound = yVal + criticalValue * (s * Math.sqrt(1+((double) 1/this.n)+ Math.pow((parameter - avgX),2)/this.Sxx));

        // Output the confidence interval
        if(upperBound > lowerBound){
            return( "\n\nPrediction:" + df.format(yVal) +"\nConfidence Interval ("+ (1-this.significanceLevel)*100 +"%) : ]" + df.format(lowerBound) + ", " + df.format(upperBound) + "[");

        }else{
            return( "\n\nPrediction:" + df.format(yVal) +"\nConfidence Interval ("+ (1-this.significanceLevel)*100 +"%) : ]" + df.format(upperBound) + ", " +  df.format(lowerBound) + "[");

            }
        } else {
            return "[NO DATA]";
        }
    }

    public int getN() {
        return n;
    }

    /**
     * This method creates the anova significance model
     *
     * @return - String
     */
    public String anovaSingificanceModel(){


        double SR = this.SR;
        double SE = this.SE;

        double MSR = SR;
        double MSE = SE/(this.n);
        double f = MSR/MSE;

        FDistribution fd = new FDistribution(1, this.n-2);
        Double fSnedecor = fd.inverseCumulativeProbability(1-this.significanceLevel);



        String message = ("\n||-=-=-=- Significance Model With ANOVA -=-=-=-||"
                + "\n MSR:" + df.format(MSR)
                + "\n MSE:" + df.format(MSE)
                + "\n F0 :" + df.format(f)
                + "\n F de Snedecore : " + df.format(fSnedecor)
                + "\n-=-=-=--=-=-=-"
                + "\nH0 : b = b0"
                + "\nH1 : b != b0"
                + "\n-=-=-=--=-=-=-");

        if(f>fSnedecor){
            message = message + "\nF0 > F de Snedecor\nH0 is rejected -> regression model is significant";
        }else{
            message = message + "\nF0 < F de Snedecor\nH0 is accepted -> regression model is not significant";
        }

        return message;
    }

    /**
     * this method returns the confidence interval for the interference
     *
     * @return - String
     */

    public String calculateInterceptConfidenceInterval(){

        TDistribution tDistribution = new TDistribution(this.n - 1);
        double criticalValue = tDistribution.inverseCumulativeProbability(1 - this.significanceLevel / 2);
        double marginOfError = criticalValue * this.regression.getInterceptStdErr();
        double confidenceIntervalMin = this.regression.getIntercept() - marginOfError;
        double confidenceIntervalMax = this.regression.getIntercept() + marginOfError;


        return "\n\n||-=-=-=- Intercept Confidence Interval -=-=-=-||"+
                "\n Intercept: " + df.format(this.intercept) +
                "\n Intercept Standard Error: " + df.format(this.regression.getInterceptStdErr()) +
                "\n Intercept Confidence Interval (" + ((1-this.significanceLevel)*100) + ") -> ] " + df.format(confidenceIntervalMin) + "; " + df.format(confidenceIntervalMax) + "[";
    }

    /**
     * this method returns the confidence interval for the slope
     *
     * @return - String
     */

    public String calculateSlopeConfidenceInterval(){

        TDistribution tDistribution = new TDistribution(this.n - 2);
        double criticalValue = tDistribution.inverseCumulativeProbability(1 - this.significanceLevel / 2);
        double marginOfError = criticalValue * this.regression.getSlopeStdErr();
        double confidenceIntervalMin = this.regression.getSlope() - marginOfError;
        double confidenceIntervalMax = this.regression.getSlope() + marginOfError;


        return "\n\n||-=-=-=- Slope Confidence Interval -=-=-=-||"+
                "\n Slope : " + df.format(this.slope) +
                "\n Slope Standard Error: " + df.format(this.regression.getSlopeStdErr()) +
                "\n Slope Confidence Interval (" + ((1-this.significanceLevel)*100) + ") -> ] " + df.format(confidenceIntervalMin) + "; " + df.format(confidenceIntervalMax) + "[";
    }

    /**
     * this method returns the hypothesis test for the intercept
     *
     * @return - String
     */

    public String calculateInterceptHyopthesisTest(){

        double t = this.intercept / this.regression.getInterceptStdErr();

        TDistribution tDist = new TDistribution(this.n-2);
        double tc = tDist.inverseCumulativeProbability(1 - this.significanceLevel/2);

        double s = Math.sqrt(this.SE / (this.n-1));

        // Perform hypothesis test
        String message = ("\n\n||-=-=-=- Intercept Hypothesis Test -=-=-=-||"
                + "\n s :" + df.format(s)
                + "\n-=-=-=--=-=-=-"
                + "\nH0 : a = a0"
                + "\nH1 : a != a0"
                + "\n-=-=-=--=-=-=-")
                + "\n t = " + df.format(t)
                + "\n tc =" + df.format(tc);

        if (Math.abs(t) <= tc) {
            message = message + ("\n|t| <= tc \n -> H0 accepted");
        } else {
            message = message + ("\n|t| > tc \n -> H0 rejected");
        }


       return message;
    }

    /**
     * This method returns the forecast prices list
     *
     * @return - String
     */

    public String getForecastList(){
        String message ="\nForecast | Sale";

        for (int i = 0; i < parameters[0].length; i++) {
            String forecast = String.valueOf(df.format(regression.predict(parameters[0][i])));
            message = message + "\n" + forecast + " | " + df.format(parameters[1][i]);
        }
        return message;
    }

    /**
     * this method returns the hypothesis test for the slope
     *
     * @return - String
     */
    public String calculateSlopeHyopthesisTest(){

        double t = this.slope / this.regression.getSlopeStdErr();
        double s = Math.sqrt(this.SE / (this.n-1));
        TDistribution tDist = new TDistribution(this.n-2);

        double tc = tDist.inverseCumulativeProbability(1 - this.significanceLevel/2);


        String message = ("\n\n||-=-=-=- Slope Hypothesis Test -=-=-=-||"
                + "\n s :" + df.format(s)
                + "\n-=-=-=--=-=-=-"
                + "\nH0 : b = b0"
                + "\nH1 : b != b0"
                + "\n-=-=-=--=-=-=-")
                + "\n t = " + df.format(t)
                + "\n tc =" + df.format(tc);

        if (Math.abs(t) <= tc) {
            message = message + ("\n|t| <= tc \n -> H0 accepted");
        } else {
            message = message + ("\n|t| > tc \n -> H0 rejected");
        }


        return message;
    }

    /**
     * This method returns the analysis report
     *
     * @return - String
     */
    public String generateAnalysisReport() {
        if (n - 1 != -1) {
            return this.toString() + this.calculateInterceptConfidenceInterval() + this.calculateSlopeConfidenceInterval() + this.calculateInterceptHyopthesisTest() + this.calculateSlopeHyopthesisTest() + this.anovaSingificanceModel() + this.getForecastList();
        } else {
            return "[NO DATA]";
        }
    }
}
