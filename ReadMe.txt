Data generation:

	There are two main parts: Metadata and Images

	Metadata comes from ISD (isd.dmlab.cs.gsu.edu)
	Image data comes from DMLAB API (http://dmlab.cs.gsu.edu/dmlabapi/)

	Since the dmlab have access to backing DB of both projects, those connections are used directly. 

	To run the code in your local or on the DMLAB(@GSU) server, you need to configure CONNECTION_TYPE inside DBPrefs.java. 

	Set it to the following: 			
			CONNECTION_TYPE = CONNECTION_LOCAL; (To run locally)
			CONNECTION_TYPE = CONNECTION_DMLAB_SERVER; (To run on the dmlab server)


	These statements are already present, please use the appropriate initialization and comment the other one. 	


	There are two parameters for running the code, output directory and year. Year is passed because data for different years can be generated by running different process. It is faster in that way.


Dependencies: 
	Needs maven, dmlablib jar. 

Commands:

Installing dmlablib jar: 
   Option1: 
          mvn install:install-file -Dfile=<path-to-jar> -DgroupId=edu.gsu.dmlab -DartifactId=lib -Dversion=0.0.1 
	OR
         mvn install:install-file -Dfile=<path-to-jar> -DpomFile=<path-to-pomfile>

   Option2: Install dmlablib repo and build it using ‘mvn install’. It generates libs which can be by default imported if they are in the same workspace.

Build and Run:
	mvn clean compile assembly:single

  After that should be able to run compiled jar under "/target". Copy jar and run jar using the following command:

	java -jar <path_to_jar_file>  <output_dir_path> <year>




