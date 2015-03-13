Adding necessary repositories:

```
 <repositories>
  <repository>
   <id>org.bindgen</id>
   <name>Bindgen Repository</name>
   <url>http://repo.joist.ws</url>
   <snapshots><enabled>false</enabled></snapshots>
  </repository>
  <repository>
   <id>org.bindgen.wicket</id>
   <name>Bindgen Wicket Repository</name>
   <url>http://bindgen-wicket.googlecode.com/svn/maven2</url>
   <snapshots><enabled>false</enabled></snapshots>
  </repository>
 </repositories>
 <pluginRepositories>
  <pluginRepository>
   <id>maven-annotation-plugin</id>
   <name>maven-annotation-processor Repository</name>
   <url>http://maven-annotation-plugin.googlecode.com/svn/trunk/mavenrepo</url>
  </pluginRepository>
 </pluginRepositories>
```

Adding necessary dependencies:

```
 <properties>
  <bindgen.version>2.5</bindgen.version>
  <bindgen.wicket.version>1.0</bindgen.wicket.version>
 </properties>
 <dependency>
  <groupId>org.bindgen.wicket</groupId>
  <artifactId>bindgen-wicket</artifactId>
  <version>${bindgen.wicket.version}</version>
 </dependency>
```

Configuring Maven2 to use Bindgen correctly:

```
 <build>
  <plugins>
   <plugin>
    <!--
         set source compliance level to 1.6, 
         do not use javac to run annotation processors, 
         we will use the maven-processor-plugin to do this
    -->
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
     <source>1.6</source>
     <target>1.6</target>
     <compilerArgument>-proc:none</compilerArgument>
    </configuration>
   </plugin>
   <plugin>
    <groupId>org.bsc.maven</groupId>
    <artifactId>maven-processor-plugin</artifactId>
    <executions>
     <execution>
      <id>process</id>
      <goals>
       <goal>process</goal>
      </goals>
      <phase>generate-sources</phase>
     </execution>
    </executions>
    <dependencies/>
   </plugin>
  </plugins>
 </build>
```

Configure Maven2 Eclipse plugin to setup the project to use Bindgen annotation processor when `mvn eclipse:eclipse` is executed

```
 <build>
  <plugins>
   <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-eclipse-plugin</artifactId>
    <configuration>
     <additionalConfig>
      <file>
       <name>.factorypath</name>
       <content>
<![CDATA[<factorypath>
  <factorypathentry kind="VARJAR" id="M2_REPO/org/bindgen/bindgen/${bindgen.version}/bindgen-${bindgen.version}.jar" enabled="true" runInBatchMode="false"/>
  </factorypath>]]>
       </content>
      </file>
      <file>
       <name>.settings/org.eclipse.jdt.apt.core.prefs</name>
       <content>
<![CDATA[
eclipse.preferences.version=1
org.eclipse.jdt.apt.aptEnabled=true
org.eclipse.jdt.apt.genSrcDir=target/generated-sources/apt/main/java
org.eclipse.jdt.apt.reconcileEnabled=true]]>
       </content>
      </file>
     </additionalConfig>
    </configuration>
   </plugin>
  </plugins>
 </build>

```