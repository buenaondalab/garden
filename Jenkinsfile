pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                withMaven {
                    maven: 'Maven 3.9.9'
                    jdk: 'OpenJDK21'
                    sh "./mvnw clean verify -Dspring-boot.run.profiles=prod"
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}