# TagMyCode Official IntelliJ IDEA Plugin
This is the official IntelliJ IDEA plugin for [TagMyCode](http://tagmycode.com).

It works also on others JetBrains IDEs:
* RubyMine
* WebStorm
* PhpStorm
* PyCharm
* AppCode
* Android Studio
* 0xDBE

## Getting started ##
### Dependencies###
To download all dependencies use Maven

```
mvn clean package
```

this command download all required languages into lib folder

###Consumer Id and Consumer secret###
You need to create a Java class Secret.java into plugin/com/tagmycode/intellij/secret

```
package com.tagmycode.intellij.secret;

public class Secret extends AbstractSecret {

    @Override
    public String getConsumerId() {
        return "YOUR_CONSUMER_ID";
    }

    @Override
    public String getConsumerSecret() {
        return "YOUR_CONSUMER_SECRET";
    }
}

```
