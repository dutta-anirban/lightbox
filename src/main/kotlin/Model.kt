import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.image.Image
import javafx.scene.image.ImageView

class Model {
    private val _images = mutableListOf<Image>()
    private var _curview: IView? = null
    val enableTransformProperty: SimpleBooleanProperty = SimpleBooleanProperty(false)
    val selectedImageProperty: SimpleObjectProperty<ImageView?> = SimpleObjectProperty(null)
    val imageCountProperty: SimpleObjectProperty<Int> = SimpleObjectProperty(0)

    init {
        // Sample images:
        // addImage(Image("https://upload.wikimedia.org/wikipedia/commons/0/0e/Lady_Gaga_at_Joe_Biden%27s_inauguration_%28cropped_5%29.jpg"))
        // addImage(Image("https://upload.wikimedia.org/wikipedia/commons/0/00/Alia_Bhatt_in_RRR.png"))
        // addImage(Image("https://upload.wikimedia.org/wikipedia/commons/6/6d/L%C3%AA_Ti%E1%BA%BFn_Ch%C3%A2u.jpg"))
        // addImage(Image("https://upload.wikimedia.org/wikipedia/commons/3/3a/Tony_Bennett_%26_Lady_GaGa%2C_Cheek_to_Cheek_Tour_04_%28cropped%29.jpg"))
        // addImage(Image("https://cdn.britannica.com/73/146573-050-BA7ECF8A/meat-dress-Lady-Gaga-award-video-Bad-September-2010.jpg"))
        selectedImageProperty.addListener { _, old, new ->
            onImageChanged(old, new)
        }
    }

    fun setView(view: IView) {
        selectedImageProperty.value = null
        _curview = view
        for (image in _images) notifyViewImageAdded(image)
    }

    private fun notifyViewImageAdded(image: Image) {
        _curview?.imageAdded(ImageView(image).apply {
            fitWidth = 512.0
            fitHeight = 512.0
            isSmooth = true
            isPreserveRatio = true
        })
    }

    fun addImage(image: Image) {
        _images.add(image)
        notifyViewImageAdded(image)
        imageCountProperty.value = _images.size
    }

    fun removeSelectedImage() {
        if (selectedImageProperty.value == null)
            return
        val imv = selectedImageProperty.value!!
        _curview?.imageRemoved(imv)
        _images.remove(imv.image)
        selectedImageProperty.value = null
        imageCountProperty.value = _images.size
    }

    fun removeAllImages() {
        _curview?.imagesRemoved()
        _images.clear()
        selectedImageProperty.value = null
        imageCountProperty.value = _images.size
    }

    private fun onImageChanged(oldView: ImageView?, newView: ImageView?) {
        _curview?.onImageChanged(oldView, newView)
    }
}