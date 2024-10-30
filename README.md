# Setting Up the Ruoyi-Vue-Pro Project

This comprehensive guide is designed to help junior software engineers set up the **Ruoyi-Vue-Pro** project on their
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
      https://github.com/YunaiV/ruoyi-vue-pro.git
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
   cd path/to/ruoyi-vue-pro
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
    - **Database Name**: `ruoyi-vue-pro`
    - **Character Set**: `utf8mb4`
    - **Collation**: `utf8mb4_general_ci`

4. **Create the Database**:
    - Click **OK**.

### Import the SQL File

1. **Locate the SQL File**:
    - The SQL file is located in the backend project directory at `sql/mysql/ruoyi-vue-pro.sql`.

2. **Import the SQL File**:
    - Right-click on the `ruoyi-vue-pro` database and select **Execute SQL File**.
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
   cd path/to/ruoyi-vue-pro
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
    - Go to **File > Open** and select the `ruoyi-vue-pro` directory.

3. **Configure the Application**:
    - Open the `Application.java` file located in `yudao-admin-server/src/main/java/cn/iocoder/yudao/adminserver`.
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
   git clone https://github.com/yudaocode/yudao-ui-admin-vue3.git
   ```

4. **Navigate into the Project Directory**:

   ```bash
   cd yudao-ui-admin-vue3
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

this doc was giving a junior software engineer to faster set up their pc for running the project please help me make it
more detail, easy to understand and format it more nicely