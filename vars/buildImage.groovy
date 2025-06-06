#!/usr/bin/env groovy
 import com.example.Docker

def call(String imageName){
    docker = new Docker(this)
    docker.buildImage(imageName)
    docker.login()
    docker.publishImage(imageName)
}


