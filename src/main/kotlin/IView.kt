import javafx.scene.image.ImageView

interface IView {
    fun imageAdded(imageView: ImageView)
    fun imageRemoved(imageView: ImageView)
    fun imagesRemoved()
    fun onImageChanged(oldView: ImageView?, newView: ImageView?)
}
