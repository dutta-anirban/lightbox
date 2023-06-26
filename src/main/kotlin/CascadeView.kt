import javafx.event.EventHandler
import javafx.scene.control.ScrollPane
import javafx.scene.image.ImageView
import javafx.scene.layout.Background
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane

class CascadeView(private val model: Model) : IView, ScrollPane() {

    private var initCountX = 0 // FIXME: Dirty hack!
    private var initCountY = 0

    private val pane = Pane().apply {
        background = Background.EMPTY
    }

    init {
        model.setView(this)
        model.enableTransformProperty.value = true
        content = pane
        translateZ = 0.0

        setOnMousePressed {
            // If what we clicked has z = 0 (i.e. is the background)
            if (it.z == 0.0) model.selectedImageProperty.value = null
        }

        widthProperty().addListener { _, _, v ->
            if (initCountX > 0)
                return@addListener
            for (child in pane.children) {
                child.layoutX = (v.toDouble() - child.boundsInLocal.width - 5.0
                        ).coerceAtLeast(0.0) * Math.random()
            }
            initCountX++
        }
        heightProperty().addListener { _, _, v ->
            if (initCountY > 0)
                return@addListener
            for (child in pane.children) {
                child.layoutY = (v.toDouble() - child.boundsInLocal.height - 5.0
                        ).coerceAtLeast(0.0) * Math.random()
            }
            initCountY++
        }
    }

    override fun imageAdded(imageView: ImageView) {
        var startX = 0
        var startY = 0
        val self = this
        pane.children.add(StackPane().apply {
            children.add(imageView)
            translateZ = 1.0
            onMousePressed = EventHandler { e ->
                model.selectedImageProperty.value = this.children[0] as ImageView
                startX = e.sceneX.toInt() - this.layoutX.toInt()
                startY = e.sceneY.toInt() - this.layoutY.toInt()
            }
            onMouseDragged = EventHandler { e ->
                this.layoutX = 0.0.coerceAtLeast(e.sceneX - startX)
                this.layoutY = 0.0.coerceAtLeast(e.sceneY - startY)
            }
            if (initCountX >= 1 && initCountY >= 1) {
                layoutX = (self.width - this.boundsInLocal.width - 5.0).coerceAtLeast(0.0) * Math.random()
                layoutY = (self.height - this.boundsInLocal.height - 5.0).coerceAtLeast(0.0) * Math.random()
            }
        })
    }

    override fun imageRemoved(imageView: ImageView) {
        pane.children.remove(imageView.parent)
    }

    override fun onImageChanged(oldView: ImageView?, newView: ImageView?) {
        oldView?.parent?.style = ""
        newView?.parent?.toFront()
        newView?.parent?.style = """
            -fx-border-color: #00dfff;
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);
        """.trimIndent()
    }

    override fun imagesRemoved() {
        pane.children.clear()
    }
}