node {
    withEnv(['serviceName=boss-loan', 'commitHash=${sh(script: \'git rev-parse --short HEAD\', returnStdout: true).trim()}', 'test=asdasd']) {
        stage('Checkout') {
            checkout([$class: 'GitSCM', branches: [[name: 'feature/SSOR-208-JenkinsPipelines']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/SSOrphans/boss-loan-service.git']]])
            sh 'git submodule update --init'

        }
        stage('Build') {
            withMaven(jdk: 'openjdk-11') {
                sh 'mvn clean package'
            }
        }
        stage('Quality Analysis') {
            withCredentials([string(credentialsId: 'sonar-token', variable: 'sonartoken')]) {
                withMaven(jdk: 'openjdk-11') {
                    sh 'mvn clean install'
                    sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=$sonartoken'
                }
            }
        }
        stage('Docker Build') {
            withCredentials([string(credentialsId: 'aws-repo', variable: 'awsRepo')]) {
                echo "Building and deploying $serviceName"

                docker.build('$serviceName')
                docker.withRegistry("$awsRepo", 'ecr:us-east-2:aws-credentials') {
                    docker.image('$serviceName').push('$commitHash')
                }
            }
        }
    }
}