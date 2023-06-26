import javafx.geometry.Insets
import javafx.scene.image.ImageView
import javafx.scene.layout.FlowPane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox

class TileView(private val model: Model) : IView, FlowPane() {
    init {
        model.setView(this)
        model.enableTransformProperty.value = false
        padding = Insets(10.0)
        hgap = 10.0
        vgap = 10.0
        setOnMousePressed {
            model.selectedImageProperty.value = null
        }
    }

    override fun imageAdded(imageView: ImageView) {
        children.add(VBox(StackPane(imageView.apply {
            setOnMouseReleased {
                model.selectedImageProperty.value = this
            }
        })))
    }

    override fun imageRemoved(imageView: ImageView) {
        children.remove(imageView.parent?.parent)
    }

    override fun imagesRemoved() {
        children.clear()
    }

    override fun onImageChanged(oldView: ImageView?, newView: ImageView?) {
        oldView?.parent?.style = ""
        newView?.parent?.parent?.toBack()
        newView?.parent?.style = """
            -fx-border-color: #00dfff;
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);
        """.trimIndent()
    }
}