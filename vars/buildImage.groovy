#!/usr/bin/env groovy
def call() {
    echo 'building the docker image...'
    withCredentials([
            usernamePassword(credentials: '<credentials-id>', usernameVariable: 'USERNAME', passwordVariable: 'PWD')
    ]) {
        sh 'docker build -t <your/private-repo-name:version> .'
        sh "echo $PWD | docker login -u $USERNAME --password-stdin"
        sh 'docker push <your/private-repo-name:version>'
    }
}