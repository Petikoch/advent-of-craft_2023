package ci;

import ci.dependencies.*;

public class Pipeline {
    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    public Pipeline(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }

    public void run(Project project) {
        boolean testsPassed = runTests(project);
        boolean deploySuccessful = deploy(project, testsPassed);
        sendEmailSummary(testsPassed, deploySuccessful);
    }

    private boolean runTests(Project project) {
        boolean testsPassed;
        if (project.hasTests()) {
            if (ExecutionResult.SUCCESS == project.runTests()) {
                log.info("Tests passed");
                testsPassed = true;
            } else {
                log.error("Tests failed");
                testsPassed = false;
            }
        } else {
            log.info("No tests");
            testsPassed = true;
        }
        return testsPassed;
    }

    private boolean deploy(Project project, boolean testsPassed) {
        boolean deploySuccessful;
        if (testsPassed) {
            if (ExecutionResult.SUCCESS == project.deploy()) {
                log.info("Deployment successful");
                deploySuccessful = true;
            } else {
                log.error("Deployment failed");
                deploySuccessful = false;
            }
        } else {
            deploySuccessful = false;
        }
        return deploySuccessful;
    }

    private void sendEmailSummary(boolean testsPassed, boolean deploySuccessful) {
        if (config.sendEmailSummary()) {
            log.info("Sending email");
            if (testsPassed) {
                if (deploySuccessful) {
                    emailer.send("Deployment completed successfully");
                } else {
                    emailer.send("Deployment failed");
                }
            } else {
                emailer.send("Tests failed");
            }
        } else {
            log.info("Email disabled");
        }
    }
}