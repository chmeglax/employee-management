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
           stage('Unit tests'){
            steps{
                sh 'mvn test'
            }
         }



         stage("Building Docker Image") {
                steps{
                    sh 'docker build -t chmeglax/employee-api:latest .'
                }
        }
        
        stage("Login to DockerHub") {
                steps{

                    sh 'docker login -u chmeglax -p UQfekWkCV6RQu5G'
                }
        }
        stage("Push to DockerHub") {
                steps{
                    sh 'docker push  chmeglax/employee-api:latest'
                }
        }
        stage("Deploy to k8s"){
            steps{
                sshagent(['k8s']) {
                    sh "scp -o StrictHostKeyChecking=no deploymentService.yaml vagrant@10.0.0.10:/home/vagrant"
                    sh "scp -o StrictHostKeyChecking=no deploymentDb.yaml vagrant@10.0.0.10:/home/vagrant"
                    script {
                        try {
                            sh "ssh vagrant@10.0.0.10 kubectl apply -f ./deploymentDb.yaml"
                        }catch(error){
                            sh "ssh vagrant@10.0.0.10 kubectl create -f ./deploymentDb.yaml"
                        }
                        try {
                            sh "ssh vagrant@10.0.0.10 kubectl apply -f ./deploymentService.yaml"
                        }catch(error){
                            sh "ssh vagrant@10.0.0.10 kubectl create -f ./deploymentService.yaml"
                        }
                    }
                }
            }
        }


    }


}
