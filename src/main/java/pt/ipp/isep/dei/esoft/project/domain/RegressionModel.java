package pt.ipp.isep.dei.esoft.project.domain;

public interface RegressionModel {
    <T> T getRegressionModel(double[][] parameters, double significanceLevel);
}