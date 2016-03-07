package com.grails.plugin.validAPI

public enum StatusCode {
    TWO_HUNDRED (200, "Gata/adress ej angivet"),
    TWO_HUNDRED_ONE (201, "Felaktigt gatunamn"),
    TWO_HUNDRED_TWO (202, "Gata existerar ej"),
    TWO_HUNDRED_THREE (203, "Gata/adress stämmer inte med angivet postnummer"),
    TWO_HUNDRED_FOUR (204, "Gata/adress stämmer inte med angiven postort"),
    TWO_HUNDRED_FIVE (205, "Gata/adress stämmer inte med angivet postnummer och postort"),
    TWO_HUNDRED_SIX (206, "Felaktigt gatunummer/adressplats"),
    TWO_HUNDRED_SEVEN (207, "Gatunummer/adressplats existerar ej på angiven adress"),
    TWO_HUNDRED_EIGHT (208, "Gatunummer/adressplats saknas"),
    TWO_HUNDRED_NINE (209, "Littera saknas"),
    TWO_HUNDRED_TEN (210, "Felaktigt littera"),
    TWO_HUNDRED_ELEVEN (211, "Littera existerar ej"),
    TWO_HUNDRED_TWELVE (212, "Litterauppgift saknas i PDB"),
    TWO_HUNDRED_THIRTEEN (213, "Felaktigt formaterad gata"),
    TWO_HUNDRED_FOURTEEN (214, "Gatuadress innehöll information som ej tillhörde själva gatunamnet"),
    TWO_HUNDRED_FIFTEEN (215, "Gatuadress saknas eller är ogiltig för normala brev och paket"),
    TWO_HUNDRED_SIXTEEN (216, "Gatunamn hade förkortningar"),
    TWO_HUNDRED_SEVENTEEN (217, "Boxadress fanns med i adress, men existerar ej"),
    TWO_HUNDRED_EIGHTEEN (218, "Både gatuadress och boxadress fanns i samma adress"),
    TWO_HUNDRED_NINETEEN (219, "Poste restante"),
    TWO_HUNDRED_TWENTY (220, "Stickvägsangivelse saknas"),
    TWO_HUNDRED_TWENTY_ONE (221, "Stickvägsangivelse felaktig"),
    TWO_HUNDRED_TWENTY_TWO (222, "Gata hade mindre felaktigheter, men i huvudsak korrekt"),
    TWO_HUNDRED_FIFTY_ONE (251, "Gatunummer/adressplats är normalt inte nödvändigt för denna adress även om det inte är fel"),
    TWO_HUNDRED_NINETY_NINE (299, "Gata OK"),
    THREE_HUNDRED (300, "Postnummer ej angivet"),
    THREE_HUNDRED_ONE (301, "Felaktigt postnummer"),
    THREE_HUNDRED_TWO (302, "Postnummer existerar ej"),
    THREE_HUNDRED_THREE (303, "Postnummer stämmer inte med angiven postort"),
    THREE_HUNDRED_FOUR (304, "Postnummer stämmer inte med angiven gata/adress"),
    THREE_HUNDRED_FIVE (305, "Postnummer stämmer inte med angiven gata/adress och/eller postort"),
    THREE_HUNDRED_SIX (306, "Icke fullständigt postnummer angivet"),
    THREE_HUNDRED_SEVEN (307, "Storkundspostnummer"),
    THREE_HUNDRED_NINETY_NINE (399, "Postnummer OK"),
    FOUR_HUNDRED (400, "Postort ej angivet"),
    FOUR_HUNDRED_ONE (401, "Felaktig postort"),
    FOUR_HUNDRED_TWO (402, "Postort existerar ej"),
    FOUR_HUNDRED_THREE (403, "Postorten stämmer inte med angivet postnummer"),
    FOUR_HUNDRED_FOUR (404, "Postorten stämmer inte med angiven gata/adress"),
    FOUR_HUNDRED_FIVE (405, "Postorten stämmer inte med angiven gata/adress och postnummer"),
    FOUR_HUNDRED_NINETY_NINE (499, "Postort OK"),
    SIX_HUNDRED_THREE (603, "Flera möjliga gatunummer/adressplatser finns"),
    SIX_HUNDRED_FOUR (604, "Postnummer och postort stämmer inte överens. Angiven gata/adress existerar både i postnumret och postorten."),
    SIX_HUNDRED_THIRTEEN (613, "Historisk ändring"),
    API_NOT_RESPONDING(-1, 'Valid API is not responding in the expected manner')

    Integer statusCode
    String message

    StatusCode(Integer statusCode, String message) {
        this.statusCode = statusCode
        this.message = message
    }

    static StatusCode findByCode(Integer code) {
        return StatusCode.values().find {it.statusCode == code}
    }

}