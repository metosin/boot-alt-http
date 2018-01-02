## 0.2.0 (2018-01-02)

- Support serving files from directories
    - Useful with `target` task
    - Target directory state doesn't change during the compilation
    so using this instead of classpath will prevent not-found errors.
    - Correctly ignores files in fileset with only [input role](https://github.com/boot-clj/boot/wiki/Filesets#roles-of-files)

## 0.1.2 (2017-01-31)

- Close HTTP server on Boot cleanup hook

## 0.1.1

- Fix: HTTP server was started each time task runs, instead of only the first time

## 0.1.0

- Initial release
