package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

import java.text.DecimalFormat;


public class MultiLinear {

    private final DecimalFormat df = new DecimalFormat("0.0000");
    private OLSMultipleLinearRegression regression;
    private double[][] parameters;
    private int n;
    private double[] B;
    private int k;
    private double SE;
    private double ST;
    private double SR;
    private double squareR;
    private double squareRAdjusted;
    private double[] parametersStdErr;

    private double significanceLevel;



    public MultiLinear(OLSMultipleLinearRegression regression, double[][] parameters, double significanceLevel){

        this.regression = regression;
        this.n = parameters[0].length;
        this.parameters = parameters;
        this.B = regression.estimateRegressionParameters();
        this.k = B.length;
        this.SE = regression.calculateResidualSumOfSquares();
        this.ST = regression.calculateTotalSumOfSquares();
        this.SR = this.ST - this.SE;
        this.squareR = regression.calculateRSquared();
        this.squareRAdjusted = regression.calculateAdjustedRSquared();
        this.parametersStdErr = regression.estimateRegressionParametersStandardErrors();
        this.significanceLevel = significanceLevel;
    }

/*
    private double[][] calculateC() {
        double[][] C = new double[6][6];
        for (int i = 0; i < parameters[0].length; i++) {
            for (int j = 0; j < parameters.length; j++) {

            }
        }
    }*/


    /**
     * This method gets the regression coefficients
     *
     * @return - double[]
     */

    public double[] getB() {
        return B;
    }

    public MultiLinear() {

    }


    /**
     * This method creates the list of forecast prices
     * @return - String
     */
    public String getForecastList(){
        String message ="\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\nForecast | Sale";

        for (int i = 0; i < parameters[0].length; i++) {
            String forecast = String.valueOf(df.format(this.B[0] + parameters[0][i]*this.B[1] + parameters[1][i]*this.B[2] + parameters[2][i]*this.B[3] + parameters[3][i]*this.B[4] + parameters[4][i]*this.B[5]));
            message = message + "\n" + forecast + " | " + parameters[5][i];
        }
        return message;
    }

    @Override
    public String toString() {
        return "\nRegression Model :" +
                "\nSIGNIFICANCE_LEVEL=" + this.significanceLevel +
                "\nn=" + df.format(this.n) +
                "\nSQT=" + df.format(ST) +
                "\nSQR=" + df.format(SR) +
                "\nSQE=" + df.format(SE) +
                "\nR Squared =" + df.format(squareR) +
                "\nR Squared Adjusted =" + df.format(squareRAdjusted) +
                //"\nparametersStdErr=" + Arrays.toString(parametersStdErr) +
                "\n\n\n Equation : Y =  " + "(" + df.format(this.B[0]) + ") +" + "(" + df.format(this.B[1]) + ")X\u2081 +(" + df.format(this.B[2]) + ")X\u2082 +(" + df.format(this.B[3]) + ")X\u2083 +(" + df.format(this.B[4]) + ")X\u2084" +"\n\n";

    }

    /**
     * This method creates the anova significance model
     *
     * @return - String
     */
    public String anovaSingificanceModel(){


        double MQR = this.SR / (this.k-1);
        double MQE = this.SE/(this.n-(this.k));
        double f = MQR/MQE;

        FDistribution fd = new FDistribution(this.k, this.n-(this.k));
        Double fSnedecor = fd.inverseCumulativeProbability(1-this.significanceLevel);



        String message = ("\n\n||-=-=-=- Significance Model With ANOVA -=-=-=-||"
                + "\n MQR:" + df.format(MQR)
                + "\n MQE:" + df.format(MQE)
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
        message = message + "\n -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n";
        //System.out.println(message);
        return message;
    }


    /**
     * This method calculates the confidence intervals for the coefficients
     *
     * @return - String
     */

    public String calculateCoefficientCondifenceIntervals(){

        TDistribution tDistribution = new TDistribution(this.n - 2);
        double criticalValue = tDistribution.inverseCumulativeProbability(1 - this.significanceLevel / 2);

        double[] lowerBounds = new double[this.B.length];
        double[] upperBounds = new double[this.B.length];
        for (int i = 0; i < this.B.length; i++) {
            lowerBounds[i] = this.B[i] - criticalValue * this.parametersStdErr[i];
            upperBounds[i] = this.B[i] + criticalValue * this.parametersStdErr[i];
        }
        String message = "\n[-=-=-=-=-=-= Confidence Intervals (" + (1-this.significanceLevel)*100 +  "%) -=-=-=-=-=-=]\n\n";
        // Add confidence intervals to message
        message = message + "\n" ;
        for (int i = 0; i < this.B.length; i++) {
            message = message + "\n\nParameter " + (i) + " Confidence Interval (" + (1-this.significanceLevel)*100 + "%): ]" + df.format(lowerBounds[i]) + ", " + df.format(upperBounds[i]) + "["
                    + "\nParameter " + i + " =" + df.format(this.B[i]) + "\nStandard Error: " + df.format(this.parametersStdErr[i]) + "\n -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
        }
        //System.out.println(message);

        return message;
    }

    /**
     * This method does the hypothesis tests for the coefficients
     *
     * @return - String
     */
    public String calculateHypothesisTests(){
        TDistribution tDistribution = new TDistribution(this.n - 2);
        double criticalValue = tDistribution.inverseCumulativeProbability(1 - this.significanceLevel / 2);

        String message = "\n\n -=-=-=-=-=- Hypothesis Tests ("+(1- this.significanceLevel)*100+ "%) -=-=-=-=-=- \n Test : H0 : B = 0 \n         H1 : B =|= 0";



        double[] tVals= new double[this.B.length];
        for (int i = 0; i < this.B.length; i++) {
            tVals[i] = this.B[i] / this.parametersStdErr[i];
            message = message + "\n -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n Parameter " + i + ": "+ df.format(this.B[i]) + "\nobserved t -> " + df.format(tVals[i]) + "\n tc ->" + df.format(criticalValue);

            if(Math.abs(tVals[i]) <= criticalValue){
                message = message + "\n|observed t| <= tc, Accepts H0";
            }else {
                message = message + "\n|observed t| > tc, Rejects H0";
            }
        }
        return message;
    }

    /**
     * This method predicts a custom value
     *
     * @param parameters
     * @return - String
     */

    public String predictMulti(double[] parameters){
        RealMatrix xMatrix = MatrixUtils.createRealMatrix(matrixX());

        RealMatrix xMatrixT = MatrixUtils.createRealMatrix(transpose(matrixX()));

        RealMatrix multiplicationXxT = (xMatrixT.multiply(xMatrix));

        RealMatrix inverse = new LUDecomposition(multiplicationXxT).getSolver().getInverse();
        double[][] pams = {{1,parameters[0],parameters[1],parameters[2],parameters[3],parameters[4]}};

        RealMatrix xO = MatrixUtils.createRealMatrix(pams);

        RealMatrix xOT = MatrixUtils.createRealMatrix(transpose(xO.getData()));
        //System.out.println(xOT.getColumnDimension());
        //System.out.println(xOT.getRowDimension());

        RealMatrix xTXX = xO.multiply(inverse).multiply(xOT);

        double MQE = this.SE/(this.n-(this.k));


        double predictedY = (this.B[0] + parameters[0]*this.B[1] + parameters[1]*this.B[2] + parameters[2]*this.B[3] + parameters[3]*this.B[4] + parameters[4]*this.B[5]);


        TDistribution tDistribution = new TDistribution(this.n - (this.k-1));
        double criticalValue = tDistribution.inverseCumulativeProbability(1 - this.significanceLevel / 2);


        Double lower = predictedY - (Math.sqrt(MQE *((1+ xTXX.getEntry(0,0))) * criticalValue));
        Double upper = predictedY + (Math.sqrt(MQE *((1+ xTXX.getEntry(0,0))) * criticalValue));


        if(upper<lower){
            return("\nPredicted value: " + df.format(predictedY) + "\nConfidence interval("+(1- significanceLevel)*100 + "%): ]" + df.format(upper) + ", " + df.format(lower) + "[");

        }else{
            return("\nPredicted value: " + df.format(predictedY) + "\nConfidence interval("+(1- significanceLevel)*100 + "%): [" + df.format(lower) + ", " + df.format(upper) + "]");

        }
    }


    /**
     *
     * This method does manual matrix multiplication
     * @param m1
     * @param m2
     * @return - double[][]
     */
    private double[][] manualMultiplication(double[][] m1, double[][] m2){
        double[][] result = new double[m1[0].length][m2.length];
        for (int i = 0; i < m1[0].length; i++) {
            for (int j = 0; j < m2.length; j++) {
                int sum = 0;
                for (int k = 0; k < m2.length; k++) {
                    sum += m1[i][k] * m2[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    /**
     *
     * This method adjuts the matrix of X for calculation
     * @return - double[][]
     */
    private double[][] matrixX() {
        if (this.parameters[0].length != this.parameters[5].length) throw new IllegalArgumentException("The arrays sizes should be the same");
        int length = parameters.length;

        double[][] matrixAux = new double[parameters[0].length][length];
        // X matrix
        for (int i = 0; i < this.parameters[0].length; i++) {
            matrixAux[i][0] = 1;
            matrixAux[i][1] = this.parameters[0][i];
            matrixAux[i][2] = this.parameters[1][i];;
            matrixAux[i][3] = this.parameters[2][i];;
            matrixAux[i][4] = this.parameters[3][i];;
            matrixAux[i][5] = this.parameters[4][i];;
        }
        return matrixAux;
    }


    /**
     * This method transposes the matrix
     *
     * @param matrix
     * @return - double[][]
     */
    private double[][] transpose(double[][] matrix) {
        double[][] matrixTransposed = new double[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrixTransposed[i][j] = matrix[j][i];
            }
        }

        return matrixTransposed;

    }

    /**
     * This method generates the analysis report
     *
     * @return - String
     */

    public String generateAnalysisReport(){
        return this.toString() +this.anovaSingificanceModel() + this.calculateCoefficientCondifenceIntervals()  + this.calculateHypothesisTests() +  this.getForecastList();
    }

}
