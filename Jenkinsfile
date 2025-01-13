pipeline{
    agent any
    // tools{
    //     dockerTool "myDocker"
    // }

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
            sh "/opt/homebrew/bin/docker --version"
            sh "docker build -t persist:latest ."
        }
            }
        }
    }

}