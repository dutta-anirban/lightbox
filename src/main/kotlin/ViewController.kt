import javafx.scene.control.ScrollPane
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import kotlin.math.round

class ViewController(val stage: Stage, private val model: Model) : BorderPane() {
    enum class LAYOUT { Cascade, Tile }

    init {
        top = ToolbarView(this, model)
        bottom = StatusbarView(this, model)
    }

    fun setLayout(layout: LAYOUT) {
        center = when (layout) {
            LAYOUT.Cascade -> CascadeView(model)
            LAYOUT.Tile -> ScrollPane(TileView(model).apply {
            }).apply {
                isFitToWidth = true
                isFitToHeight = true
            }
        }
    }

    fun zoomSelectedImage(zoom: Double) {
        val imv = model.selectedImageProperty.value!!
        imv.fitWidth = imv.fitWidth * zoom
        imv.fitHeight = imv.fitHeight * zoom
    }

    fun rotateSelectedImage(angle: Double) {
        val imv = model.selectedImageProperty.value!!
        imv.parent.rotate = (round(imv.parent.rotate + angle)) % 360
    }

    fun resetSelectedImage() {
        val imv = model.selectedImageProperty.value!!
        imv.fitWidth = 512.0
        imv.fitHeight = 512.0
        imv.parent.rotate = 0.0
    }
}
