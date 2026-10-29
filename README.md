# Ray Tracing in Java

A learning project: I'm working through Peter Shirley's [_Ray Tracing in One Weekend_](https://raytracing.github.io/books/RayTracingInOneWeekend.html) series and writing the code in **Java** instead of the book's C++.

The point is to learn how ray tracing works and get more practice with Java. It isn't meant to be a production renderer, and the code will change as I move through the chapters.

## Progress

- [x] Output an image (PPM format)
- [x] `Vec3` vector class (a Java `record`)
- [x] Color utility
- [x] Rays, a simple camera, and background
- [ ] Adding a sphere
- [ ] Surface normals and multiple objects
- [ ] Antialiasing
- [ ] Diffuse materials
- [ ] Metal
- [ ] Dielectrics
- [ ] Positionable camera
- [ ] Defocus blur
- [ ] Final render

## Porting from C++ to Java

Some things that work differently from the book's C++:

- **No operator overloading.** Vector math uses methods instead, so `a + b * t` becomes `a.plus(b.times(t))`.
- **Immutable vectors.** `Vec3` is a `record`, so every operation returns a new vector and nothing is modified in place.
- **Output.** The image goes to `image.ppm` through a `PrintWriter`, and progress prints to `stderr`.

## Running

Requires a recent JDK (records and switch expressions are used).

Run `org.example.Main` from your IDE, or from the command line:

```sh
./gradlew build
java -cp build/classes/java/main io.github.yeekevin.raytracer.Main
```

That writes `image.ppm` to the project root. You can open it with an image viewer that supports PPM, such as Preview on macOS, GIMP, or an online PPM viewer.

## Credits

- [_Ray Tracing in One Weekend_](https://raytracing.github.io/), by Peter Shirley, Trevor David Black, and Steve Hollasch. The original series and C++ code come from this book; this repo is my own Java version written while learning from it.
