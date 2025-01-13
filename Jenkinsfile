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
                script{
                    app = docker.build("Persist:latest")
                }
            }
        }
    }

}