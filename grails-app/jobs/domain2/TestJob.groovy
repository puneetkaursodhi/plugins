package domain2


class TestJob {
    static triggers = {
        simple repeatInterval: 5000l // execute job once in 5 seconds
//        simple name: 'mySimpleTrigger', startDelay: 60000, repeatInterval: 1000
//        cron name: 'myTrigger', cronExpression: "0 17 18 * * ?"
//        cron cronExpression: "0 0 1 ? * MON *"

    }

    def execute() {
        // execute job
        println "I am inside job...................."
    }
}
