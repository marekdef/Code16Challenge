package pl.senordeveloper.code16challenge.model

data class Location(
    val city: String,
    val country: String,
    val state: String,
    val street: String,
    val timezone: String
) {
    val formatted: CharSequence = "$street\n$city, $state $country"
}