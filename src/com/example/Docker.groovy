#!/usr/bin/env groovy
package com.example

class Docker implements Serializable{

    // Property to hold the Jenkins pipeline script context
    def script    // 'def' means the type is dynamic, common in Groovy

    // Constructor: This method is called automatically when you create a new Docker object.
    // It takes one argument (the pipeline script context) and stores it.
    Docker(script){
        this.script=script
    }

    def buildImage(String imageName){
        // Now, use 'script.' to access Jenkins pipeline steps:
        script.echo 'Building the docker image...'
        script.sh "docker build -t $imageName ."
    }

    def login(){
        script.echo 'Login to DockerHub...'
        // Accessing Jenkins credentials
        script.withCredentials([
                script.usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable:'DOCKER_USER', passwordVariable: 'DOCKER_PASS')
        ]){
            // DOCKER_USER and DOCKER_PASS are now available as environment variables
            // within this 'withCredentials' block.
            script.sh "echo '${script.DOCKER_PASS}' | docker login -u '${script.DOCKER_USER}' --password-stdin"
        }
    }

    def publishImage(String imageName){
        script.echo 'Publishing the Docker image...'
        script.sh "docker push $imageName"
    }

}