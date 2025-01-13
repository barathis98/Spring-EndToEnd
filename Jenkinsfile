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
               sh "docker --version"
            }
        }
    }

}