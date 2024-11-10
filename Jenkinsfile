pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                withMaven (
                    maven: 'Maven 3.9.9',
                    jdk: 'OpenJDK21',
                    publisherStrategy: 'EXPLICIT'
                ){
                    sh "mvn clean compile"
                }
            }
        }
        stage('Unit Tests') {
            steps {
                echo 'Testing..'
                withMaven (
                    maven: 'Maven 3.9.9',
                    jdk: 'OpenJDK21'
                ){
                    sh "mvn test"
                }
            }
        }
        stage('Package') {
            steps {
                withMaven (
                    maven: 'Maven 3.9.9',
                    jdk: 'OpenJDK21',
                    publisherStrategy: 'EXPLICIT'
                ){
                    sh "mvn verify -DskipTests"
                }
                post {
                    always {
                        allure includeProperties:
                            false,
                            jdk: '',
                            results: [[path: 'target/allure-results']]
                    }
                }
            }

        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}