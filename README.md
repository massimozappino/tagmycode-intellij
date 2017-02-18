TagMyCode Official IntelliJ IDEA Plugin
=======================================

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

```bash
mvn clean package
```

this command downloads all required dependencies into ```lib``` folder

Set the correct project compiler output: go to ```File``` -> ```Project Structure...``` -> ```Project``` -> ```Project compiler output``` and add the full **absolute** path, for example ```/home/user/projects/tagmycode-intellij/out```
###Consumer Id and Consumer secret###
You need to create a Java class ```Secret.java``` into ```plugin/com/tagmycode/intellij```

```java
package com.tagmycode.intellij;

import com.tagmycode.plugin.AbstractSecret;

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
