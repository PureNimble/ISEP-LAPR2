package pt.ipp.isep.dei.esoft.project.domain;

public class RegressionDTO {

    private String prediction;

    private String report;

    public RegressionDTO()
    {

    }
    public RegressionDTO(String prediction, String report){
        this.prediction = prediction;
        this.report = report;
    }



    public String getReport() {
        return report;
    }

    public String getPrediction() {return prediction;}
}