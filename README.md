#Building the Project

1. The project builds with the Maven build tool. Open the “Maven Projects” right-hand tab.
2. Execute the “clean” lifecycle goal to erase the previous build.
3 .Execute the "install" lifecycle goal to rebuild the project.

#Running the Project

An embedded Jetty web server is included in the project, and will run a server on your local machine’s port 9090.

1. To start Jetty, find the jetty plugin under the maven tab described above and execute the “jetty:run” lifecycle goal2.
2. One the application is running, open your web browser and navigate to http://localhost:9090

#If you get an error having to do with connection refused

You will either need to create a dataset folder called 'dayoff' or change the endpoint string in the RestClient class to have the dataset folder you want. 
