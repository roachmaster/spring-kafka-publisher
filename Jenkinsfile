node {
    boolean runBuildAndTest = env.REPO_BUILD_TEST.toBoolean()
    boolean runDockerBuild = env.DOCKER_BUILD.toBoolean()
    boolean k3sBuild = env.K3S_BUILD.toBoolean()

    stage("Clone"){
        git 'git@github.com:roachmaster/spring-kafka-publisher.git'
    }

    stage("Build"){
        if(runBuildAndTest){
            sh "./gradlew clean build --info"
        }
    }

    stage("Docker Build"){
        if(runBuildAndTest && runDockerBuild){
            withCredentials([usernamePassword(credentialsId: '87e61f11-079d-4052-b083-ea5859f0f85b', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                def dockerVersion = sh(returnStdout: true, script: "./gradlew properties -q --no-daemon --console=plain -q | grep '^version:' | awk '{print \$2}'").trim()
                dockerBuild(dockerName:"${DOCKER_USERNAME}/kafka-publisher:${dockerVersion}",
                            dockerOpt:"--build-arg JAR_FILE=build/libs/ApacheKafkaPublisher-${dockerVersion}.jar",
                            DOCKER_PASSWORD: "${DOCKER_PASSWORD}",
                            DOCKER_USERNAME:"${DOCKER_USERNAME}")
            }
        }
    }

    stage("K3S Deployment"){
        if(k3sBuild){
            k3sDeployment name: "kafka-publisher"
            k3sService name: "kafka-publisher"
            waitForPodToBeReady name:"kafka-publisher", maxNumOfAttempts: 30
        }
    }

        stage("Run Acceptance Test"){
            sh "./gradlew acceptanceTest --info"
            cucumber buildStatus: 'null', customCssFiles: '', customJsFiles: '', failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '**/Cucumber-*.json', jsonReportDirectory: 'build/cucumber-reports', pendingStepsNumber: -1, reportTitle: 'ApacheKafkaPublisher', skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
        }
}