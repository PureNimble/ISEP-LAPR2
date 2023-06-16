package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class SimpleLinearRegression implements RegressionModel{

    @Override
    public SimpleLinear getRegressionModel(double[][] parameters, double significanceLevel) {


        SimpleRegression regression = new SimpleRegression();


        for (int i = 0; i < parameters[0].length; i++) {
            regression.addData(parameters[0][i], parameters[1][i]);
        }


        SimpleLinear simpleRegression = createRegressionModel(parameters, regression, significanceLevel);



        // slope coefficiente data
        //double standardError = regression.getSlopeStdErr();
        //String[] slopeConfidenceInterval95 = simpleRegression.calculateConfidenceInterval(standardError, simpleRegression.getSlope());
        //String[] slopeHypothesisTest = simpleRegression.calculateHyopthesisTest(regression.getSlope(), standardError);
        //standardError = regression.getInterceptStdErr();
        //String[] interceptConfidenceInterval95 = simpleRegression.calculateConfidenceInterval(standardError, simpleRegression.getIntercept());
        //String[] interceptHypothesisTest = simpleRegression.calculateHyopthesisTest(regression.getIntercept(),standardError);
        //String[] anovaModel = simpleRegression.anovaSingificanceModel();



        return simpleRegression;
    }
/*
    private String[] anovaSingificanceModel(SimpleLinear regression, int N, double significanceLevel){
        double SR = regression.getSR();
        double SE = regression.getSE();
        double ST = SE + SR;

        double MSR = SR;
        double MSE = SE/(N-2);
        double f = MSR/MSE;

        FDistribution fd = new FDistribution(1, N-2);
        Double fSnedecor = fd.inverseCumulativeProbability(significanceLevel);

        System.out.println("F-value: " + fSnedecor);
        String[] anovaModel = {String.valueOf(MSR), String.valueOf(MSE), String.valueOf(fSnedecor), String.valueOf(f)};
        return anovaModel;
    }


    private Double[] calculateForecastValues(SimpleRegression regression,Double[] xValues){
        Double forecastValues[] = new Double[xValues.length];
        for (int i = 0; i < xValues.length; i++) {
            forecastValues[i] = regression.predict(xValues[i]);
        }

        return forecastValues;
    }

    private Double[] calculateConfidenceInterval(double standardError,SimpleRegression regression, Double value, Double[] y, Double significanceLevel){

        TDistribution tDistribution = new TDistribution(y.length - 2);
        double criticalValue = tDistribution.inverseCumulativeProbability(1 - significanceLevel / 2);
        double marginOfError = criticalValue * standardError;
        double confidenceIntervalMin = value - marginOfError;
        double confidenceIntervalMax = value + marginOfError;

        Double[] interval = {standardError,confidenceIntervalMin, confidenceIntervalMax};

        return interval;
    }

    public String[] calculateHyopthesisTest(double val, double valStdError, double degreesOfFreedom){
        // Calculate t-statistic
        double t = val / valStdError;

        // Calculate p-value
        TDistribution tDist = new TDistribution(degreesOfFreedom);
        double tc = tDist.inverseCumulativeProbability(1 - 0.05/2);


        System.out.println("t-statistic: " + t);
        System.out.println("tc-value: " + tc);
        System.out.println("sig level = 5%");

        // Perform hypothesis test

        String result = "";
        if (t < tc) {
            result = ("Reject the H0");
        } else {
            result = ("Fail to reject the H0");
        }
        String[] hypothesisTest = {String.valueOf(t), String.valueOf(tc), result};
        return hypothesisTest;
    }
*/


    private SimpleLinear createRegressionModel(double[][] parameters, SimpleRegression regression, double significanceLevel){


        Double intercept = regression.getIntercept();
        Double slope = regression.getSlope();
        Double rSquare = regression.getRSquare();



        // This code gets Mean of "x" and "y"
        DescriptiveStatistics xStats = new DescriptiveStatistics();
        for (double x : parameters[0]) {
            xStats.addValue(x);
        }

        DescriptiveStatistics yStats = new DescriptiveStatistics();
        for (double y : parameters[1]) {
            yStats.addValue(y);
        }

        double xAvg = xStats.getMean();
        double yAvg = yStats.getMean();


        // This code gets "Sxx", "Syy" and "Sxy"
        double Sxx = 0;
        for (double x : parameters[0]) {
            Sxx += Math.pow(x-xAvg, 2);
        }

        double Syy =0;
        for (double y : parameters[1]) {
            Syy += Math.pow(y-yAvg, 2);
        }

        double Sxy =0;
        for (int i = 0; i < parameters[0].length; i++) {
            Sxy += (parameters[0][i] - xAvg)*(parameters[1][i] -yAvg);
        }

        double SE = regression.getSumSquaredErrors();
        double SR = regression.getRegressionSumSquares();





        return new SimpleLinear(regression,parameters[1].length,Sxx, Syy,Sxy,SE, SR, xStats.getMean(), yStats.getMean(),rSquare, slope, intercept, parameters, significanceLevel);
    }
}