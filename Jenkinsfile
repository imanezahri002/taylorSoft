pipeline {
    agent any

    environment {
        PROJECT_NAME = 'taylorsoft'
        IMAGE_TAG = "${BUILD_NUMBER}"
        REGISTRY = 'localhost:5000'
        MAVEN_OPTS = '-Xmx1024m -Xms512m'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 1, unit: 'HOURS')
        timestamps()
    }

    stages {
        stage('🔍 Checkout') {
            steps {
                echo '📦 Récupération du code source...'
                checkout scm
                script {
                    env.GIT_COMMIT_MSG = sh(returnStdout: true, script: 'git log -1 --pretty=%B').trim()
                    env.GIT_AUTHOR = sh(returnStdout: true, script: 'git log -1 --pretty=%an').trim()
                    echo "✓ Branch: ${env.GIT_BRANCH}"
                    echo "✓ Commit: ${env.GIT_COMMIT}"
                    echo "✓ Author: ${env.GIT_AUTHOR}"
                }
            }
        }

        stage('🔨 Build') {
            steps {
                echo '🔨 Compilation du projet Maven...'
                sh '''
                    chmod +x ./mvnw
                    ./mvnw clean package -DskipTests -q
                    echo "✓ Build réussi"
                '''
            }
        }

        stage('🧪 Tests') {
            steps {
                echo '🧪 Exécution des tests unitaires...'
                script {
                    try {
                        sh './mvnw test -q'
                        echo "✓ Tests réussis"
                    } catch (Exception e) {
                        echo "⚠️ Tests échoués mais poursuite du pipeline"
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('📊 SonarQube Analysis') {
            when {
                expression {
                    env.GIT_BRANCH == 'origin/master' || env.GIT_BRANCH == 'origin/develop'
                }
            }
            steps {
                echo '📊 Analyse de qualité du code...'
                script {
                    try {
                        sh '''
                            ./mvnw sonar:sonar \
                              -Dsonar.projectKey=taylorsoft \
                              -Dsonar.host.url=http://sonarqube:9000 \
                              -Dsonar.login=${SONARQUBE_TOKEN} || echo "SonarQube non disponible"
                        '''
                    } catch (Exception e) {
                        echo "⚠️ SonarQube échoué"
                    }
                }
            }
        }

        stage('📁 Archive Results') {
            steps {
                echo '📁 Archivage des artefacts...'
                script {
                    def jarExists = sh(script: 'test -f target/*.jar', returnStatus: true)
                    if (jarExists == 0) {
                        echo "✓ Artefact JAR trouvé"
                    } else {
                        echo "⚠️ Pas de JAR généré"
                    }
                }
            }
        }

        stage('🐳 Build Docker Image') {
            when {
                expression {
                    fileExists('Dockerfile') && (env.GIT_BRANCH == 'origin/master' || env.GIT_BRANCH == 'origin/develop')
                }
            }
            steps {
                echo '🐳 Construction de l\'image Docker...'
                script {
                    try {
                        sh '''
                            docker build \
                              -t ${REGISTRY}/${PROJECT_NAME}:${IMAGE_TAG} \
                              -t ${REGISTRY}/${PROJECT_NAME}:latest \
                              -f Dockerfile .
                            echo "✓ Image Docker créée: ${REGISTRY}/${PROJECT_NAME}:${IMAGE_TAG}"
                        '''
                    } catch (Exception e) {
                        echo "❌ Erreur Docker build"
                        currentBuild.result = 'FAILURE'
                    }
                }
            }
        }

        stage('🚀 Deploy') {
            when {
                expression { fileExists('docker-compose.yml') && env.GIT_BRANCH == 'origin/master' }
            }
            steps {
                echo '🚀 Déploiement en Production...'
                script {
                    try {
                        sh '''
                            docker-compose down --remove-orphans || true
                            docker-compose up -d
                            sleep 10
                            docker-compose ps
                            echo "✓ Déploiement réussi"
                        '''
                    } catch (Exception e) {
                        echo "❌ Erreur déploiement"
                        currentBuild.result = 'FAILURE'
                    }
                }
            }
        }
    }

    post {
        always {
            node('master') {
                echo '📊 Rapport final du build'
                script {
                    junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
                    archiveArtifacts artifacts: '**/target/*.jar,**/target/*.war', allowEmptyArchive: true

                    def testSummary = sh(returnStdout: true, script: '''
                        if [ -f "target/surefire-reports/index.html" ]; then
                            grep -oP '(?<=Tests run: )\\d+' target/surefire-reports/index.html || echo "Tests: 0"
                        else
                            echo "Pas de rapport de tests"
                        fi
                    ''').trim()

                    echo "Tests résumé: ${testSummary}"
                }
            }
        }

        success {
            echo '✅ Pipeline complété avec succès!'
            echo "Build ${env.BUILD_NUMBER} - ${env.PROJECT_NAME}:${IMAGE_TAG}"
        }

        unstable {
            echo '⚠️ Pipeline complété avec des avertissements'
            echo "Consultez les logs pour plus de détails"
        }

        failure {
            echo '❌ Pipeline échoué'
            echo "Build ${env.BUILD_NUMBER} - Vérifiez les logs"
        }

        cleanup {
            node('master') {
                cleanWs()
            }
        }
    }
}
