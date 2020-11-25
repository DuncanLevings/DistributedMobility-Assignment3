package sheridan.levings.assignment3.network

data class FlowerJson(
    val label: String,
    val text: String,
    var price: String,
    val pictures: PicturesJson)