# HUD Player Mini (Fabric, Client-side)

Shows your live 3D player model in the **bottom-right HUD** with no frame, tiny "mini-me".
- Updates in real time (movement, armor, held item).
- Client-only (safe for servers).
- Uses Fabric API's HudRenderCallback and `InventoryScreen.drawEntity`.

## Build (one command)
1) Install JDK 17.
2) In this folder, run:
   - Windows: `gradlew.bat build`
   - macOS/Linux: `./gradlew build`
3) The mod jar will appear under `build/libs/` (the `-all` or `-dev` jars are not for distribution).

## Configure versions
Open `gradle.properties` and set:
```
minecraft_version=1.21.7
yarn_mappings=1.21.7+build.1
loader_version=0.16.7
fabric_api_version=0.**.*+1.21.7
```
Use the exact versions you have installed. If 1.21.7 yarn is not available, use the closest 1.21.x that matches your Fabric API.

## Position/Size
Edit values in `HUDPlayerMiniClient.java`:
- `PADDING` (distance from edges)
- `MODEL_HEIGHT` (vertical lift from bottom)
- `SCALE` (size of the model)

## Notes
- No frames or UI elements are drawn—just the 3D player model.
- If other HUD mods overlap the corner, increase the padding or move `x`/`y` math.
- This is client-side only; it won't affect server gameplay.
