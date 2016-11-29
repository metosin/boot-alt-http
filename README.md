# Boot-alt-http [![Clojars Project](https://img.shields.io/clojars/v/metosin/boot-alt-http.svg)](https://clojars.org/metosin/boot-alt-http)

Simple Boot http server task to serve files from classpath.

## Features

- By default uses a free port
- Serves index.html for URIs pointing to a directory
- Only serves files from classpath, no options to serve files from file system or
use Ring handler. Use [something](https://github.com/pandeiro/boot-http)
[else](https://github.com/danielsz/system) for that!

## TODO / Ideas

- Serve only files with Boot output [fileset role](https://github.com/boot-clj/boot/wiki/Filesets#fileset-components)

## License

Copyright © 2016 Juho Teperi

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
