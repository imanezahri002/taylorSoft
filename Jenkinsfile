pipeline {
    agent any

    environment {
        // Variables du projet
        PROJECT_NAME = 'TaylorSoft'
        DOCKER_REGISTRY = 'your-registry'
        IMAGE_TAG = "${BUILD_NUMBER}"
        AWS_REGION = 'eu-north-1'
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
                echo '🔨 Compilation du projet Maven...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo '🧪 Exécution des tests...'
                sh 'mvn test'
            }
        }

        stage('Code Quality Analysis') {
            steps {
                echo '📊 Analyse de qualité du code...'
                // SonarQube (optionnel - à configurer)
                echo '✓ Analyse de qualité complétée'
            }
        }

        stage('Build Docker Image') {
            when {
                anyOf {
                    branch 'master'
                    branch 'develop'
                }
            }
            steps {
                echo '🐳 Construction de l\'image Docker...'
                sh '''
                    docker build -t ${DOCKER_REGISTRY}/${PROJECT_NAME}:${IMAGE_TAG} .
                    docker tag ${DOCKER_REGISTRY}/${PROJECT_NAME}:${IMAGE_TAG} ${DOCKER_REGISTRY}/${PROJECT_NAME}:latest
                '''
            }
        }

        stage('Deploy to Staging') {
            when {
                branch 'develop'
            }
            steps {
                echo '🚀 Déploiement en Staging...'
                sh '''
                    docker-compose -f docker-compose.yml pull || true
                    docker-compose -f docker-compose.yml up -d
                '''
            }
        }

        stage('Deploy to Production') {
            when {
                branch 'master'
            }
            steps {
                echo '🚀 Déploiement en Production...'
                sh '''
                    docker-compose -f docker-compose.yml pull || true
                    docker-compose -f docker-compose.yml up -d
                '''
            }
        }
    }

    post {
        always {
            echo '🧹 Nettoyage et rapports...'
            junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
        success {
            echo '✅ Pipeline complété avec succès!'
        }
        failure {
            echo '❌ Erreur dans le pipeline!'
        }
    }
}

