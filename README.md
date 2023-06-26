# Lightbox

```
kotlinc-jvm 1.8.21-release-380 (JRE 1.8.0_371-b11)
Windows 10 Home 64-bit 22H2 (Build 19044.1387)
```

## Project Specification

### 1. Tool Bar

The Tool Bar contains the following buttons with images, text, and tooltips:

- `Add Image`: Opens a file chooser dialog to select image files to add to the Lightbox in a random position.
  All images are scaled to the same size when loaded, and appear proportionally in the Lightbox.
- `Delete Image`: Deletes the currently selected image from the Lightbox.
  If no image is selected, the button is disabled.
- `Delete All`: Deletes all images from the Lightbox. If no images are present, the button is disabled.
- `Rotate Left`: Rotates the currently selected image 10 degrees counter-clockwise.
  If no image is selected, the button is disabled.
- `Rotate Right`: Rotates the currently selected image 10 degrees clockwise.
  If no image is selected, the button is disabled.
- `Zoom In`: Increases the size of the currently selected image by 25%.
  If no image is selected, the button is disabled.
- `Zoom Out`: Decreases the size of the currently selected image by 25%.
  If no image is selected, the button is disabled.
- `Reset`: Resets the currently selected image to its original size and rotation.
  If no image is selected, the button is disabled.
- `Tile`: Automatically repositions the images so that they do not overlap.
    - Changing from `Cascade` to `Tile` removes any previous transformations the user applied, and then translates all
      the
      images to fit onto the existing as evenly as possible without overlapping.
    - If there are too many images to fit in the space, the excess are added below and a scroll bar becomes visible on
      the
      right hand side.
    - Image deletion reformats this view to factor in the newfound space.
    - Transformations are not allowed in this mode. The relevant buttons are disabled.
    - Resizing the window causes the images to reflow to fit the new window size.
- `Cascade`: Allows the user to move slides and allows images to overlap. This is the default mode when the application
  is launched.
    - Changing from `Tile` to `Cascade` mode does not change the image properties, but enables transformation
      capabilities
      so that the users can scale, rotate, and translate freely.
    - Resizing the window in this mode does not move images form their positions. If the window is resized in a way that
      obscures the image, a scroll bar appears on the right hand side or the bottom, accordingly. These scrollbars only
      appear when necessary, and are otherwise not visible.

### 2. Preview Pane

The Preview Pane is the focus of the interface, and is an empty surface where the user can place images. It supports the
following interactions:

- Clicking an image that is on the pane will select it and bring it on to the foreground. The selected image gets a
  blue border and a shadow around it. Only one image can be selected at a time. Clicking on the pane background
  deselects the image. Image refactoring buttons are disabled when no image is selected.
- Dragging a selected image will move it. Translation tracks the mouse movement, and the image is moved by the same
  amount. The image is moved to the front of the stack of images, and is brought to the foreground. This works
  regardless
  of the current image properties (zoom, rotation, etc.).

### 3. Status Bar

The Status Bar displays the following information:

- The number of images currently in the Lightbox.
- The name of the currently selected image.

## Attributions:

Icons8 Windows 11 Outline pack (https://icons8.com). The following icons were used:

| Icon                                                  | Text         | Size    | Source                                                   |
|-------------------------------------------------------|--------------|---------|----------------------------------------------------------|
| ![Add Image Icon](src/main/resources/add.png)         | Add Image    | 20 x 20 | https://icons8.com/icon/Lyu8C5wFtEAY/insert-raster-image |
| ![Delete Image Icon](src/main/resources/delete.png)   | Delete Image | 20 x 20 | https://icons8.com/icon/EcyViLVAqLKy/remove-image        |
| ![Delete All Icon](src/main/resources/delete_all.png) | Delete All   | 20 x 20 | https://icons8.com/icon/eTfmsOcKBClu/delete-all          |
| ![Rotate Left](src/main/resources/rotate_left.png)    | Rotate Left  | 20 x 20 | https://icons8.com/icon/NnMiWA88umSu/rotate-left         |
| ![Rotate Right](src/main/resources/rotate_right.png)  | Rotate Right | 20 x 20 | https://icons8.com/icon/ldOKxBGqxxa9/rotate-right        |
| ![Zoom In](src/main/resources/zoom_in.png)            | Zoom In      | 20 x 20 | https://icons8.com/icon/2vADHy9Z7ATx/zoom-in             |
| ![Zoom Out](src/main/resources/zoom_out.png)          | Zoom Out     | 20 x 20 | https://icons8.com/icon/B3rtongZbJz8/zoom-out            |
| ![Reset](src/main/resources/reset.png)                | Reset        | 20 x 20 | https://icons8.com/icon/XjogNOTUqTEK/synchronize         |
| ![Cascade](src/main/resources/cascade.png)            | Cascade      | 20 x 20 | https://icons8.com/icon/xkxYbc0WQHuF/four-squares        |
| ![Tile](src/main/resources/tile.png)                  | Tile         | 20 x 20 | https://icons8.com/icon/8mWYveU7TJqt/sheets              |
| ![Logo](src/main/resources/logo.png)                  | App Logo     | 40 x 40 | https://icons8.com/icon/OGFBT28VCWGI/light-box           |
