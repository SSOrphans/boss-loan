node{
    environment {
        COMMIT_HASH = "${sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()}"
    }
    stage('Checkout'){
        checkout scm
    }
    stage('Build'){
        withMaven{
            sh 'mvn clean package'
        }
    }
    stage('Docker Build') {
                steps {
                    echo 'Deploying....'
                    // sh "aws ecr ........."
                    sh "docker build --tag MicroServiceName:$COMMIT_HASH ."
                    //sh "docker tag MicroServiceName:$COMMIT_HASH $AWS_ID/ECR Repo/MicroServiceName:$COMMIT_HASH"
                    //sh "docker push $AWS_ID/ECR Repo/MicroServiceName:$COMMIT_HASH"
                }
            }
    stage('Deploy'){
        echo 'Deploying...'
    }
}
