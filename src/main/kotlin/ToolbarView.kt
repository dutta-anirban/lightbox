import javafx.beans.binding.Bindings
import javafx.scene.control.Button
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToolBar
import javafx.scene.control.Tooltip
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.HBox.setHgrow
import javafx.scene.layout.Priority
import javafx.stage.FileChooser

class ToolbarView(private val viewController: ViewController, private val model: Model) : ToolBar() {

    init {
        translateZ = 3.0
        val disableBtnBinding = Bindings.createBooleanBinding({
            model.selectedImageProperty.value != null && model.enableTransformProperty.value
        }, model.selectedImageProperty, model.enableTransformProperty).not()

        val btnAddImg = createButton("Add Image", "add.png", "Add Image")
        val btnDelImg = createButton("Delete Image", "delete.png", "Delete Image").apply {
            disableProperty().bind(Bindings.createBooleanBinding({
                model.selectedImageProperty.value == null
            }, model.selectedImageProperty))
        }
        val btnDelAll = createButton("Delete All", "delete_all.png", "Delete All Images").apply {
            disableProperty().bind(Bindings.createBooleanBinding({
                model.imageCountProperty.value == 0
            }, model.imageCountProperty))
        }
        val btnRotCcw = createButton("Rotate Left", "rotate_left.png", "Rotate Left").apply {
            disableProperty().bind(disableBtnBinding)
        }
        val btnRotCw = createButton("Rotate Right", "rotate_right.png", "Rotate Right").apply {
            disableProperty().bind(disableBtnBinding)
        }
        val btnZoomIn = createButton("Zoom In", "zoom_in.png", "Zoom In").apply {
            disableProperty().bind(disableBtnBinding)
        }
        val btnZoomOut =
            createButton("Zoom Out", "zoom_out.png", "Zoom Out").apply { disableProperty().bind(disableBtnBinding) }
        val btnReset = createButton("Reset", "reset.png", "Reset").apply { disableProperty().bind(disableBtnBinding) }
        val btnCascade = createToggleButton("Cascade", "cascade.png", "Cascade Images")
        val btnTiled = createToggleButton("Tile", "tile.png", "Tile Images")

        items.addAll(
            btnAddImg.apply { setOnAction { onAddImage() } },
            btnDelImg.apply { setOnAction { model.removeSelectedImage() } },
            btnDelAll.apply { setOnAction { model.removeAllImages() } },
            btnRotCw.apply { setOnAction { viewController.rotateSelectedImage(10.0) } },
            btnRotCcw.apply { setOnAction { viewController.rotateSelectedImage(-10.0) } },
            btnZoomIn.apply { setOnAction { viewController.zoomSelectedImage(1.25) } },
            btnZoomOut.apply { setOnAction { viewController.zoomSelectedImage(0.75) } },
            btnReset.apply { setOnAction { viewController.resetSelectedImage() } },
            HBox().apply { setHgrow(this, Priority.ALWAYS) },

            // Switch view mutual toggle buttons
            btnCascade.apply {
                setOnAction {
                    viewController.setLayout(ViewController.LAYOUT.Cascade)
                    btnCascade.isSelected = true
                    btnTiled.isSelected = false
                }
                this.isSelected = true
            },
            btnTiled.apply {
                setOnAction {
                    viewController.setLayout(ViewController.LAYOUT.Tile)
                    btnCascade.isSelected = false
                    btnTiled.isSelected = true
                }
                this.isSelected = false
            }
        )

        // Update layout view AFTER toolbar construction
        viewController.setLayout(ViewController.LAYOUT.Cascade)
    }

    private fun onAddImage() {
        val fileChooser = FileChooser().apply {
            title = "Open Image"
            extensionFilters.add(FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"))
        }
        val files = fileChooser.showOpenMultipleDialog(null)
        if (files != null) {
            for (file in files) {
                model.addImage(Image(file.toURI().toString()))
            }
        }
    }

    private fun createButton(name: String, imagePath: String, tooltipText: String): Button {
        val button = Button().apply {
            text = name
            tooltip = Tooltip(tooltipText)
            graphic = ImageView(Image(imagePath)).apply {
                fitWidth = 20.0
                fitHeight = 20.0
            }
            graphicTextGap = 3.0
        }
        return button
    }

    private fun createToggleButton(name: String, imagePath: String, tooltipText: String): ToggleButton {
        val button = ToggleButton().apply {
            text = name
            tooltip = Tooltip(tooltipText)
            graphic = ImageView(Image(imagePath)).apply {
                fitWidth = 20.0
                fitHeight = 20.0
            }
            graphicTextGap = 3.0
        }
        return button
    }
}