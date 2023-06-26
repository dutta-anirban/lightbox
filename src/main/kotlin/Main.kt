import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

class LightboxApp : Application() {
    override fun start(stage: Stage) {
        val root = ViewController(stage, Model())
        stage.apply {
            scene = Scene(root)
            icons.add(Image("logo.png"))
            width = 1000.0
            height = 800.0
            maxWidth = 1600.0
            maxHeight = 1200.0
            title = "Lightbox (c) 2023 Anirban Dutta"
        }.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(LightboxApp::class.java, *args)
}