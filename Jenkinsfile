pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                withMaven (
                    maven: 'Maven 3.9.9',
                    jdk: 'OpenJDK21'
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
            post {
                always {
                    allure includeProperties:
                        false,
                        jdk: '',
                        results: [[path: 'allure-results']]
                }
            }
        }
        stage('Package') {
            steps {
                withMaven (
                    maven: 'Maven 3.9.9',
                    jdk: 'OpenJDK21'
                ){
                    sh "mvn verify -DskipTests"
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