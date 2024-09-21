# US 08 - See the list of property announcement requests

# 4. Tests 

# PublishedAnnouncementRequestController

**Test 1:** Get published Announcements 

	@Test
    void getPublishedAnnouncementsTest() {

        Repositories repositories = Repositories.getInstance();

        PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
        publishedAnnouncementRepository.add(publishedAnnouncement);
        AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();
        ComissionRepository comissionRepository = new ComissionRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController(publishedAnnouncementRepository, announcementRequestRepository, comissionRepository, employeeRepository, authenticationRepository);

        List<PublishedAnnouncement> publishedAnnouncements = new ArrayList<>();

        publishedAnnouncements.add(publishedAnnouncement);

        assertEquals(publishedAnnouncements, controller.getPublishedAnnouncements());
    }
	

**Test 2:** Get the Announcement Request from the most Recent-AC1 

	@Test
    void getAnnouncementRequestByMostRecentTest() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
        AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();
        ComissionRepository comissionRepository = new ComissionRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.add(employee);
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Diogo", "emailExample@this.app", "1231dwadwd", AuthenticationController.ROLE_AGENT);
        authenticationRepository.doLogin("emailExample@this.app", "1231dwadwd");

        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController(publishedAnnouncementRepository, announcementRequestRepository, comissionRepository, employeeRepository, authenticationRepository);

        List<AnnouncementRequest> announcementRequests = new ArrayList<>();

        announcementRequests.add(announcementRequest);

        controller.announcementRequestRepository.add(announcementRequest);

        assertEquals(announcementRequests, controller.getAnnouncementRequestByMostRecent());
    }

**Test 3:** Get the comisison list

	 @Test
    void getComissionTest() {
        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController();


        List<Comission> comissions = new ArrayList<>();

        comissions.add(new Comission(50));
        comissions.add(new Comission(20));
        comissions.add(new Comission(10.5));
        comissions.add(new Comission(23.7));

        controller.comissionRepository.add(new Comission(50));
        controller.comissionRepository.add(new Comission(20));
        controller.comissionRepository.add(new Comission(10.5));
        controller.comissionRepository.add(new Comission(23.7));

        assertEquals(comissions, controller.getComission());
    }

**Test 4:** Get the Employee By Email

	  @Test
    void getEmployeeByEmailTest() {

        PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
        AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();
        ComissionRepository comissionRepository = new ComissionRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.add(employee);
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Diogo", "emailExample@this.app", "1231dwadwd", AuthenticationController.ROLE_AGENT);
        authenticationRepository.doLogin("emailExample@this.app", "1231dwadwd");

        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController(publishedAnnouncementRepository, announcementRequestRepository, comissionRepository, employeeRepository, authenticationRepository);


        assertEquals(employee, controller.getEmployeeByEmail());

    }

**Test 5:** Get Announcement Request By Description

	   @Test
    void getAnnouncementRequestByDescriptionTest() {

        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController();
        int index = 0;

        controller.announcementRequestRepository.add(announcementRequest);
        controller.announcementRequestRepository.add(announcementRequest1);
        controller.announcementRequestRepository.add(announcementRequest2);

        assertEquals(announcementRequest, controller.getAnnouncementRequestByDescription(index));

    }

**Test 6:** Get the Comission By Description

	   @Test
    void getComissionByDescriptionTest() {

        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController();

        double comissionValue = 50;
        controller.comissionRepository.add(comission);

        assertEquals(comission, controller.getComissionByDescription(comissionValue));

    }

**Test 7:** Check if the Announcement Request was rejected

	   @Test
    void rejectPublishAnnouncementRequest() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
        publishedAnnouncementRepository.add(publishedAnnouncement1);
        AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();
        announcementRequestRepository.add(announcementRequest1);
        ComissionRepository comissionRepository = new ComissionRepository();
        comissionRepository.add(comission);
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.add(employee);
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Diogo", "emailExample@this.app", "1231dwadwd", AuthenticationController.ROLE_AGENT);
        authenticationRepository.doLogin("emailExample@this.app", "1231dwadwd");


        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController(publishedAnnouncementRepository, announcementRequestRepository, comissionRepository, employeeRepository, authenticationRepository);


        controller.rejectPublishAnnouncementRequest(0);

        assertEquals("false",announcementRequest1.getStatus());



    }

**Test 8:** Check if the accepted Announcement Request was published

	    @Test
    void createPublishAnnouncementRequestTest() {

        PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
        publishedAnnouncementRepository.add(publishedAnnouncement1);
        AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();
        announcementRequestRepository.add(announcementRequest1);
        ComissionRepository comissionRepository = new ComissionRepository();
        comissionRepository.add(comission);
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.add(employee);
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Diogo", "emailExample@this.app", "1231dwadwd", AuthenticationController.ROLE_AGENT);
        authenticationRepository.doLogin("emailExample@this.app", "1231dwadwd");


        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController(publishedAnnouncementRepository, announcementRequestRepository, comissionRepository, employeeRepository, authenticationRepository);

        Optional<PublishedAnnouncement> newPublishedAnnouncement = Optional.of(publishedAnnouncement1);

        List<PublishedAnnouncement> publishedAnnouncements = new ArrayList<>();

        publishedAnnouncements.add(publishedAnnouncement1);


        assertEquals(newPublishedAnnouncement, controller.createPublishAnnouncementRequest(comission.getComission(), 0));
        assertEquals(publishedAnnouncements, controller.getPublishedAnnouncements());
        assertEquals(1, controller.getPublishedAnnouncements().size());

        PublishedAnnouncement publishedAnnouncement = newPublishedAnnouncement.get();

        assertEquals(publishedAnnouncement1.getComission(), publishedAnnouncement.getComission());
        assertEquals(publishedAnnouncement1.getBusiness(), publishedAnnouncement.getBusiness());
        assertEquals(publishedAnnouncement1.getDate(), publishedAnnouncement.getDate());
        assertEquals(publishedAnnouncement1.getProperty(), publishedAnnouncement.getProperty());
        assertEquals(publishedAnnouncement1.getPropertyType(), publishedAnnouncement.getPropertyType());
        assertEquals(publishedAnnouncement1.getTypeOfBusiness(), publishedAnnouncement.getTypeOfBusiness());

    }

**Test 9:** To dto

	    @Test
    void toDto() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
        AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();
        announcementRequestRepository.add(announcementRequest);
        ComissionRepository comissionRepository = new ComissionRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.add(employee);
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Diogo", "emailExample@this.app", "1231dwadwd", AuthenticationController.ROLE_AGENT);
        authenticationRepository.doLogin("emailExample@this.app", "1231dwadwd");

        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController(publishedAnnouncementRepository, announcementRequestRepository, comissionRepository, employeeRepository, authenticationRepository);

        List<AnnouncementRequestDto> announcementRequestDtos = new ArrayList<>();
        announcementRequestDtos.add(announcementRequestDto);

        assertEquals(announcementRequestDtos,controller.toDto());
    }


# AnnouncementRequestDto

**Test 10:** Test to string

	@Test
    void testToStringTest() {


        String expectedOutput = String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Price:%s\n" +
                        "%s",
                date.toString(),typeOfBusiness.toString(),propertyType,business.toString(),house.toString());


        assertEquals(expectedOutput, announcementRequestDto.toString());

    }


**Test 11:** Get Date

	 @Test
    void getDateTest() {

       Date result = announcementRequestDTO1.getDate();
       Date expect = new Date();

       assertEquals(expect,result);

    }

**Test 12:** Get Type Of Business

	 @Test
    void getTypeOfBusinessTest() {

        TypeOfBusiness result = announcementRequestDto.getTypeOfBusiness();
        TypeOfBusiness expected = new TypeOfBusiness("Sale");

        assertEquals(expected,result);

    }

**Test 13:** Get Property

	   @Test
    void getPropertyTest() {

        Property result = announcementRequestDto.getProperty();
        Property expected = house;

        assertEquals(expected,result);
    }

**Test 14:** Get Property Type

	 @Test
    void getPropertyTypeTest() {

        PropertyType result = announcementRequestDto2.getPropertyType();
        PropertyType expected = new PropertyType("Land");

        assertEquals(expected,result);
    }

**Test 15:** Get BusinessTest

	 @Test
    void getBusinessTest() {

        Business  result = announcementRequestDto2.getBusiness();
        Business expected = new Business(102.213);

        assertEquals(expected,result);
    }

**Test 16:** Get Duration of Contract

	  @Test
    void getDurationOfContractTest() {

        int  result = announcementRequestDto2.getDurationOfContract();
        int expected = 0;

        assertEquals(expected,result);

    }

**Test 17:** Get Agent

	  @Test
    void getAgentTest() {

        Employee result = announcementRequestDto.getAgent();
        Employee expected = employee;

        assertEquals(expected,result);


    }

**Test 18:** Get Status

	    @Test
    void getStatusTest() {
        String  result = announcementRequestDto2.getStatus();
        String expected = "";

        assertEquals(expected,result);
    }

# AnnouncementRequestRepository

**Test 19:** Get Announcement Requests By Most Recent

        @Test
        void getAnnouncementRequestsByMostRecentTest() {

        AnnouncementRequestRepository repository = new AnnouncementRequestRepository();

        List<AnnouncementRequest> expected = new ArrayList<>();
        expected.add(announcementRequest);
        expected.add(announcementRequest1);
        expected.add(announcementRequest2);

        repository.add(announcementRequest);
        repository.add(announcementRequest1);
        repository.add(announcementRequest2);

        Collections.reverse(expected);

        assertEquals(expected,repository.getAnnouncementRequestsByMostRecent(employee));

    }

**Test 20:** Get Announcement Request By Description

        @Test
         void getAnnouncementRequestByDescriptionTest() {

        AnnouncementRequestRepository repository = new AnnouncementRequestRepository();

        repository.add(announcementRequest);
        repository.add(announcementRequest1);
        repository.add(announcementRequest2);


        assertEquals(announcementRequest1,repository.getAnnouncementRequestByDescription(1));

    }

**Test 21:** Reject Announcement Request

    @Test
    void rejectAnnouncementRequestTest() {
        AnnouncementRequestRepository repository = new AnnouncementRequestRepository();

        repository.add(announcementRequest);
        repository.add(announcementRequest1);
        repository.add(announcementRequest2);


        repository.rejectAnnouncementRequest(announcementRequestDto);

        assertEquals("false",announcementRequest.getStatus());


    }

# PublihedAnnouncementRepository

**Test 19:** Published Announcement Request

       @Test
        void publishedAnnouncementRequestTest() {

        PublishedAnnouncementRepository repository = new PublishedAnnouncementRepository();



        List<AnnouncementRequest> announcementRequests = new ArrayList<>();
        announcementRequests.add(announcementRequest);
        announcementRequests.add(announcementRequest1);

        Optional<PublishedAnnouncement> publishedAnnouncementExpected = Optional.of(publishedAnnouncement);

        Optional<PublishedAnnouncement> publishedAnnouncementResult = repository.publishedAnnouncementRequest(announcementRequests,announcementRequestDto,comission);

        assertEquals("true",announcementRequest.getStatus());
        assertEquals(publishedAnnouncementExpected,publishedAnnouncementResult);

    }

# AnnouncementRequestMapper

**Test 20:** to Dto

        @Test
        void toDto() {

        AnnouncementRequestMapper announcementRequestMapper = new AnnouncementRequestMapper();

        List<AnnouncementRequest> announcementRequests = new ArrayList<>();
        announcementRequests.add(announcementRequest);
        announcementRequests.add(announcementRequest1);
        announcementRequests.add(announcementRequest2);


        List<AnnouncementRequestDto> announcementRequestDtos = new ArrayList<>();
        announcementRequestDtos.add(announcementRequestDto);
        announcementRequestDtos.add(announcementRequestDTO1);
        announcementRequestDtos.add(announcementRequestDto2);

        assertEquals(announcementRequestDtos,announcementRequestMapper.toDto(announcementRequests));
    }

**Test 21:** to Dto Object

    @Test
    void toDtoObject() {

        AnnouncementRequestMapper announcementRequestMapper = new AnnouncementRequestMapper();

        assertEquals(announcementRequestDto,announcementRequestMapper.toDtoObject("",employee,house,typeOfBusiness,propertyType,business,date,0));

    }

**Test 22:** Get Announcement Request DTO By Description

    @Test
    void getAnnouncementRequestDtoByDescription() {

        AnnouncementRequestMapper announcementRequestMapper = new AnnouncementRequestMapper();

        List<AnnouncementRequestDto> announcementRequestDtos = new ArrayList<>();
        announcementRequestDtos.add(announcementRequestDto);
        announcementRequestDtos.add(announcementRequestDTO1);
        announcementRequestDtos.add(announcementRequestDto2);

        assertEquals(announcementRequestDto,announcementRequestMapper.getAnnouncementRequestDtoByDescription(announcementRequestDtos,0));


    }


