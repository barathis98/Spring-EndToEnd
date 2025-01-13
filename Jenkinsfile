pipeline{
    agent any

    stages{
        stage("Clone Repo"){
            steps{
                script{
                    checkout scm
                }
            }
        }

        stage("Build maven"){
            steps{
                script{
                    sh "mvn clean package"
                }
            }
        }

        stage("Build docker image"){
            steps{
                     script {
            sh "docker --version"
            sh "docker build -t persist:latest ."
        }
            }
        }
    }

}