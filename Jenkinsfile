pipeline {
     agent any
     environment {
        PATH = "$PATH:/usr/share/maven"
         registry='chmeglax/employee-api'
             registryCredential = 'dockerhub'
        dockerImage=''
    }
     stages {
        stage("fetch code"){
             steps{
               git branch: 'main', credentialsId: '2d98216b-4f2b-4934-b40f-9efa97f83a9a', url: 'https://github.com/chmeglax/employee-management.git'
             }
         }

       stage('Build'){
            steps{
                sh 'mvn clean install'
            }
         }
            stage('test junit'){
            steps{
                sh 'mvn test'
            }
         }



         stage("Building Docker Image") {
                steps{
                    script {
                        docker.build registry + ":$BUILD_NUMBER"
                    }
                }
        }
        
        stage("Login to DockerHub") {
                steps{

                    sh 'docker login -u chmeglax -p UQfekWkCV6RQu5G'
                }
        }
        stage("Push to DockerHub") {
                steps{
                    script {
                        docker.push registry + ":$BUILD_NUMBER"
                    }
                }
        }


    }
       /*   post {
        success {
            emailext  body: 'Le pipeline que vous venez de lancer est achevé avec succé', recipientProviders: [buildUser()], subject: 'Build réussi', to: 'elyes.benothman@esprit.tn'
        }
        failure{
            emailext attachLog: true, body: 'Le pipeline que vous vener de lancez est achevé avec failure', recipientProviders: [buildUser()], subject: 'Build failed', to: 'elyes.benothman@esprit.tn'
        }
    }*/

}
