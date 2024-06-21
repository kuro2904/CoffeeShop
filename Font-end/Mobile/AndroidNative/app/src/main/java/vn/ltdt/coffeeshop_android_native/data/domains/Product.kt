package vn.ltdt.coffeeshop_android_native.data.domains

data class Product(
    val id: Int,
    val name: String,
    val categoryId: Int,
    val description: String,
    val categoryName: String?,
    val isActive: Boolean,
    val details:List<ProductDetail>,
    val images: List<String>
)
