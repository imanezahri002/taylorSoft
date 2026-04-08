pipeline {
    agent any

    environment {
        // Variables d'environnement Maven
        MAVEN_HOME = tool 'Maven'
        PATH = "${MAVEN_HOME}/bin:${PATH}"

        // Variables AWS S3
        AWS_ACCESS_KEY_ID = credentials('aws-access-key-id')
        AWS_SECRET_ACCESS_KEY = credentials('aws-secret-access-key')
        AWS_REGION = 'eu-north-1'
        AWS_S3_BUCKET = credentials('aws-s3-bucket')

        // Variables du projet
        PROJECT_NAME = 'TaylorSoft'
        DOCKER_REGISTRY = 'your-registry'
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

        stage('SonarQube Analysis') {
            steps {
                echo '📊 Analyse SonarQube...'
                sh '''
                    mvn sonar:sonar \
                        -Dsonar.projectKey=TaylorSoft \
                        -Dsonar.sources=src \
                        -Dsonar.host.url=http://sonarqube:9000 \
                        -Dsonar.login=${SONARQUBE_TOKEN}
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                echo '🐳 Construction de l\'image Docker...'
                sh '''
                    docker build -t ${DOCKER_REGISTRY}/${PROJECT_NAME}:${IMAGE_TAG} .
                    docker tag ${DOCKER_REGISTRY}/${PROJECT_NAME}:${IMAGE_TAG} ${DOCKER_REGISTRY}/${PROJECT_NAME}:latest
                '''
            }
        }

        stage('Push Docker Image') {
            steps {
                echo '📤 Push de l\'image Docker...'
                sh '''
                    docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD} ${DOCKER_REGISTRY}
                    docker push ${DOCKER_REGISTRY}/${PROJECT_NAME}:${IMAGE_TAG}
                    docker push ${DOCKER_REGISTRY}/${PROJECT_NAME}:latest
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
                    docker-compose -f docker-compose.yml pull
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
                    docker-compose -f docker-compose.yml pull
                    docker-compose -f docker-compose.yml up -d
                '''
            }
        }
    }

    post {
        always {
            echo '🧹 Nettoyage...'
            cleanWs()
        }
        success {
            echo '✅ Pipeline complété avec succès!'
            // Vous pouvez ajouter des notifications Slack/Email ici
        }
        failure {
            echo '❌ Erreur dans le pipeline!'
            // Vous pouvez ajouter des notifications Slack/Email ici
        }
    }
}

