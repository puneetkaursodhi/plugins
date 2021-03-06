package bootcamp

class Employee implements Serializable {
    String firstName
    String lastName
    String email
    String password
    String confirmPassword
    Date dateCreated
    Date lastUpdated
//    Long personId


    static hasMany = [addresses: Address]

    static transients = ['fullName', 'confirmPassword']


    static mapping = {
        table 'person'
        firstName column: 'fname', type: 'text'
//        id name: 'personId'
        autoTimestamp(false)
        sort("firstName": "desc")
//        version(false)
          addresses cascade: 'all-delete-orphan'
    }


    static constraints = {
        email(unique: true, email: true)
        dateCreated(nullable: true)
        lastUpdated(nullable: true)
        confirmPassword(bindable: true, nullable: true, blank: true, validator: { val, obj ->
            if (!obj.id && (obj.password != val || !val)) {
                return 'password.do.not.match.confirmPassword'
            }
        })
        password(size: 5..15, blank: false, validator: { val, obj ->
            if (val?.equalsIgnoreCase(obj.firstName)) {
                String suggestion = obj.firstName.reverse()
                return ["password.cannot.be.firstname", suggestion]
            }
        })
    }

    String getFullName() {
        [firstName, lastName].findAll { it }.join(' ')

    }

    String toString() {
        firstName
    }
}
