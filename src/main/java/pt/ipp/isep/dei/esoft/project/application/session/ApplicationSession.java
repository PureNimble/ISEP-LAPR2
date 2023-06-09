package pt.ipp.isep.dei.esoft.project.application.session;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationSession {
    private AuthenticationRepository authenticationRepository=null;
    private static final String CONFIGURATION_FILENAME = "src/main/resources/config.properties";
    private static final String COMPANY_DESIGNATION = "Company.Designation";

    public ApplicationSession() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        Properties props = getProperties();
    }

    public UserSession getCurrentSession(){
        pt.isep.lei.esoft.auth.UserSession userSession = this.authenticationRepository.getCurrentUserSession();
        return new UserSession(userSession);
    }

    private Properties getProperties()
    {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(COMPANY_DESIGNATION, "Real Estate USA");


        // Read configured values
        try
        {
            InputStream in = new FileInputStream(CONFIGURATION_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        return props;
    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static ApplicationSession singleton = null;
    public static ApplicationSession getInstance()
    {
        if(singleton == null)
        {
            synchronized(ApplicationSession.class)
            {
                singleton = new ApplicationSession();
            }
        }
        return singleton;
    }


}