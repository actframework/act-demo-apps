### ACT Framework Demo Applications

Usage:

To start in dev mode:

```bash
cd /path/to/a/app
mvn clean compile exec:exec
```

To start in prod mode:

```bash
cd /path/to/a/app
mvn clean package
cd target/dist
unzip *.zip
./start
```
