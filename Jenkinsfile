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

        stage("Build"){
            steps{
                     script {
            sh "docker --version"
            sh "docker build -t persist:latest ."
        }
            }
        }
    }

}