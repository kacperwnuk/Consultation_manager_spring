pipeline {
    agent any    
    tools {
    maven 'M3'
    }
    environment {
        CI = 'true'
    }
    stages {
        stage('Build') {
	    steps {
                sh "mvn -DskipTests clean install"
            }
        }
	stage('Test') {
	    steps {
	        sh "mvn test"
	    }
	}
        stage('Deliver') {
	    steps {
		archiveArtifacts artifacts: 'target/*.war', fingerprint: true
	    }
	}
	stage('Deploy') {
	    when {
		branch 'master'
	    }
	    steps {
		sh "/home/mzyzynsk/jenkins/scripts/deploy_master.sh"
	    }
	}

    }
};
