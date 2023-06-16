package pt.ipp.isep.dei.esoft.project.domain;

public class RegressionMapper {

    public static RegressionDTO toDto(String prediction, String report){
        return new RegressionDTO(prediction, report);
    }
}