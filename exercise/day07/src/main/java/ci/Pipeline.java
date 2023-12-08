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
        boolean deploySuccessful = false;
        if (testsPassed) {
            deploySuccessful = deploy(project);
        }
        sendEmailSummary(testsPassed, deploySuccessful);
    }

    private boolean runTests(Project project) {
        if (!project.hasTests()) {
            log.info("No tests");
            return true;
        }
        if (ExecutionResult.SUCCESS == project.runTests()) {
            log.info("Tests passed");
            return true;
        }
        log.error("Tests failed");
        return false;
    }

    private boolean deploy(Project project) {
        if (ExecutionResult.SUCCESS == project.deploy()) {
            log.info("Deployment successful");
            return true;
        }
        log.error("Deployment failed");
        return false;
    }

    private void sendEmailSummary(boolean testsPassed, boolean deploySuccessful) {
        if (!config.sendEmailSummary()) {
            log.info("Email disabled");
            return;
        }

        log.info("Sending email");
        if (!testsPassed) {
            emailer.send("Tests failed");
            return;
        }

        if (deploySuccessful) {
            emailer.send("Deployment completed successfully");
        } else {
            emailer.send("Deployment failed");
        }
    }
}
