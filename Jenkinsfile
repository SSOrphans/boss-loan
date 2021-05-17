node{
    stage('Checkout'){
        checkout scm
    }
    stage('Build'){
        withMaven{
            ssh 'mvn clean package'
        }
    }
    stage('Docker Build') {
                steps {
                    echo 'Deploying....'
                    // sh "aws ecr ........."
                    sh "docker build --tag MicroServiceName:$COMMIT_HASH ."
                    sh "docker tag MicroServiceName:$COMMIT_HASH $AWS_ID/ECR Repo/MicroServiceName:$COMMIT_HASH"
                    sh "docker push $AWS_ID/ECR Repo/MicroServiceName:$COMMIT_HASH"
                }
            }
    stage('Deploy'){
        echo 'Deploying...'
    }
}