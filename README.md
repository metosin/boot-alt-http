# Boot-alt-http [![Clojars Project](https://img.shields.io/clojars/v/metosin/boot-alt-http.svg)](https://clojars.org/metosin/boot-alt-http)

Simple Boot http server task to serve files

## Features

- By default uses a free port
- Serves index.html for URIs pointing to a directory
- Serve only files with Boot output [fileset role](https://github.com/boot-clj/boot/wiki/Filesets#fileset-components)
    - Works when combined with `target` task and serving files from a directory:
    `(comp ... (target :dir #{"boot-dev-target"}) (server :directories #{"boot-dev-target/public"}))`

## License

Copyright © 2016-2017 Juho Teperi

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
