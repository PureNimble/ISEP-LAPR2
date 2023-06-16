package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class MultiLinearRegression implements RegressionModel{


    @Override
    public MultiLinear getRegressionModel(double[][] parameters, double significanceLevel) {

/*
        regression.newSampleData(pricesList, predictors);

        double[] beta = regression.estimateRegressionParameters();

        double[] residuals = regression.estimateResiduals();

        double[][] parametersVariance = regression.estimateRegressionParametersVariance();

        double regressandVariance = regression.estimateRegressandVariance();

        double rSquared = regression.calculateRSquared();

        double sigma = regression.estimateRegressionStandardError();

        double[] newX = {2000, 31, 2,3,2};*/
        MultiLinear regression = createRegressionModel(parameters, significanceLevel);


        return regression;
    }

    private MultiLinear createRegressionModel(double parameters[][], double significanceLevel){
        /*double[] propertyAreaList = parameters[0];
        double[] distanceFromCentreList = parameters[1];
        double[] bedroomsList = parameters[2];
        double[] bathroomsList = parameters[3];
        double[] parkingSpacesList = parameters[4];
        double[] pricesList = parameters[5];
        regression.newSampleData(parameters[5], predictors);*/


        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();



        double[][] predictors = new double[parameters[0].length][];
        for (int i = 0; i < parameters[0].length; i++) {
            predictors[i] = new double[]{parameters[0][i], parameters[1][i], parameters[2][i],parameters[3][i],parameters[4][i]};
        }

        regression.newSampleData(parameters[5], predictors);

        /////////////////// Test values

/*
        double[][] data = {{65,71,69,75,78,66,23,89,23,63},{63,69,64,76,75,65,64,76,75,65},{2,2,2,12,5,3,2,12,5,3},{8,6,3,2,5,2,3,2,5,2},{9,1,1,3,5,8,1,3,5,8},{2,4,6,6,7,9,6,6,7,9}};

        double[][] predictors = new double[10][];
        double[] yvals= new double[10];
        for (int i = 0; i < data[0].length; i++) {
            predictors[i] = new double[]{data[1][i],data[2][i],data[3][i],data[4][i],data[5][i]};
            yvals[i] = data[0][i];
        }

        regression.newSampleData(yvals, predictors);*/


        MultiLinear multi = new MultiLinear(regression, parameters, significanceLevel);
        //multi.anovaSingificanceModel();
        //multi.calculateCoefficientCondifenceIntervals();
        //multi.calculateHypothesisTests();
        return multi;
    }

}