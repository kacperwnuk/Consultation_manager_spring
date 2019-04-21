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
            post {
                always {
                    cobertura coberturaReportFile: '**/coverage.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }
        stage('Deploy') {
            when {
                branch 'development'
            }
            steps {
                sh "cp /home/mzyzynsk/tomcat-conf/server.xml target/server.xml"
                sh "cp /home/mzyzynsk/tomcat-conf/web.xml target/web.xml"
                sh "cp /home/mzyzynsk/tomcat-conf/keystore.p12 target/keystore.p12"
                sh "/home/mzyzynsk/jenkins/scripts/deploy_master.sh"
            }
        }
    }
};
