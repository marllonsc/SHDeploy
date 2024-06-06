pipeline {
  agent none
  
  environment {
    // Define any necessary environment variables here
    SONARQUBE_SCANNER = 'SonarQube Scanner'
  }

  stages {
    stage("Build & SonarQube Scanner") {
      agent any
      steps {
        // Set up SonarQube scanner environment
        withSonarQubeEnv(SONARQUBE_SCANNER) {
          // Execute Maven build and SonarQube analysis
          sh 'mvn clean package sonar:sonar'
        }
      }
    }
  }

  post {
    always {
      // Post actions like archiving results, sending notifications, etc.
      echo 'Pipeline finished.'
    }
  }
}
