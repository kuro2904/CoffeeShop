package vn.ltdt.coffeeshop_android_native.data.domains

data class Token(
    val type: String = "Bearer ",
    val token: String
)
