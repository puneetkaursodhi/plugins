package domain2

import domain2.dto.EmailDTO
import grails.transaction.Transactional
import org.springframework.context.MessageSource

@Transactional
class EmailService {
    static transactional = false
    def mailService
    MessageSource messageSource
    def groovyPageRenderer

    void sendMail(EmailDTO emailDTO) {
        mailService.sendMail {
            to emailDTO.to.toArray()
            subject emailDTO.subject
            if (emailDTO.content) {
                html emailDTO.content
            } else {
                body(view: emailDTO.view, model: emailDTO.model)
            }
        }
    }

    void sendTestMail() {
        mailService.sendMail {
            to "puneet.kaur@tothenew.com"
            subject "Hello Puneet"
            body 'How are you?'
//            html '<b>Hello</b> World'
        }
    }
}
