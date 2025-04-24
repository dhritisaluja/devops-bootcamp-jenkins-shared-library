#!/usr/bin/env groovy
def call(String imageName) {
    echo 'building the docker image...'
    withCredentials([
            usernamePassword(credentials: '<credentials-id>', usernameVariable: 'USERNAME', passwordVariable: 'PWD')
    ]) {
        sh "docker build -t $imageName ."
        sh "echo $PWD | docker login -u $USERNAME --password-stdin"
        sh "docker push $imageName"
    }
}