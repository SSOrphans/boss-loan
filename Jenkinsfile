node{
    environment {
        SERVICE_NAME = "loanService"
        SONAR_TOKEN = credentials('SONAR_TOKEN')
        COMMIT_HASH = "${sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()}"
    }
    stage('Checkout'){
        checkout scm
    }
    stage('Quality Analysis') {
        withMaven {
            sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login= $SONAR_TOKEN'
        }
    }
    stage('Build') {
        withMaven {
            sh 'mvn clean package'
        }
    }
    stage('Docker Build') {
                steps {
                    echo 'Deploying....'
                    // sh "aws ecr ........."
                    sh "docker build --tag $SERVICE_NAME:$COMMIT_HASH ."
                    //sh "docker tag $SERVICE_NAME:$COMMIT_HASH $AWS_ID/ECR Repo/$SERVICE_NAME:$COMMIT_HASH"
                    //sh "docker push $AWS_ID/ECR Repo/$SERVICE_NAME:$COMMIT_HASH"
                }
            }
    stage('Deploy'){
        echo 'Deploying...'
    }
}