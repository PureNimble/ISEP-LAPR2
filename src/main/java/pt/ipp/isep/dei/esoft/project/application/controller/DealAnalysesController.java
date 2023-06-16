package pt.ipp.isep.dei.esoft.project.application.controller;

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

        if (param == -1){ //MultiLinear
            RegressionModel multi = new MultiLinearRegression();
            MultiLinear multiLinear = multi.getRegressionModel(parameterMatrix, significanceLevel);
            return RegressionMapper.toDto(multiLinear.predict(valueToPredict),multiLinear.generateAnalysisReport());

        }
        else if (param != 0){ //Single

            RegressionModel simple = new SimpleLinearRegression();
            SimpleLinear simpleLinear = simple.getRegressionModel(parameterMatrix, significanceLevel);
            return RegressionMapper.toDto(simpleLinear.predict(valueToPredict[0]),simpleLinear.generateAnalysisReport());

        }

        return RegressionMapper.toDto(null,null);
    }
}