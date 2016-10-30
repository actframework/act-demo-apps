# ActFramework Upload Demo

This application demonstrates how to do upload in ActFramework. 

Note this application require you have mongodb installed and 
running on your local computer but no need to do any further
configuration except the default installation

## Start the application

Start the application in dev mode

```
mvn clean compile exec:exec
```

Start the application in prod mode

```
mvn clean package
cd target/dist
unzip *
./start
```

Once application has been started, you should see something like:
 
```
| | | | _ __  | |  ___    __ _   __| | |  _ \   ___  _ __ ___    ___  
| | | || '_ \ | | / _ \  / _` | / _` | | | | | / _ \| '_ ` _ \  / _ \ 
| |_| || |_) || || (_) || (_| || (_| | | |_| ||  __/| | | | | || (_) |
 \___/ | .__/ |_| \___/  \__,_| \__,_| |____/  \___||_| |_| |_| \___/ 
       |_|                                                            
                                   powered by ActFramework v0.3.0-0d33

 version: 0.0.1-SNAPSHOT
base dir: /home/luog/p/act/samples/upload/target/dist
 profile: prod
    mode: PROD

Oct 30, 2016 5:45:11 PM org.mongodb.morphia.logging.MorphiaLoggerFactory chooseLoggerFactory
INFO: LoggerImplFactory set to org.mongodb.morphia.logging.jdk.JDKLoggerFactory
17:45:11.394 [main] INFO  a.Act - loading application(s) ...
17:45:11.400 [main] INFO  a.Act - App starting ....
17:45:11.485 [main] WARN  a.c.AppConfig - Application secret key not set! You are in the dangerous zone!!!
17:45:11.523 [main] WARN  a.a.DbServiceManager - DB configuration not found. Will try to init default service with the sole db plugin: act.db.morphia.MorphiaPlugin@73545b80
17:45:11.638 [main] INFO  o.m.d.cluster - Cluster created with settings {hosts=[localhost:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms', maxWaitQueueSize=500}
17:45:11.692 [main] WARN  a.Act - No "db" (database name) configured. Will use "test" as database name for the default service
17:45:11.701 [main] INFO  a.s.StorageServiceManager - storage service[store1] initialized
17:45:11.702 [main] WARN  a.s.StorageServiceManager - Storage service configuration not found. Use the sole one storage service[store1] as default service
17:45:11.771 [cluster-ClusterId{value='581596f728525516b034b1c0', description='null'}-localhost:27017] INFO  o.m.d.connection - Opened connection [connectionId{localValue:1, serverValue:465}] to localhost:27017
17:45:11.773 [cluster-ClusterId{value='581596f728525516b034b1c0', description='null'}-localhost:27017] INFO  o.m.d.cluster - Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, version=ServerVersion{versionList=[3, 2, 10]}, minWireVersion=0, maxWireVersion=4, maxDocumentSize=16777216, roundTripTimeNanos=670374}
17:45:12.013 [main] WARN  a.m.MailerConfig - smtp host configuration not found, will use mock smtp to send email
17:45:12.013 [main] WARN  a.c.AppConfig - host is not configured. Use localhost as hostname
17:45:12.183 [main] INFO  o.m.d.connection - Opened connection [connectionId{localValue:2, serverValue:466}] to localhost:27017
17:45:12.194 [main] INFO  a.Act - App[Upload Demo] loaded in 794ms
17:45:12.207 [main] INFO  o.xnio - XNIO version 3.3.6.Final
17:45:12.220 [main] INFO  o.x.nio - XNIO NIO Implementation Version 3.3.6.Final
17:45:12.275 [main] INFO  a.Act - network client hooked on port: 5460
17:45:12.276 [main] INFO  a.Act - CLI server started on port: 5461
17:45:12.276 [main] INFO  a.b.a.RunApp - it takes 1557ms to start the app
```

Then you can can open browser at `http://localhost:5460` to get the home page.

## Understanding the Demo app

The demo application contains an entry Java class `demo.upload.UploadDemo` with the main method: 

```java
public class UploadDemo {

    ...
    
    public static void main(String[] args) throws Exception {
        RunApp.start("Upload Demo", Version.appVersion(), UploadDemo.class);
    }

}
```

The main method call `act.boot.app.RunApp.start(...)` method to bootstrap ActFramework and load this application:

* The `Version.appVersion()` is also provided by ActFramework, however it relies on the application to put a 
`app.version` file under the `resources` folder.

* The `UploadDemo.class` is the application entry class, which tells ActFramework it shall scan all classes under
`demo.upload` package

### Action handler

The `UploadDemo` class contains several action handler method:

```java
    // This is the root URL handler. It will load the template
    // stored in resources/rythm/demo/upload/UploadDemo/home.html
    @GetAction
    public void home() {
    }

    // This is the method to load testing page for SingleImage operation
    // It will read all SingleImage instances from database and render
    // into the template located at
    // resources/rythm/demo/upload/UploadDemo/singleImageHomt.html
    @GetAction("/single")
    public void singleImageHome() {
        List<SingleImage> images = singleDao.findAllAsList();
        render(images);
    }

    // This action handler demonstrates how to take java.util.File
    // as parameter to handle file upload.
    @PostAction("/single/by_file")
    public void uploadSingleWithFile(String title, File image) {
        singleDao.save(new SingleImage(title, SObject.of(image)));
        redirect("/single");
    }

    // This action handler demonstrates how to take org.osgl.storage.ISObject
    // as parameter to handle file upload.
    @PostAction("/single/by_sobj")
    public void uploadSingleWithSObject(String title, ISObject image) {
        singleDao.save(new SingleImage(title, image));
        redirect("/single");
    }

    // This action handler demonstrates how to use POJO binding
    // to process file upload
    @PostAction("/single/by_bind")
    public void uploadSingleWithAutoBinding(SingleImage image) {
        singleDao.save(image);
        redirect("/single");
    }

    // This action handler demonstrates how to delete a POJO entity
    // and it will automatically delete associated blob file
    @DeleteAction("/single/{id}")
    public void deleteSingleImage(@DbBind("id") SingleImage image) {
        notFoundIfNull(image);
        singleDao.delete(image);
        redirect("/single");
    }
```

### Configure storage service

The `storage.properties` file sit under `resources/conf/common` folder configure the default storage
 service configuration:
 
```
# For named storage service
ss.instances=store1
ss.store1.impl=act.storage.FileSystemStoragePlugin
ss.store1.home.dir=store1
ss.store1.staticWebEndpoint=/store1
ss.store1.get.MetaOnly=true
```

At the moment there is one storage service instance named `store` created. And the engine for `store` is
file system storage. 

### SingleImage model

The `SingleImage` model class contains an `ISObject` typed field, which link the database record to
a BLOB storage:

```java
/**
 * A model with single image attachment
 */
@Store("store1:") // specify the storage service ID
@Entity("simg") // specify the mongodb collection name
public class SingleImage extends MorphiaModel<SingleImage> {

    private String title;

    @Store // mark field as a storage item
    private ISObject image;

    private SingleImage() {}

    public SingleImage(String title, ISObject image) {
        this.title = $.notNull(title);
        this.image = $.notNull(image);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ISObject getImage() {
        return image;
    }

    public String getImageUrl() {
        return null == image ? null : image.getUrl();
    }

    public void setImage(ISObject image) {
        this.image = image;
    }
}
```

Note, application developer doesn't need to handle the persistence of the blob data (image). After one `SingleImage`
has been created and saved, it looks like the following JSON piece in mongodb:

```javascript
> db.simg.findOne()
{
	"_id" : ObjectId("5815970328525516b034b1c1"),
	"className" : "demo.upload.SingleImage",
	"image" : "2016/10/30/5a57e2cc-c4d4-4bf4-aa70-16bded646c72",
	"title" : "1",
	"_created" : NumberLong("1477809923438"),
	"_modified" : NumberLong("1477809923438"),
	"v" : NumberLong(1)
}
```

Note the `image` field value is `2016/10/30/5a57e2cc-c4d4-4bf4-aa70-16bded646c72`, it is actually a key to 
fetch the BLOB data from the configured storage service `store1`, which is set to link to file system under
folder `store1`. When we run the `tree` command under `store1` folder, we see something like:

```
 luog > ⋯ > target > dist > store1 > tree
.
└── 2016
    └── 10
        └── 30
            ├── 024479cb-5218-479f-8765-265e0b43ea9e
            ├── 024479cb-5218-479f-8765-265e0b43ea9e.attr
            ├── 576a599d-e3ed-4027-8980-2de8627f3cef
            ├── 576a599d-e3ed-4027-8980-2de8627f3cef.attr
            ├── 5a57e2cc-c4d4-4bf4-aa70-16bded646c72
            └── 5a57e2cc-c4d4-4bf4-aa70-16bded646c72.attr
```

And those are files stores the BLOB data, i.g. the image blob.

On prod mode we can configure the storage engine to be something like AWS S3:

```
ss.instances=store1
ss.store1.impl=act.storage.S3StoragePlugin
ss.store1.keyId=<YOUR_AWS_S3_SVC_KEY>
ss.store1.keySecret=<YOUR_AWS_S3_SVC_SECRET>
ss.store1.bucket=<YOUR_BUCKET_NAME>
ss.store1.staticWebEndpoint=//myapp.s3-website-ap-southeast-2.amazonaws.com
ss.store1.defStorageClass=standard
ss.store1.get.noGet=true
ss.store1.maxErrorRetry=20
ss.store1.tcpKeepAlive=true
ss.store1.maxConnection=30
```

You can put the configuration from PROD mode in `prod` configuration folder located at
`resources/conf/prod`. When your app starts in `PROD` mode it will automatically assume
the current profile is `prod` and use any properties file to overwrite the properties 
file found in the `resources/conf/common` folder.
 
## FAQ

**Question**: Why do I get the following error message ?

`Cannot encrypt/decrypt! Please download Java Crypto Extension pack from Oracle`

**Answer**: Please download and install the JCE pack from Oracle for
[Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html) or 
[Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)
