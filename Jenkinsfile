pipeline {
    agent any

    parameters {
        string(name: 'TAGS', defaultValue: '@ui or @api', description: 'Cucumber tags to run')
        string(name: 'ENV', defaultValue: 'qa', description: 'Environment to run tests against')
    }

    environment {
        MAVEN_HOME = tool 'maven-3.9.5'
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Execute Tests') {
            steps {
                sh "mvn test -Dcucumber.filter.tags=\"${params.TAGS}\" -Denv=${params.ENV}"
            }
        }
    }

    post {
        always {
            publishHTML(target: [
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target',
                reportFiles: 'cucumber-reports.html',
                reportName: 'Cucumber HTML Report'
            ])
            
            // Archive Extent Reports
            archiveArtifacts artifacts: 'target/spark/**', allowEmptyArchive: true
        }
    }
}
