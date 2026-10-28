pipeline {
    agent any

    environment {
        STUDENT_NAME  = 'Miras Yeren'
        IMAGE_NAME    = 'yeren-miras-student-api'
        IMAGE_TAG     = 'v1.0-yeren-miras-41517'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Student: ${STUDENT_NAME}"
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x gradlew || true'
                sh './gradlew cleen bootJar --no-daemon -x test'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('Verify') {
            steps {
                sh "docker image inspect ${IMAGE_NAME}:${IMAGE_TAG} --format 'Image: {{.RepoTags}} Size: {{.Size}}'"
                echo "Verified image for ${STUDENT_NAME}"
            }
        }
    }

    post {
        success { echo "Pipeline finished: SUCCESS for ${STUDENT_NAME}" }
        failure { echo "Pipeline finished: FAILURE for ${STUDENT_NAME}" }
    }
}
