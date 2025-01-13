pipeline{
    agent any
    // tools{
    //     dockerTool "myDocker"
    // }

    stages{
             stage('Initialize'){
        def dockerHome = tool 'myDocker'
        env.PATH = "${dockerHome}/bin:${env.PATH}"
    }
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