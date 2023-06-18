package pt.ipp.isep.dei.esoft.project.application.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import pt.ipp.isep.dei.esoft.project.domain.MultiLinear;
import pt.ipp.isep.dei.esoft.project.domain.MultiLinearRegression;
import pt.ipp.isep.dei.esoft.project.domain.RegressionDTO;
import pt.ipp.isep.dei.esoft.project.domain.RegressionMapper;
import pt.ipp.isep.dei.esoft.project.domain.RegressionModel;
import pt.ipp.isep.dei.esoft.project.domain.SimpleLinear;
import pt.ipp.isep.dei.esoft.project.domain.SimpleLinearRegression;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class DealAnalysesController {

    /**
     * Retrieves the PublishedAnnouncementRepository instance.
     *
     * @return The PublishedAnnouncementRepository instance.
     */
    private PublishedAnnouncementRepository publishedAnnouncementRepository = null;
    
    /**
     * Constructs a new instance of DealAnalysesController and initializes the repositories.
     */
    public DealAnalysesController() {
        getPublishedAnnouncementRepository();
    }

    /**
     * Retrieves the PublishedAnnouncementRepository instance.
     *
     * @return The PublishedAnnouncementRepository instance.
     */
    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
    }

    /**
     * @param param
     * @return The regressionModel results.
     */
    public RegressionDTO regressionModel(int param, double significanceLevel, double[] valueToPredict) {

        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        double[][] parameterMatrix = publishedAnnouncementRepository.getParameterMatrix(param);
        if (parameterMatrix.length == 0 || parameterMatrix[0].length == 0){
            RegressionDTO regressionDTO = RegressionMapper.toDto(null, "No data to analyse");
            writeRegressionDTOToFile(regressionDTO);
            return regressionDTO;
        }

        if (param == -1){ //MultiLinear
            RegressionModel multi = new MultiLinearRegression();
            MultiLinear multiLinear = multi.getRegressionModel(parameterMatrix, significanceLevel);
            RegressionDTO regressionDTO = RegressionMapper.toDto(multiLinear.predictMulti(valueToPredict),multiLinear.generateAnalysisReport());
            writeRegressionDTOToFile(regressionDTO);
            return regressionDTO;

        }
        else if (param != 0){ //Single

            RegressionModel simple = new SimpleLinearRegression();
            SimpleLinear simpleLinear = simple.getRegressionModel(parameterMatrix, significanceLevel);
            RegressionDTO regressionDTO = RegressionMapper.toDto(simpleLinear.predictSimple(valueToPredict[0]),simpleLinear.generateAnalysisReport());;
            writeRegressionDTOToFile(regressionDTO);            
            return regressionDTO;

        }
        RegressionDTO regressionDTO = RegressionMapper.toDto(null,null);
        writeRegressionDTOToFile(regressionDTO);
        return regressionDTO;
    }

    public void writeRegressionDTOToFile(RegressionDTO regressionDTO) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ReportAndPrediction.txt", false))) {
            writer.write("Report: " + regressionDTO.getReport());
            writer.newLine();
            writer.write("\nPrediction: " + regressionDTO.getPrediction());

        } catch (IOException e) {
            e.printStackTrace();
            // Handle any exceptions that occur during file writing
        }
    }

}