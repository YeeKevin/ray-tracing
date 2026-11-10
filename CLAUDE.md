# Ray Tracing in One Weekend, Java port

Learning project: a listing-by-listing Java port of the book (v4.0.2).

## Reference
~/references/raytracing — book text in books/, finished C++ in src/InOneWeekend/.

## Project
- Package io.github.yeekevin.raytracer, Java 21, build with ./gradlew build
- Main renders image.ppm to the project root (gitignored)

## Conventions
- Translate one listing at a time, keeping the book's comments
- Vec3 is an immutable record (used for points and colors); C++ operators become methods
- HitRecord is mutable; when adding a field, also update copyFrom()
- Material.scatter returns Optional<ScatterResult>; empty means absorbed
- Constants and random helpers live in RtWeekend
- Camera settings are public fields, like the book

## Workflow
- After each listing, briefly explain what changed and what the render should look like
- Build, then show the diff before committing
- Conventional Commits, e.g. feat(material): add Metal material
- Ask before adding dependencies
- After changes, run `./gradlew run && open -a Preview image.ppm` so I can check the render before committing
- After finishing a listing or section, update the Progress section of this file

## Progress
Done through Listing 87 (defocus blur / depth of field; section 13 complete). Next: Listing 88, final scene, section 14.
