import javafx.beans.binding.Bindings
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.scene.control.Label
import javafx.scene.control.Separator
import javafx.scene.layout.HBox

class StatusbarView(val viewController: ViewController, val model: Model) : HBox() {
    init {
        padding = Insets(5.0)
        children.addAll(
            Label().apply {
                textProperty().bind(
                    Bindings.createStringBinding({
                        model.imageCountProperty.value.toString()
                    }, model.imageCountProperty)
                )
            },
            Label(" images loaded"),
            Separator(Orientation.VERTICAL).apply { padding = Insets(0.0, 3.0, 0.0, 7.0) },
            Label().apply {
                textProperty().bind(
                    Bindings.createStringBinding({
                        when (model.selectedImageProperty.value?.image?.url) {
                            null -> "Select an image"
                            else -> model.selectedImageProperty.value?.image?.url
                        }
                    }, model.selectedImageProperty)
                )
            },
        )
    }
}