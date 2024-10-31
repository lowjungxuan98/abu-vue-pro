# Setting Up the abu-vue-pro Project

This comprehensive guide is designed to help junior software engineers set up the **abu-vue-pro** project on their
local machines. By following these step-by-step instructions, you'll be able to run the project smoothly and understand
its architecture better.

---

## Table of Contents

1. [Chapter 1: Project Setup](#chapter-1-project-setup)
    1. [Prerequisites](#1-prerequisites)
    2. [Clone the Backend Repository](#2-clone-the-backend-repository)
    3. [Checkout the Correct Branch](#3-checkout-the-correct-branch)
    4. [Set Up the Database](#4-set-up-the-database)
        - [Install Docker Desktop](#install-docker-desktop)
        - [Run MySQL Container](#run-mysql-container)
        - [Install Navicat](#install-navicat)
        - [Configure MySQL Connection](#configure-mysql-connection)
        - [Create the Database](#create-the-database)
        - [Import the SQL File](#import-the-sql-file)
    5. [Set Up Redis](#5-set-up-redis)
    6. [Build and Run the Backend](#6-build-and-run-the-backend)
        - [Install Maven](#install-maven)
        - [Build with Maven](#build-with-maven)
        - [Run the Backend Application](#run-the-backend-application)
    7. [Set Up the Frontend](#7-set-up-the-frontend)
        - [Clone the Frontend Repository](#clone-the-frontend-repository)
        - [Install Node.js and Package Managers](#install-nodejs-and-package-managers)
        - [Install Dependencies](#install-dependencies)
        - [Run the Development Server](#run-the-development-server)
2. [Chapter 2: Project Architecture Overview](#chapter-2-project-architecture-overview)
3. [Chapter 3: Create New Feature](#chapter-3-create-new-feature)
    1. [Create Table in DB](#1-create-table-in-db)
    2. [Code Generation](#2-code-generation)
    3. [Running the Back-end](#3-running-the-back-end)
    4. [Running the Front-end](#4-running-the-front-end)
4. [Chapter 4: Create New Module](#chapter-4-create-new-module)
    1. [Create Module](#1-create-abu-module-demo)
    2. [Create Submodule(API)](#2-create-abu-module-demo-api)
    3. [Create Submodule(Biz)](#3-create-abu-module-demo-biz)
    4. [Add abu-module-demo to abu-server](#4-add-abu-module-demo-to-abu-server)
    5. [Testing](#5-testing)
5. [Chapter 5: Delete a module](#chapter-5-delete-a-module)
6. [Chapter 6: Change Package Name](#chapter-6-change-package-name)

---

# Chapter 1: Project Setup

## 1. Prerequisites

Before you begin, make sure your system meets the following requirements:

- **Operating System**: macOS
- **Software and Tools**:
    - **Git**: Version control system for cloning repositories
    - **IntelliJ IDEA**: Integrated Development Environment (IDE) for Java
    - **Homebrew** (for macOS users): Package manager for installing software
    - **Docker Desktop**: Container platform for running MySQL and Redis
    - **Navicat** or **MySQL Workbench**: GUI tools for managing MySQL databases
    - **Node.js**, **npm**, and **pnpm**: For frontend development
    - **Maven**: Build automation tool for Java projects

## 2. Clone the Backend Repository

### Step-by-Step Instructions:

1. **Open IntelliJ IDEA**:
    - If you don't have it installed, download it
      from [IntelliJ IDEA Downloads](https://www.jetbrains.com/idea/download/).

2. **Clone the Repository**:
    - Go to **VCS** in the top menu and select **Get from Version Control**.
    - In the **URL** field, enter the backend repository URL:

      ```plaintext
      https://github.com/lowjungxuan98/abu-vue-pro.git
      ```

    - Choose a **Directory** where you want to store the project.
    - Click **Clone** to download the project to your local machine.

3. **Wait for IntelliJ IDEA** to import the project and index files.

## 3. Checkout the Correct Branch

The project uses different branches for various Java versions. Ensure you're on the correct branch that matches your
Java Development Kit (JDK) version.

### Steps:

1. **Open the Terminal** within IntelliJ IDEA:
    - Go to **View > Tool Windows > Terminal**.

2. **Navigate to the Project Directory** (if you're not already there):

   ```bash
   cd path/to/abu-vue-pro
   ```

3. **List All Branches** to find the one you need:

   ```bash
   git branch -a
   ```

4. **Checkout the `master-jdk17` Branch** (assuming you're using JDK 17):

   ```bash
   git checkout master-jdk17
   ```

5. **Pull the Latest Changes**:

   ```bash
   git pull origin master-jdk17
   ```

## 4. Set Up the Database

The project uses MySQL as its database. You'll need to set up a MySQL instance and import the project's database schema.

### Install Docker Desktop

1. **Download Docker Desktop**:
    - Visit the [Docker Desktop Download Page](https://www.docker.com/products/docker-desktop/).
    - Choose the version for your operating system (Windows, macOS, or Linux).

2. **Install Docker Desktop**:
    - Run the installer and follow the on-screen instructions.
    - After installation, launch Docker Desktop to ensure it's running.

### Run MySQL Container

1. **Open Your Terminal**.

2. **Pull the MySQL Docker Image** (optional, Docker will do this automatically if the image is not present):

   ```bash
   docker pull mysql
   ```

3. **Run the MySQL Container**:

   ```bash
   docker run -d \
     --name mysql-demo \
     -p 3306:3306 \
     -e MYSQL_ROOT_PASSWORD=123456 \
     mysql:latest
   ```

    - **Explanation**:
        - `-d`: Runs the container in detached mode (in the background).
        - `--name mysql-demo`: Names the container "mysql-demo".
        - `-p 3306:3306`: Maps port 3306 of the container to port 3306 on your machine.
        - `-e MYSQL_ROOT_PASSWORD=123456`: Sets the MySQL root password to `123456`.
        - `mysql:latest`: Specifies the MySQL image and tag to use.

4. **Verify the Container is Running**:

   ```bash
   docker ps
   ```

    - You should see `mysql-demo` listed.

### Install Navicat

Navicat is a GUI tool for managing databases. If you prefer, you can use **MySQL Workbench** or **DBeaver**.

1. **Download Navicat**:
    - Visit the [Navicat Download Page](https://navicat.com/en/download/navicat-premium-essentials).

2. **Install Navicat**:
    - Run the installer and follow the instructions.

### Configure MySQL Connection

1. **Open Navicat**.

2. **Create a New Connection**:
    - Click on **Connection** > **MySQL**.

3. **Enter Connection Details**:
    - **Connection Name**: `Local MySQL`
    - **Host**: `127.0.0.1`
    - **Port**: `3306`
    - **Username**: `root`
    - **Password**: `123456`

4. **Test the Connection**:
    - Click on **Test Connection** to ensure the settings are correct.

5. **Save the Connection**:
    - Click **OK**.

### Create the Database

1. **Connect to MySQL**:
    - Double-click the **Local MySQL** connection.

2. **Create a New Database**:
    - Right-click on the **Local MySQL** connection and select **Create Database**.

3. **Enter Database Details**:
    - **Database Name**: `abu-vue-pro`
    - **Character Set**: `utf8mb4`
    - **Collation**: `utf8mb4_general_ci`

4. **Create the Database**:
    - Click **OK**.

### Import the SQL File

1. **Locate the SQL File**:
    - The SQL file is located in the backend project directory at `sql/mysql/abu-vue-pro.sql`.

2. **Import the SQL File**:
    - Right-click on the `abu-vue-pro` database and select **Execute SQL File**.
    - Click on the **...** button to browse and select the SQL file.
    - Click **Start** to begin the import process.

3. **Verify the Import**:
    - After the import completes, refresh the database to see the tables and data.

## 5. Set Up Redis

Redis is used for caching and session management in the project.

1. **Run the Redis Container**:

   ```bash
   docker run -d --name redis-demo -p 6379:6379 redis:latest
   ```

    - **Explanation**:
        - `-d`: Runs the container in detached mode.
        - `--name redis-demo`: Names the container "redis-demo".
        - `-p 6379:6379`: Maps port 6379 of the container to port 6379 on your machine.

2. **Verify the Container is Running**:
   ```bash
   docker ps
   ```
    - You should see `redis-demo` listed.

## 6. Build and Run the Backend

### Install Maven

If you don't have Maven installed, follow these steps:

1. **For macOS Users Using Homebrew**:
   ```bash
   brew install maven
   ```

2. **Verify Maven Installation**:

   ```bash
   mvn -v
   ```

    - You should see the Maven version information.

### Build with Maven

1. **Navigate to the Project Root Directory**:

   ```bash
   cd path/to/abu-vue-pro
   ```

2. **Clean and Package the Project**:

   ```bash
   mvn clean package -DskipTests
   ```

    - **Explanation**:
        - `clean`: Removes the `target` directory.
        - `package`: Compiles the code and packages it into a JAR/WAR file.
        - `-DskipTests`: Skips running unit tests to speed up the build process.

3. **Wait for the Build to Complete**:
    - Ensure there are no errors during the build process.

### Run the Backend Application

1. **Open IntelliJ IDEA**.

2. **Import the Project** (if not already opened):
    - Go to **File > Open** and select the `abu-vue-pro` directory.

3. **Configure the Application**:
    - Open the `Application.java` file located in `abu-admin-server/src/main/java/my.abu.pp/adminserver`.
    - Right-click on the `Application.java` file and select **Run 'Application.main()'**.

4. **Modify Application Configuration** (if necessary):
    - The application uses `application-dev.yml` for development settings.
    - Ensure the database and Redis configurations match your local setup.

5. **Run the Application**:
    - Click on the **Run** button or use the shortcut **Shift + F10**.

6. **Verify the Application is Running**:
    - Check the console output for any errors.
    - By default, the backend runs on port **8080**.

## 7. Set Up the Frontend

### Clone the Frontend Repository

1. **Open Your Terminal**.

2. **Navigate to Your Workspace Directory**:

   ```bash
   cd path/to/your/workspace
   ```

3. **Clone the Frontend Repository**:

   ```bash
   git clone https://github.com/abucode/abu-ui-admin-vue3.git
   ```

4. **Navigate into the Project Directory**:

   ```bash
   cd abu-ui-admin-vue3
   ```

### Install Node.js and Package Managers

1. **Install Node.js**:

    - **For macOS Users Using Homebrew**:

      ```bash
      brew install node
      ```

2. **Verify Node.js and npm Installation**:

   ```bash
   node -v
   npm -v
   ```

3. **Install pnpm**:

    - **Using npm**:

      ```bash
      npm install -g pnpm
      ```

    - **Verify pnpm Installation**:

      ```bash
      pnpm -v
      ```

### Install Dependencies

1. **Install Project Dependencies**:

   ```bash
   pnpm install
   ```

    - **Note**: Using `pnpm` is recommended for its speed and efficient disk space usage.

### Run the Development Server

1. **Start the Frontend Application**:

   ```bash
   pnpm dev
   ```

2. **Access the Application**:

    - Open your web browser and navigate to [http://localhost:80](http://localhost:80).

---

# Chapter 2: Project Architecture Overview

[项目结构](https://doc.iocoder.cn/project-intro/)

[自顶向下，讲解项目的整体结构（上）](https://t.zsxq.com/07FiIaQr3)

[自顶向下，讲解项目的整体结构（下）](https://t.zsxq.com/07yNfE6un)

---

# Chapter 3: Create New Feature

> Note: The unit test part able to skip because the code generator is not provide the source

[Video](https://t.zsxq.com/2HeGQ)

[Documentation](https://doc.iocoder.cn/new-feature/)

## 1. Create Table in DB

Design a database table for user groups named `system_group`, with the following create table statement:

```MySQL
CREATE TABLE `system_group`
(
    `id`          bigint                                  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`        varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Name',
    `description` varchar(512) COLLATE utf8mb4_unicode_ci                      DEFAULT NULL COMMENT 'Description',
    `status`      tinyint                                 NOT NULL COMMENT 'Status',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'Creator',
    `create_time` datetime                                NOT NULL             DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'Updater',
    `update_time` datetime                                NOT NULL             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update Time',
    `deleted`     bit(1)                                  NOT NULL             DEFAULT b'0' COMMENT 'Deleted',
    `tenant_id`   bigint                                  NOT NULL             DEFAULT '0' COMMENT 'Tenant ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='User Group';
```

**Note on Table Name Prefix**: The prefix of the table name should be consistent with the Maven module's name. For
example, since the user group is in the `abu-module-system` module, the table name's prefix is `system_`.

*Explanation*: This convention helps in organizing the database tables according to the modules they belong to, making
maintenance easier.

## 2. Code Generation

![alt text](https://doc.iocoder.cn/img/%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90/%E5%8D%95%E8%A1%A8/%E5%AF%BC%E5%85%A5%E8%A1%A8.png)

![alt text](https://doc.iocoder.cn/img/%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90/%E5%8D%95%E8%A1%A8/%E4%BF%AE%E6%94%B9%E9%85%8D%E7%BD%AE-%E5%9F%BA%E6%9C%AC%E4%BF%A1%E6%81%AF.png)

![alt text](https://doc.iocoder.cn/img/%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90/%E5%8D%95%E8%A1%A8/%E4%BF%AE%E6%94%B9%E9%85%8D%E7%BD%AE-%E5%AD%97%E6%AE%B5%E4%BF%A1%E6%81%AF.png)

![alt text](https://doc.iocoder.cn/img/%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90/%E5%8D%95%E8%A1%A8/%E4%BF%AE%E6%94%B9%E9%85%8D%E7%BD%AE-%E7%94%9F%E6%88%90%E4%BF%A1%E6%81%AF.png)

| Front-end Project | Front-end Template Type             |
|-------------------|-------------------------------------|
| abu-ui-admin-vue2 | Vue2 Element UI Standard Template   |
| abu-ui-admin-vue3 | Vue3 Element Plus Standard Template |
| abu-ui-admin-vben | Vue3 Vben Template                  |

## 3. Running the Back-end

![alt text](https://doc.iocoder.cn/img/%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90/%E5%8D%95%E8%A1%A8/%E5%A4%8D%E5%88%B6%E5%90%8E%E7%AB%AF.png)

> ![alt text](https://doc.iocoder.cn/img/%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90/%E5%8D%95%E8%A1%A8/%E6%B7%BB%E5%8A%A0%E9%94%99%E8%AF%AF%E7%A0%81.png)
>
> Please remember to delete the `ErrorCodeConstabts_手动操作.java`

![alt text](https://doc.iocoder.cn/img/%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90/%E5%8D%95%E8%A1%A8/%E5%88%9D%E5%A7%8B%E5%8C%96%E8%8F%9C%E5%8D%95.png)

![alt text](https://doc.iocoder.cn/img/%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90/%E5%8D%95%E8%A1%A8/%E6%8E%A5%E5%8F%A3%E8%B0%83%E7%94%A8.png)

## 4. Running the Front-end

![alt text](https://doc.iocoder.cn/img/%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90/%E5%8D%95%E8%A1%A8/%E5%A4%8D%E5%88%B6%E5%89%8D%E7%AB%AF.png)

> ![alt text](https://doc.iocoder.cn/img/%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90/%E5%8D%95%E8%A1%A8/%E5%89%8D%E7%AB%AF%E7%95%8C%E9%9D%A2.png)
>
> If the feature didn't appear please Navigate to `系统管理` > `菜单管理` > `刷新菜单缓存` to clear the cache

---

Here's the structured and translated guide for setting up the new module, `abu-module-demo`, in English:

---

Here is the translated and formatted guide for creating and integrating the new `abu-module-demo` module, along with
its API and business logic submodules:

---

# Chapter 4: Create New Module

## 1. Create `abu-module-demo`

- **Parent**: abu
- **Name**: abu-module-demo
- **Location**: `<root dir>/abu-module-demo`
- **Steps**:
    - Delete the `src` folder.
    - Edit `pom.xml`:

   ```xml
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       
       <parent>
           <artifactId>abu</artifactId>
           <groupId>my.abu.gg</groupId>
           <version>${revision}</version> <!-- 1. Modify version to ${revision} -->
       </parent>
       
       <modules>
           <module>abu-module-demo-api</module>
           <module>abu-module-demo-biz</module>
       </modules>
       
       <modelVersion>4.0.0</modelVersion>
       <artifactId>abu-module-demo</artifactId>
       <packaging>pom</packaging> <!-- 2. Add packaging as pom -->
       
       <name>${project.artifactId}</name> <!-- 3. Add name as ${project.artifactId} -->
       <description> <!-- 4. Add description for the module -->
           The demo module, mainly implementing features such as XXX, YYY, ZZZ.
       </description>
       
       <properties>
           <maven.compiler.source>23</maven.compiler.source>
           <maven.compiler.target>23</maven.compiler.target>
           <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
       </properties>
   </project>
   ```

## 2. Create `abu-module-demo-api`

- **Parent**: abu-module-demo
- **Name**: abu-module-demo-api
- **Location**: `<root dir>/abu-module-demo/abu-module-demo-api`
- **Steps**:
    - Delete the `src` folder.
    - Edit `pom.xml`:

   ```xml
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       
       <modelVersion>4.0.0</modelVersion>
       
       <parent>
           <groupId>my.abu.gg</groupId>
           <artifactId>abu-module-demo</artifactId>
           <version>${revision}</version> <!-- 1. Modify version to ${revision} -->
       </parent>
       
       <artifactId>abu-module-demo-api</artifactId>
       <packaging>jar</packaging> <!-- 2. Add packaging as jar -->
       
       <name>${project.artifactId}</name> <!-- 3. Add name as ${project.artifactId} -->
       <description> <!-- 4. Add description for the module -->
           The API for the demo module, exposed for other modules to call.
       </description>
       
       <dependencies> <!-- 5. Add abu-common dependency -->
           <dependency>
               <groupId>my.abu.gg</groupId>
               <artifactId>abu-common</artifactId>
           </dependency>
       </dependencies>
       
       <properties>
           <maven.compiler.source>23</maven.compiler.source>
           <maven.compiler.target>23</maven.compiler.target>
           <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
       </properties>
   </project>
   ```

## 3. Create `abu-module-demo-biz`

- **Parent**: abu-module-demo
- **Name**: abu-module-demo-biz
- **Location**: `<root dir>/abu-module-demo/abu-module-demo-biz`
- **Steps**:
    - Delete the `src` folder.
    - Create the following folders:

       ```
       project
       └─ api
       └─ controller
       │   └─ admin
       │   └─ app
       └─ convert
       └─ dal
       └─ enum
       └─ job
       └─ mq
       └─ service
       ```

    - Create `DemoTestController` in `controller.admin`:

       ```java
       package my.abu.pp.module.demo.controller.admin;
  
       import my.abu.pp.framework.common.pojo.CommonResult;
       import io.swagger.v3.oas.annotations.tags.Tag;
       import io.swagger.v3.oas.annotations.Operation;
       import org.springframework.validation.annotation.Validated;
       import org.springframework.web.bind.annotation.GetMapping;
       import org.springframework.web.bind.annotation.RequestMapping;
       import org.springframework.web.bind.annotation.RestController;
  
       import static my.abu.pp.framework.common.pojo.CommonResult.success;
  
       @Tag(name = "Admin - Test")
       @RestController
       @RequestMapping("/demo/test")
       @Validated
       public class DemoTestController {
  
           @GetMapping("/get")
           @Operation(summary = "Retrieve test information")
           public CommonResult<String> get() {
               return success("true");
           }
       }
       ```

    - Create `AppDemoTestController` in `controller.app`:

       ```java
       package my.abu.pp.module.demo.controller.app;
  
       import my.abu.pp.framework.common.pojo.CommonResult;
       import io.swagger.v3.oas.annotations.tags.Tag;
       import io.swagger.v3.oas.annotations.Operation;
       import org.springframework.validation.annotation.Validated;
       import org.springframework.web.bind.annotation.GetMapping;
       import org.springframework.web.bind.annotation.RequestMapping;
       import org.springframework.web.bind.annotation.RestController;
  
       import static my.abu.pp.framework.common.pojo.CommonResult.success;
  
       @Tag(name = "User App - Test")
       @RestController
       @RequestMapping("/demo/test")
       @Validated
       public class AppDemoTestController {
  
           @GetMapping("/get")
           @Operation(summary = "Retrieve test information")
           public CommonResult<String> get() {
               return success("true");
           }
       }
       ```

    - Edit `pom.xml`:

       ```xml
       <project xmlns="http://maven.apache.org/POM/4.0.0"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
           
           <modelVersion>4.0.0</modelVersion>
           
           <parent>
               <groupId>my.abu.gg</groupId>
               <artifactId>abu-module-demo</artifactId>
               <version>${revision}</version> <!-- 1. Modify version to ${revision} -->
           </parent>
           
           <packaging>jar</packaging> <!-- 2. Add packaging as jar -->
           
           <artifactId>abu-module-demo-biz</artifactId>
           
           <name>${project.artifactId}</name> <!-- 3. Add name as ${project.artifactId} -->
           <description> <!-- 4. Add description for the module -->
               The demo module, implementing features such as XXX, YYY, ZZZ.
           </description>
           
           <dependencies> <!-- 5. Add dependencies for common business and technical components -->
               <dependency>
                   <groupId>my.abu.gg</groupId>
                   <artifactId>abu-module-demo-api</artifactId>
                   <version>${revision}</version>
               </dependency>
               
               <!-- Web-related dependencies -->
               <dependency>
                   <groupId>my.abu.gg</groupId>
                   <artifactId>abu-spring-boot-starter-web</artifactId>
               </dependency>
               <dependency>
                   <groupId>my.abu.gg</groupId>
                   <artifactId>abu-spring-boot-starter-security</artifactId>
               </dependency>
               
               <!-- Database-related dependencies -->
               <dependency>
                   <groupId>my.abu.gg</groupId>
                   <artifactId>abu-spring-boot-starter-mybatis</artifactId>
               </dependency>
               
               <!-- Testing dependencies -->
               <dependency>
                   <groupId>my.abu.gg</groupId>
                   <artifactId>abu-spring-boot-starter-test</artifactId>
               </dependency>
           </dependencies>
           
           <properties>
               <maven.compiler.source>23</maven.compiler.source>
               <maven.compiler.target>23</maven.compiler.target>
               <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
           </properties>
       </project>
       ```

## 4. Add `abu-module-demo` to `abu-server`

- Edit `.../abu-server/pom.xml`:

   ```xml
    <dependency>
       <groupId>my.abu.gg</groupId>
       <artifactId>abu-module-demo-biz</artifactId>
       <version>${revision}</version>
   </dependency>
   ```

## 5. Testing

1. Restart or re-run the spring application
2. [API Doc](http://127.0.0.1:48080/doc.html)
3. Admin API
   ![alt text](https://doc.iocoder.cn/img/%E6%96%B0%E5%BB%BA%E6%A8%A1%E5%9D%97/48.png)
4. APP API
   ![alt text](https://doc.iocoder.cn/img/%E6%96%B0%E5%BB%BA%E6%A8%A1%E5%9D%97/49.png)

---

# Chapter 5: Delete a Module

---

# Chapter 6: Change Package Name

1. edit [ProjectReactor.java](abu-server/src/test/java/my/abu/pp/ProjectReactor.java)
    - groupIdNew -> my.abu.gg
    - artifactIdNew -> abu
    - packageNameNew -> my.abu.pp
    - titleNew -> 阿布管理系统
2. run the [ProjectReactor.java](abu-server/src/test/java/my/abu/pp/ProjectReactor.java), once complete with generate a
   new folder
3. replace all `ruoyi-vue-pro` (Optional)
    - replace `https://github.com/YunaiV/ruoyi-vue-pro` with https://github.com/lowjungxuan98/abu-vue-pro
    - replace `ruoyi-vue-pro` to `abu-vue-pro`
    - replace `芋道源码` to `阿布源码`
    - replace `芋道` to `阿布`
    - rename the database from `ruoyi-vue-pro` to `abu-vue-pro`
    - delete the **Redis** in docker and re-install
