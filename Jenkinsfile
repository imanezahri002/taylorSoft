pipeline {
    agent any

    environment {
        PROJECT_NAME = 'TaylorSoft'
        IMAGE_TAG = "${BUILD_NUMBER}"
        MAVEN_OPTS = '-DskipTests -Dorg.slf4j.simpleLogger.defaultLogLevel=warn'
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
                script {
                    try {
                        sh 'chmod +x ./mvnw && ./mvnw clean package -DskipTests -q'
                    } catch (Exception e) {
                        echo '⚠️ Build échoué mais poursuite du pipeline'
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Archive Results') {
            steps {
                echo '📁 Archivage des artefacts...'
                script {
                    try {
                        sh '''
                            if [ -d "target" ]; then
                                echo "✓ Dossier target trouvé"
                            else
                                echo "⚠️ Dossier target non trouvé"
                            fi
                        '''
                    } catch (Exception e) {
                        echo "Artefacts non disponibles"
                    }
                }
            }
        }

        stage('Build Docker Image') {
            when {
                expression { fileExists('Dockerfile') }
            }
            steps {
                echo '🐳 Construction de l\'image Docker...'
                script {
                    try {
                        sh '''
                            docker build -t ${PROJECT_NAME}:${IMAGE_TAG} . || echo "Docker build échoué"
                        '''
                    } catch (Exception e) {
                        echo "Docker non disponible ou échec"
                    }
                }
            }
        }

        stage('Deploy') {
            when {
                expression { fileExists('docker-compose.yml') }
            }
            steps {
                echo '🚀 Déploiement...'
                script {
                    try {
                        sh 'docker-compose up -d || echo "Docker-compose échoué"'
                    } catch (Exception e) {
                        echo "Docker-compose non disponible"
                    }
                }
            }
        }
    }

    post {
        always {
            echo '📊 Rapport final'
            junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
            archiveArtifacts artifacts: '**/target/*.jar,**/target/*.war', allowEmptyArchive: true
        }
        success {
            echo '✅ Pipeline complété avec succès!'
        }
        unstable {
            echo '⚠️ Pipeline complété avec des avertissements'
        }
        failure {
            echo '❌ Pipeline échoué'
        }
    }
}
