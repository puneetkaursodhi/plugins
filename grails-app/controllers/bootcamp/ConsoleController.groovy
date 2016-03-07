package bootcamp

import grails.converters.JSON
import org.grails.plugins.console.Evaluation

class ConsoleController extends org.grails.plugins.console.ConsoleController{

    def execute(String code, boolean autoImportDomains) {
        println "I am inside extended controller..........."
        Evaluation eval = consoleService.eval(code, autoImportDomains, request)

        Map result = [
                totalTime: eval.totalTime,
                output: eval.output
        ]
        if (eval.exception) {
            result.exception = [
                    stackTrace: eval.stackTraceAsString,
                    lineNumber: eval.exceptionLineNumber
            ]
            result.result = eval.exception.toString()
            result.resultTree = eval.exception
        } else {
            result.result = eval.resultAsString
        }

        JSON.use('console') {
            render result as JSON
        }
    }
}
