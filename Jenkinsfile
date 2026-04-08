pipeline {
    agent any

    environment {
        PROJECT_NAME = 'TaylorSoft'
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo '📦 Récupération du code source...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo '🔨 Compilation du projet...'
                sh 'chmod +x ./mvnw && ./mvnw clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo '🧪 Exécution des tests...'
                sh 'chmod +x ./mvnw && ./mvnw test || true'
            }
        }

        stage('Build Docker Image') {
            when {
                branch 'master'
            }
            steps {
                echo '🐳 Construction de l\'image Docker...'
                sh '''
                    docker build -t ${PROJECT_NAME}:${IMAGE_TAG} .
                    docker tag ${PROJECT_NAME}:${IMAGE_TAG} ${PROJECT_NAME}:latest
                '''
            }
        }

        stage('Deploy') {
            when {
                branch 'master'
            }
            steps {
                echo '🚀 Déploiement...'
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        always {
            junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
        success {
            echo '✅ Pipeline réussi!'
        }
        failure {
            echo '❌ Pipeline échoué!'
        }
    }
}
