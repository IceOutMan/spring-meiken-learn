#### pom文件依赖
```
<dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.0</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>io.pebbletemplates</groupId>
      <artifactId>pebble</artifactId>
      <version>3.1.4</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.11.3</version>
    </dependency>
    <dependency>
      <groupId>com.google.guava</groupId>
      <artifactId>guava</artifactId>
      <version>29.0-jre</version>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-simple</artifactId>
      <version>1.7.2</version>
    </dependency>

    <dependency>
      <groupId>org.apache.tomcat</groupId>
      <artifactId>tomcat-catalina</artifactId>
      <version>8.5.3</version>
      <scope>provided</scope>
    </dependency>

    //支持获取参数，不然是 arg0, arg1 arg 2 的伪参数
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-compiler-plugin</artifactId>
          <configuration>
            <compilerArgument>-parameters</compilerArgument>
            <source>15</source>
            <target>15</target>
          </configuration>
        </plugin>
```
### 手动实现MVC

#### 代码结构
> 1.DispatcherSevlert 拦截所有访问请求
> 2.通过反射获取 Controller 中的所有 GET POST 接口
> 3.调用 Controller 中的接口得到 ModelAndView
> 4.通过模版引擎进行ModelAndView的渲染和返回